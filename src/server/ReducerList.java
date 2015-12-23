package server;

import java.util.ArrayList;

import reducer.Reducer;

public class ReducerList {
	private ArrayList<Node<Reducer>> nodes;

	public ReducerList() {
		this.nodes = new ArrayList<Node<Reducer>>();
	}

	public ReducerList(ArrayList<Node<Reducer>> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node<Reducer> n) {
		nodes.add(n);
	}

	public ArrayList<Node<Reducer>> getAvailables() {
		// get available Nodes from NodeList
		ArrayList<Node<Reducer>> avail = new ArrayList<Node<Reducer>>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).isAvailable()) {
				avail.add(nodes.get(i));
			}
		}
		return avail;
	}

	public Node<Reducer> getNode(int index) {
		return nodes.get(index);
	}

	public ArrayList<Node<Reducer>> getNodes() {
		return this.nodes;
	}

}
