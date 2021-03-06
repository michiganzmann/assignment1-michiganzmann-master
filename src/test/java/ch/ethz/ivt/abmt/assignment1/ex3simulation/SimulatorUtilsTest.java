package ch.ethz.ivt.abmt.assignment1.ex3simulation;

import ch.ethz.ivt.abmt.assignment1.ex1network.Link;
import ch.ethz.ivt.abmt.assignment1.ex1network.Network;
import ch.ethz.ivt.abmt.assignment1.ex1network.NetworkUtils;
import ch.ethz.ivt.abmt.assignment1.ex2agent.Agent;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author thibautd
 */
public class SimulatorUtilsTest {
	@Test
	public void testStartsAndStopsAgent() {
		Network network = NetworkUtils.createNetwork();
		CounterAgent agent = new CounterAgent();
		SimulatorUtils.simulate( network , agent , 3 );

		Assert.assertEquals(
				"Unexpected number of calls for start",
				1,
				agent.nStart );

		Assert.assertEquals(
				"Unexpected number of calls for stop",
				1,
				agent.nStop );
	}

	@Test
	public void testNextLink() {
		Network network = NetworkUtils.createNetwork();
		NextLinkCheckAgent agent = new NextLinkCheckAgent();
		SimulatorUtils.simulate( network , agent , 3 );

		if ( agent.nNextLink < 2 ) {
			throw new RuntimeException( "not enough links checked. Probably a problem with the network generated by NetworkUtils" );
		}
	}

	@Test
	public void testDistance() {
		Network network = NetworkUtils.createNetwork();

		for ( int start = 0; start < 5; start++ ) {
			FirstLinkAgent agent = new FirstLinkAgent();
			double actual = SimulatorUtils.simulate( network , agent , start );

			Assert.assertEquals(
					"Unexpected traveled distance returned by simulate",
					agent.dist,
					actual,
					1E-9);
		}

	}

	private static class NextLinkCheckAgent implements Agent {
		private Link lastLink;
		private int nNextLink = 0;

		public void start( final Link link ) {
			lastLink = link;
		}

		public Link selectNextLink( final Link[] links ) {
			nNextLink++;
			Assert.assertArrayEquals(
					"unexpected choice of links",
					lastLink.getEndNode().getOutLinks(),
					links );
			lastLink = links.length == 0 || nNextLink == 3 ? null : links[ 0 ];
			return lastLink;
		}

		public void stop() {}
	}

	private static class CounterAgent implements Agent {
		int nStart = 0;
		int nStop = 0;

		public void start( final Link link ) {
			nStart++;
		}

		public Link selectNextLink( final Link[] links ) {
			return null;
		}

		public void stop() {
			nStop++;
		}
	}

	private static class FirstLinkAgent implements Agent {
		double dist = 0;
		int timeToAbort = 15;

		public void start( final Link link ) {
			dist = link.getLength();
			timeToAbort = 15;
		}

		public Link selectNextLink( final Link[] links ) {
			if ( links.length == 0 ) return null;
			if ( timeToAbort-- == 0 ) return null;
			dist += links[0].getLength();
			return links[0];
		}

		public void stop() {
		}
	}
}
