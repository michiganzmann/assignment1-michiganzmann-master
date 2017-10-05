package ch.ethz.ivt.abmt.assignment1;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @author thibautd
 */
public class ReflectiveTestUtils {
	public static void assertImplementsInterface( Class<?> clazz , Class<?> interfaze ) {
		final Class<?>[] interfaces = clazz.getInterfaces();

		Assert.assertEquals(
				clazz.getSimpleName()+" does not implement the right number of interfaces",
				1,
				interfaces.length );

		Assert.assertEquals(
				clazz.getSimpleName()+" implements the wrong interface",
				interfaze,
				interfaces[0] );
	}

	public static void assertHasConstructorWithParameterTypes( Class clazz, Class<?>... types ) {
		try {
			clazz.getConstructor( types );
		}
		catch ( NoSuchMethodException e ) {
			Assert.fail( clazz.getSimpleName()+" does not have a constructor with types "+ Arrays.toString( types ) );
		}
	}
}
