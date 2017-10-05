package ch.ethz.ivt.abmt.assignment1.ex1network;

import ch.ethz.ivt.abmt.assignment1.ReflectiveTestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author thibautd
 */
public class NodeImplTest {
	@Test
	public void testImplementsNode() {
		ReflectiveTestUtils.assertImplementsInterface( NodeImpl.class , Node.class );
	}

	@Test
	public void testConstructor() throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<NodeImpl> clazz = NodeImpl.class;
		final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

		Assert.assertTrue(
				"NodeImpl is expected to have one declared constructor",
				constructors.length == 1);

		final Constructor<?> constructor = constructors[ 0 ];

		Assert.assertEquals(
				"Unexpected number of constructor arguments",
				3,
				constructor.getParameterTypes().length );

		Assert.assertEquals(
				"Unexpected type of constructor argument",
				Integer.TYPE,
				constructor.getParameterTypes()[0] );
		assertArrayOfLinkImpl( constructor.getParameterTypes()[1] );
		assertArrayOfLinkImpl( constructor.getParameterTypes()[2] );

		LinkImpl[] inLinks = new LinkImpl[]{ createLink() , createLink() };
		LinkImpl[] outLinks = new LinkImpl[]{ createLink() , createLink() , createLink() };
		Node node = (Node) constructor.newInstance( 1 , inLinks , outLinks );

		Assert.assertSame(
				"unexpected in links",
				inLinks,
				node.getInLinks() );

		Assert.assertSame(
				"unexpected out links",
				outLinks,
				node.getOutLinks() );

		for ( Link l : (Link[]) inLinks ) {
			Assert.assertSame(
					"Unexpected end node in in links after NodeImpl construction",
					node,
					l.getEndNode() );
		}

		for ( Link l : (Link[]) outLinks ) {
			Assert.assertSame(
					"Unexpected start node in out links after NodeImpl construction",
					node,
					l.getStartNode() );
		}
	}

	private void assertArrayOfLinkImpl( final Class<?> constructorParameter ) {
		Assert.assertTrue(
				"constructor argument is not an array",
				constructorParameter.isArray() );
		Assert.assertEquals(
				"Unexpected type of constructor argument",
				LinkImpl.class,
				constructorParameter.getComponentType() );
	}

	private static LinkImpl createLink() {
		try {
			return LinkImpl.class.getConstructor( Integer.TYPE , Double.TYPE ).newInstance( 1 , 1 );
		}
		catch ( Exception e ) {
			throw new RuntimeException( "problem with LinkImpl construction when testing NodeImpl. Work of LinkImpl." , e );
		}
	}
}
