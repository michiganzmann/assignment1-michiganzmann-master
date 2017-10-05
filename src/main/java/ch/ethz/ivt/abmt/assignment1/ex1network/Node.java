package ch.ethz.ivt.abmt.assignment1.ex1network;

/**
 * @author thibautd
 */
public interface Node {
	// Interface of Node
	Link[] getInLinks();
	Link[] getOutLinks();
	int getId();
}
