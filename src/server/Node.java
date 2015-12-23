package server;

public class Node {
	private boolean available;
	private String role;

	public Node(boolean available, String role) {
		super();
		this.available = available;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
