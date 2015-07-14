package project4;

public class Node {

	Word data;
	Node left, right;

	public Node(Word data) {
		this.data = data;
	}

	public Word getData() {
		return data;
	}

	public void setData(Word data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node: " + data; 
	}
	
} //end Node
