package ch.ethz.ivt.abmt.assignment1.ex3simulation;

import ch.ethz.ivt.abmt.assignment1.ex1network.Network;
import ch.ethz.ivt.abmt.assignment1.ex1network.NetworkUtils;
import ch.ethz.ivt.abmt.assignment1.ex2agent.Agent;
import ch.ethz.ivt.abmt.assignment1.ex2agent.LongestLinkAgent;
import ch.ethz.ivt.abmt.assignment1.ex2agent.RandomAgent;

/**
 * @author thibautd
 */
public class Simulation {
	// Class with to main method to start a Simulation
	
	public static void main( String[] args ) {
		
		Agent random = new RandomAgent();					// creates a RandomAgent
		Agent longestLink = new LongestLinkAgent();			// creates a LongestLinkAgent
		Network network = NetworkUtils.createNetwork();		// creates a Network (given in the assignment)
		
		// Simulation with the LongestLinkAgent, returns the total distance he travelled
		double distance1 = SimulatorUtils.simulate(network, longestLink, 0);
		System.out.println("The Agent travelled "+ distance1 +"km.");
		
		// Simulation with the RandomAgent, returns the total distance he travelled
		double distance2 = SimulatorUtils.simulate(network, random, 0);
		System.out.println("The Agent travelled " + distance2 + "km.");

	}
}
