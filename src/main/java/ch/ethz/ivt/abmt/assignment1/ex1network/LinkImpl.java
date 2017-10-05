package ch.ethz.ivt.abmt.assignment1.ex1network;

/**
 * @author thibautd
 */
public class LinkImpl implements Link{
	// implements properties of Link
	
	private final int id;
	private final double length;
	private Node endNode;		// not in constructor
	private Node startNode;		// not in constructor
	
	public LinkImpl( int id, double length ) {
		// constructor (without endNode/startnode)
		this.id = id;
		this.length = length;
	}
	
	// Set startNode and endNode
	public void setEndNode( Node endNode ) { this.endNode = endNode; }
	public void setStartNode( Node startNode ) { this.startNode = startNode; }
	
	// Methods from Link to get the Information of Link
	public Node getStartNode() { return startNode; }
	public Node getEndNode() { return endNode; }
	public double getLength() { return length; }
	public int getId() { return id; }
	
	// Override of Link toString
	public String toString() {
		return "Link Nr. "+ this.getId();
	}

}
