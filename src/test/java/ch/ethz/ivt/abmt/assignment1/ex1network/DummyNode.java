package ch.ethz.ivt.abmt.assignment1.ex1network;

/**
 * @author thibautd
 */
class DummyNode implements Node {

	public Link[] getInLinks() {
		return new Link[ 0 ];
	}

	public Link[] getOutLinks() {
		return new Link[ 0 ];
	}

	public int getId() {
		return 0;
	}
}
