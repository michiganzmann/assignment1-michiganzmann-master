package ch.ethz.ivt.abmt.assignment1.ex1network;

/**
 * @author thibautd
 */
public class NetworkImpl implements Network {
	// Implements Network
	
	private final Link[] links;
	private final Node[] nodes;
	
	public NetworkImpl( Node[] nodes , Link[] links) {
		// constructor
		this.links = links;
		this.nodes = nodes;
		
	}
	
	// methods of Network to get Information
	public Node[] getNodes() { return nodes; }
	public Link[] getLinks() { return links; }
	
}
