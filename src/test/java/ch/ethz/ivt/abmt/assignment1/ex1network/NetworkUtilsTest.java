package ch.ethz.ivt.abmt.assignment1.ex1network;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author thibautd
 */
public class NetworkUtilsTest {
	@Test
	public void testNetwork() {
		final Network net = NetworkUtils.createNetwork();

		Assert.assertEquals(
				"Unexpected number of nodes",
				4,
				net.getNodes().length );

		Assert.assertEquals(
				"Unexpected number of links",
				5,
				net.getLinks().length );

		assertInLinks( net , 0 , 3 );
		assertOutLinks( net , 0 , 0 );

		assertInLinks( net , 1 , 0 );
		assertOutLinks( net ,1 , 1 , 2 , 3 );

		assertInLinks( net , 2 , 1, 4);
		assertOutLinks( net , 2 );

		assertInLinks( net , 3 , 2 );
		assertOutLinks( net ,3 , 4 );

		assertStartEndNodes( net , 0 , 0 , 1 );
		assertStartEndNodes( net , 1 , 1 , 2 );
		assertStartEndNodes( net , 2 , 1 , 3 );
		assertStartEndNodes( net , 3 , 1 , 0 );
		assertStartEndNodes( net , 4 , 3 , 2 );
	}

	private void assertInLinks( Network net , int node , int... inLinks ) {
		Set<Link> actualLinks = new HashSet<Link>( Arrays.asList( net.getNodes()[ node ].getInLinks() ) );
		Set<Link> expectedLinks = new HashSet<Link>();

		for ( int l : inLinks ) {
			expectedLinks.add( net.getLinks()[ l ] );
		}

		Assert.assertEquals(
				"unexpected inLinks for node "+node,
				expectedLinks,
				actualLinks );
	}

	private void assertOutLinks( Network net , int node , int... outLinks ) {
		Set<Link> actualLinks = new HashSet<Link>( Arrays.asList( net.getNodes()[ node ].getOutLinks() ) );
		Set<Link> expectedLinks = new HashSet<Link>();

		for ( int l : outLinks ) {
			expectedLinks.add( net.getLinks()[ l ] );
		}

		Assert.assertEquals(
				"unexpected outLinks for node "+node,
				expectedLinks,
				actualLinks );
	}

	private void assertStartEndNodes( Network net , int link , int startNodeIndex , int endNodeIndex ) {
		Node startNode = net.getLinks()[ link ].getStartNode();
		Node endNode = net.getLinks()[ link ].getEndNode();

		Assert.assertSame(
				"unexpected start node for link "+link,
				net.getNodes()[ startNodeIndex ],
				startNode );

		Assert.assertSame(
				"unexpected start node for link "+link,
				net.getNodes()[ endNodeIndex ],
				endNode );
	}
}
