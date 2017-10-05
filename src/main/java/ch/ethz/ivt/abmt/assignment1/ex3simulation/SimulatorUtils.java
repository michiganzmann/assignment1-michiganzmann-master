package ch.ethz.ivt.abmt.assignment1.ex3simulation;

import ch.ethz.ivt.abmt.assignment1.ex1network.Link;
import ch.ethz.ivt.abmt.assignment1.ex1network.Network;
import ch.ethz.ivt.abmt.assignment1.ex2agent.Agent;

/**
 * @author thibautd
 */
public class SimulatorUtils {
	private SimulatorUtils() {}

	public static double simulate( Network network , Agent agent , int startLinkIndex ) {
		// simulates the way of an Agent, on a given Network, with a given type of Agent and a StartLink
		
		boolean travelling = true;								// gets false, if selectNextLink returns null
		Link actualLink = network.getLinks()[startLinkIndex];	// saves always the Link where the Agent actually is
		
		System.out.println("An Agent enters Link "+ actualLink);
		
		agent.start(actualLink);								// agent starts on the startLink
		double distance = 0;
		
		while (travelling == true) {
			// always select a new Link if he doesn't stop and counts the distance
			distance = distance + actualLink.getLength();
			actualLink = agent.selectNextLink(actualLink.getEndNode().getOutLinks());
			if (actualLink == null) {
				travelling = false;
				agent.stop();
			}
			else {
				System.out.println("The1 Agent enters Link "+ actualLink);
			}
		}
		return distance;
	}
}
