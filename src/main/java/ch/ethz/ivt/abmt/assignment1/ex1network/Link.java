package ch.ethz.ivt.abmt.assignment1.ex1network;

/**
 * @author thibautd
 */
public interface Link {
	// Interface Link
	Node getStartNode();
	Node getEndNode();
	double getLength();
	int getId();
	
	@Override
	// Overrides Adress from toString into "Link Nr. [id]"
	public String toString();
}
