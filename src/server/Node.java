package server;

public class Node<T> {
	private boolean available;
	private T NetworkNode;

	public Node(boolean available, T NetworkNode) {
		super();
		this.NetworkNode = NetworkNode;
		this.available = available;
	}

	public T getNetworkNode() {
		return NetworkNode;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setNetworkNode(T networkNode) {
		NetworkNode = networkNode;
	}

}
