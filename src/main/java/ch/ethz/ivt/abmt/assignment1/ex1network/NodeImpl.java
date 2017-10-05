package ch.ethz.ivt.abmt.assignment1.ex1network;

/**
 * @author thibautd
 */
public class NodeImpl implements Node {
	// Implements Node
	
	private final int id;
	private final Link[] inLinks;
	private final Link[] outLinks;
	
	public NodeImpl( int id, LinkImpl[] inLinks , LinkImpl[] outLinks ) {
		// constructor
		this.id = id;
		this.inLinks = inLinks;
		this.outLinks = outLinks;
		
		// sets the EndNode of all inLinks
		for ( int i = 0; i < inLinks.length; i++) {
			inLinks[i].setEndNode(this);
		}
		// sets the StartNode of al outLinks
		for (int i =0; i < outLinks.length; i++) {
			outLinks[i].setStartNode(this);
		}
	}
	
	// methods of Node to get Informations
	public Link[] getInLinks() { return inLinks; }
	public Link[] getOutLinks() { return outLinks; }
	public int getId() { return id; }
	
	@Override // Overrides toString of Node
	public String toString() {
		return "Node Nr. "+ this.id;
	}
}
