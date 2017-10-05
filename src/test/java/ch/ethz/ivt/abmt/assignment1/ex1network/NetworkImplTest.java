package ch.ethz.ivt.abmt.assignment1.ex1network;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author thibautd
 */
public class NetworkImplTest {
	@Test
	public void testImplementsNode() {
		Class<NetworkImpl> clazz = NetworkImpl.class;
		final Class<?>[] interfaces = clazz.getInterfaces();

		Assert.assertEquals(
				"NetworkImpl does not implement any interface",
				1,
				interfaces.length );

		Assert.assertEquals(
				"NetworkImpl implements the wrong interface",
				Network.class,
				interfaces[0] );
	}

	@Test
	public void testConstructor() throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<NetworkImpl> clazz = NetworkImpl.class;
		final Constructor<?>[] constructors = clazz.getDeclaredConstructors();

		Assert.assertTrue(
				"NetworkImpl is expected to have one declared constructor",
				constructors.length == 1);

		final Constructor<?> constructor = constructors[ 0 ];

		Assert.assertEquals(
				"Unexpected number of constructor arguments",
				2,
				constructor.getParameterTypes().length );

		Class<?> constructorParameter = constructor.getParameterTypes()[0];
		Assert.assertTrue(
				"constructor argument is not an array",
				constructorParameter.isArray() );
		Assert.assertEquals(
				"Unexpected type of constructor argument",
				Node.class,
				constructorParameter.getComponentType() );

		constructorParameter = constructor.getParameterTypes()[1];
		Assert.assertTrue(
				"constructor argument is not an array",
				constructorParameter.isArray() );
		Assert.assertEquals(
				"Unexpected type of constructor argument",
				Link.class,
				constructorParameter.getComponentType() );

		Node[] nodes = new Node[]{ new DummyNode() , new DummyNode() };
		Link[] links = new Link[]{ new DummyLink() , new DummyLink() , new DummyLink() };
		Network node = (Network) constructor.newInstance( nodes , links );

		Assert.assertSame(
				"unexpected nodes",
				nodes,
				node.getNodes() );

		Assert.assertSame(
				"unexpected links",
				links,
				node.getLinks() );
	}

}
