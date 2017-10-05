package ch.ethz.ivt.abmt.assignment1.ex2agent;

import ch.ethz.ivt.abmt.assignment1.ReflectiveTestUtils;
import ch.ethz.ivt.abmt.assignment1.ex1network.Link;
import ch.ethz.ivt.abmt.assignment1.ex1network.Node;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @author thibautd
 */
public class LongestLinkAgentTest {
	private final Random random = new Random( 123 );

	@Test
	public void testImplementsAgent() {
		ReflectiveTestUtils.assertImplementsInterface( RandomAgent.class , Agent.class );
	}

	@Test
	public void testHasEmptyConstructor() {
		ReflectiveTestUtils.assertHasConstructorWithParameterTypes( RandomAgent.class );
	}

	@Test
	public void testChoosesLongestLink() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<LongestLinkAgent> constructor = LongestLinkAgent.class.getConstructor();

		for ( int i=0; i < 10; i++ ) {
			Agent agent = (Agent) constructor.newInstance();
			Link[] links = createTestLinks( i );

			agent.start( links[ 0 ] );
			Link selected = agent.selectNextLink( links );
			Assert.assertSame(
					"link selected was not the longest",
					links[ i ],
					selected );
		}
	}

	@Test
	public void testStopsAfterTen() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<LongestLinkAgent> constructor = LongestLinkAgent.class.getConstructor();

		Link[] links = createTestLinks( 0 );
		Agent agent = (Agent) constructor.newInstance();
		agent.start( links[ 0 ] );
		for ( int i=0; i < 10; i++ ) {
			agent.selectNextLink( links );
		}

		Assert.assertNull(
				"LongestLinkAgent should return null after 10 steps",
				agent.selectNextLink( links ) );
	}

	@Test
	public void testRestart() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<LongestLinkAgent> constructor = LongestLinkAgent.class.getConstructor();

		Link[] links = createTestLinks( 0 );
		Agent agent = (Agent) constructor.newInstance();
		agent.start( links[ 0 ] );
		for ( int i=0; i < 10; i++ ) {
			agent.selectNextLink( links );
		}

		agent.stop();
		agent.start( links[ 0 ] );

		for ( int i=0; i < 10; i++ ) {
			Link selected = agent.selectNextLink( links );
			Assert.assertNotNull(
					"LongestLinkAgent did not restart counting after stop",
					selected );
		}

	}

	private Link[] createTestLinks( final int longestIndex ) {
		// one node and 10 loop links
		final double max = random.nextDouble() * 1000;
		final Link[] links = new Link[10];

		final Node node = new Node() {
			
			public Link[] getInLinks() {
				return links;
			}

			public Link[] getOutLinks() {
				return links;
			}

			public int getId() {
				return 0;
			}
		};

		for ( int i=0; i < links.length; i++ ) {
			final int id = i;
			links[ i ] = new Link() {

				public Node getStartNode() {
					return node;
				}

				public Node getEndNode() {
					return node;
				}

				public double getLength() {
					return id == longestIndex ? max : random.nextDouble() * max;
				}

				public int getId() {
					return id;
				}
			};
		}

		return links;
	}
}
