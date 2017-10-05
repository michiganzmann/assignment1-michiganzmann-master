package ch.ethz.ivt.abmt.assignment1.ex2agent;

import ch.ethz.ivt.abmt.assignment1.ex1network.Link;
import ch.ethz.ivt.abmt.assignment1.ex1network.Node;

/**
 * @author thibautd
 */
public class LongestLinkAgent implements Agent {
	// Implements an Agent, who always chooses the longest Link as next Link
	
	private int linksTaken;
	Link actualLink;			// saves always the Link where the Agent travels
	
	public void start( Link startLink ) {
		// Agent starts on startLink
		actualLink = startLink;
		linksTaken = 0;
	}
	
	public Link selectNextLink( Link[] links ) {
		// Agent should choose the longest Link out of links
		double maxLength = -1;
		int nextLink = -1;
		linksTaken ++;
		
		// go throw all Links and checks if the length of the current Link
		// is longer then the actual longest Link
		// nextLink is saving the index of the actual longest Link in the Loop
		for (int i = 0; i < links.length; i++) {
			if (links[i].getLength() > maxLength) {
				maxLength = links[i].getLength();
				nextLink = i;
			}
		}
		if (nextLink == -1 || linksTaken == 11) {
			// If the Agent arrives to a dead end or took 10 Links, he stops
			return null;
		}
		else {
			// returns the Link that the agent tooks next (longest)
			actualLink = links[nextLink];
			return actualLink;
		}
	}
	
	public void stop() {
		// agent stops, resets all parameter
		linksTaken = 0;
		actualLink = null;
	}
	
}
