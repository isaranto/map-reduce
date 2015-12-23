package server;

import java.util.ArrayList;

import mapper.Mapper;

public class MapperList {
	private ArrayList<Node<Mapper>> nodes;

	public MapperList() {
		this.nodes = new ArrayList<Node<Mapper>>();
	}

	public MapperList(ArrayList<Node<Mapper>> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node<Mapper> n) {
		nodes.add(n);
	}

	public ArrayList<Node<Mapper>> getAvailables() {
		// get available Nodes from NodeList
		ArrayList<Node<Mapper>> avail = new ArrayList<Node<Mapper>>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).isAvailable()) {
				avail.add(nodes.get(i));
			}
		}
		return avail;
	}

	public Node getNode(int index) {
		return nodes.get(index);
	}

	public ArrayList<Node<Mapper>> getNodes() {
		return this.nodes;
	}

}
