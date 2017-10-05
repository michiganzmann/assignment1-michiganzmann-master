package ch.ethz.ivt.abmt.assignment1.ex2agent;

import ch.ethz.ivt.abmt.assignment1.ex1network.Link;
import ch.ethz.ivt.abmt.assignment1.ex1network.Node;


import java.util.Random;

/**
 * @author thibautd
 */
public class RandomAgent implements Agent {
	// Implements an agent, who chooses always a random Link until he ends at a dead End
	
	private Link actualLink;
	Random r = new Random();	// Object Random
	
	public void start( Link startLink ) {
		// agent start on startLink
		actualLink = startLink;
	}
	
	public Link selectNextLink( Link[] links ) {
		
		// if the given links is empty, the Agent arrived to a dead end
		if (links.length == 0) {
			return null;
		}
		
		// otherwise he chooses one Link out of the given ones
		int nextLink = r.nextInt(links.length);
		actualLink = links[nextLink];
		return actualLink;
	}
	
	public void stop() {
		// agent stops
		actualLink = null;
	}

}
