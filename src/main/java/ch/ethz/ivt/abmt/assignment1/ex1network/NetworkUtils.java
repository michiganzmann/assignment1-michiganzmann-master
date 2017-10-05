package ch.ethz.ivt.abmt.assignment1.ex1network;


/**
 * @author thibautd
 */
public class NetworkUtils {
	private NetworkUtils() {}

	public static Network createNetwork() {
		// creates all Links and Nodes of the Network given in die assignment
		
		LinkImpl[] links = new LinkImpl[5];	// 5 Links: 0 to 4
		Node[] nodes = new Node[4];			// 4 Nodes: 0 to 3
		
		// creates Links and set the id and length the same as the index in "links[]"
		for ( int i = 0; i < links.length; i++ ) {
			links[i] = new LinkImpl(i, (double) i);
		}
		
		// creates the Nodes and sets startNode and endNode of each Link
		LinkImpl[] inLinks0 = {links[3]};
		LinkImpl[] outLinks0 = {links[0]};
		nodes[0] = new NodeImpl( 0, inLinks0 ,outLinks0);
		
		LinkImpl[] inLinks1 = {links[0]};
		LinkImpl[] outLinks1 = {links[1],links[2],links[3]};
		nodes[1] = new NodeImpl( 1, inLinks1 ,outLinks1);
		
		LinkImpl[] inLinks2 = {links[1],links[4]};
		LinkImpl[] outLinks2 = {};
		nodes[2] = new NodeImpl( 2, inLinks2 ,outLinks2);
		
		LinkImpl[] inLinks3 = {links[2]};
		LinkImpl[] outLinks3 = {links[4]};
		nodes[3] = new NodeImpl( 3, inLinks3 ,outLinks3);
		
		Link[] linksnet = (Link[])links;
		NetworkImpl networkimpl = new NetworkImpl( nodes , linksnet );
		Network network = (Network)networkimpl;
		
		// returns the created Network
		return network;
	}
}
