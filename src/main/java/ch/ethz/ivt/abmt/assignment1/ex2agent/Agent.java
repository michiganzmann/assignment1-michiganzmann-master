package ch.ethz.ivt.abmt.assignment1.ex2agent;

import ch.ethz.ivt.abmt.assignment1.ex1network.Link;

/**
 * @author thibautd
 */
public interface Agent {
	// Interface of Agent (can start, go to a next Link or stop
	void start( Link link );				// link is the first Link the agent takes
	Link selectNextLink( Link[] links );	// links are all possible Links he can take from it's position
	void stop();							// agent stops
}
