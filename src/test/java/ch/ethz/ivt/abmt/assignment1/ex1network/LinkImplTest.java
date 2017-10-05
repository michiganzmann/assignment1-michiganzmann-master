package ch.ethz.ivt.abmt.assignment1.ex1network;

import ch.ethz.ivt.abmt.assignment1.ReflectiveTestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author thibautd
 */
public class LinkImplTest {
	@Test
	public void testImplementsLink() {
		ReflectiveTestUtils.assertImplementsInterface( LinkImpl.class , Link.class );
	}

	@Test
	public void testConstructor() throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<LinkImpl> clazz = LinkImpl.class;
		final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

		Assert.assertTrue(
				"LinkImpl is expected to have one declared constructor",
				constructors.length == 1);

		Constructor<LinkImpl> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor( Integer.TYPE , Double.TYPE );
		}
		catch ( NoSuchMethodException e ) {
			Assert.fail( "constructor of "+clazz.getSimpleName()+" does not have the right parameter types" );
		}

		for ( double length = 0; length < 1000; length += 20 ) {
			final Link link = (Link) constructor.newInstance( 1 , length );

			Assert.assertEquals(
					"Unexpected length",
					length,
					link.getLength(),
					1E-9 );
		}
	}

	@Test
	public void testSettersAndGetters() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<LinkImpl> clazz = LinkImpl.class;
		final Constructor<LinkImpl> constructor = clazz.getDeclaredConstructor( Integer.TYPE , Double.TYPE );

		final Method setStartNode = clazz.getMethod( "setStartNode", Node.class );
		final Method setEndNode = clazz.getMethod( "setEndNode", Node.class );

		for ( double length = 0; length < 1000; length += 20 ) {
			final Link link = (Link) constructor.newInstance( 1 , length );

			Node fromNode = new DummyNode();
			Node toNode = new DummyNode();

			setStartNode.invoke( link , fromNode );
			setEndNode.invoke( link , toNode );

			Assert.assertSame(
					"Unexpected start node",
					fromNode,
					link.getStartNode() );

			Assert.assertSame(
					"Unexpected end node",
					toNode,
					link.getEndNode() );
		}
	}

}
