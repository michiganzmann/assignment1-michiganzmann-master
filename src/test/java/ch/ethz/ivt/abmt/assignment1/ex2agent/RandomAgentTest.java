package ch.ethz.ivt.abmt.assignment1.ex2agent;

import ch.ethz.ivt.abmt.assignment1.ReflectiveTestUtils;
import ch.ethz.ivt.abmt.assignment1.ex1network.Link;
import ch.ethz.ivt.abmt.assignment1.ex1network.Node;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author thibautd
 */
public class RandomAgentTest {
	@Test
	public void testImplementsAgent() {
		ReflectiveTestUtils.assertImplementsInterface( RandomAgent.class , Agent.class );
	}

	@Test
	public void testHasEmptyConstructor() {
		ReflectiveTestUtils.assertHasConstructorWithParameterTypes( RandomAgent.class );
	}

	@Test
	public void testRandomness() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor<RandomAgent> constructor = RandomAgent.class.getConstructor();

		Agent agent = (Agent) constructor.newInstance();
		Link[] links = createTestLinks();

		agent.start( links[ 0 ] );
		Link link1 = agent.selectNextLink( links );

		for ( int i=0; i < 100; i++ ) {
			Link newLink = agent.selectNextLink( links );
			if ( newLink != link1 ) return;
		}
		Assert.fail( "RandomAgent returned 100 times the same decision. Not very random!" );
	}

	private Link[] createTestLinks() {
		// one node and 10 loop links
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
					return 1;
				}

				public int getId() {
					return id;
				}
			};
		}

		return links;
	}
}
