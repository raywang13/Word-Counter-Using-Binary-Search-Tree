package project4;

public class BST {
	Node root = null;

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}


	//add node function: if the BST is empty, first node is added to the root.
	//Else if there tree is not empty, the new node will be compared to the current
	//node. If the new node is less than the current node, then the new node will
	//get down the left branch. If the new node is greater than the current node,
	//then the new node will go down the right branch. This will continue recursively
	//until it finds a null node, and that is where it will be added.
	public void add(Node newNode, Node rootNode) {
		Node current = rootNode;
		if(this.root == null) {
			this.root = newNode; 
			return;
		}
		
		else if(current.getData().compareTo(newNode.getData()) > 0) {
			if(current.getLeft() == null) {
				current.setLeft(newNode);
			}
			else {
				add(newNode, current.getLeft());
			}
		}
		
		else if(current.getData().compareTo(newNode.getData()) < 0) {
			if(current.getRight() == null) {
				current.setRight(newNode);
			}
			else {
				add(newNode, current.getRight());
			}
		}
		
		else if(current.getData().compareTo(newNode.getData()) == 0) {
			Word data = current.getData();
			int count = data.getWordCount();
			count++;
			data.setWordCount(count);
		}
	} //end add


	//searches the BST, if the target node is found, return true, otherwise,
	//go down the BST and search the children nodes. If the target is greater
	//than the current node, then search the right node. If the target is smaller
	//than the current node, search the left node. If the whole tree is searched
	//and node is not found, return false. 
	public boolean find(String target, Node current) {
		Node returnNode = null;
		if(current == null) {
			System.out.println("Node not found.");
			return false;
		}
		String currentData = current.getData().getaWord();
		if(currentData.equals(target.toLowerCase())) {
			System.out.println("Found node! " +current.getData());
			return true;
		}
		else if(currentData.compareTo(target.toLowerCase()) > 0) {
			find(target, current.getLeft());
		}
		else if(currentData.compareTo(target.toLowerCase()) < 0) {
			find(target, current.getRight());
		}
		return false;
	} //end find


	public Node findNode(String target, Node current) {
		Node returnNode = null;

		if(current == null) {
			System.out.println("Node not found.");
			returnNode = null;
			return returnNode;
		}
		String currentData = current.getData().getaWord();
		if(currentData.compareTo(target) == 0) {
			returnNode = current;
			return returnNode;
		}
		else if(currentData.compareTo(target.toLowerCase()) > 0) {
			returnNode = findNode(target, current.left);
		}
		else if(currentData.compareTo(target.toLowerCase()) < 0) {
			returnNode = findNode(target, current.right);
		}
		return returnNode;
	} //end findNode

	//this method traverses through the BST, it checks the left node to see if it matches
	//the target. if it does, it returns the current node. if not, then it checks the right
	//node to see if it matches the target. if it does, it returns the current node. if none
	//of the conditions are met, it goes down the left subtree and repeats. then right subtree
	//and repeats until target is found. 
	public Node findParent(String target, Node current) {
		Node returnNode = null; 
		if(current == null) {
			return returnNode;
		}
		//		System.out.println("**" + current + " L:" + current.getLeft() + " R:" + current.getRight());
		if(current.getData().getaWord().compareTo(target) == 0){
			//			System.out.println("Found it!!" + current);
			return current; 
		}

		if(current.getLeft() != null) {
			int compareResult = current.getData().getaWord().compareTo(target) ; 
			if(current.getLeft().getData().getaWord().compareTo(target) == 0) {
				return current;
			}
			else if(compareResult > 0) {
				returnNode = findParent(target, current.left);  /// changing to current.left was .right 
			}
			else if(compareResult < 0) {
				returnNode = findParent(target, current.right);				
			}
		}

		if(current.getRight() != null) {
			int compareRight = current.getData().getaWord().compareTo(target) ; 

			if(compareRight < 0) {
				returnNode = findParent(target, current.right);
			}
			if(compareRight > 0) {
				returnNode = findParent(target, current.left);
			}
			if(current.getRight().getData().getaWord().compareTo(target) == 0) {
				return current;
			}
		}

		return returnNode;
	} //end find parent


	//so long as getLeft() does not equal null, the method will recursively
	//call itself to keep getting left. if getLeft() is null, then the current
	//node is the minimum.
	public Node findMin(Node current) {
		if(current == null) {
			System.out.println("Root is null.");
			return null;
		}
		if(current.getLeft() == null) {
			return current;
		}
		else{
			return findMin(current.getLeft());
		}
	} //end find min


	//so long as getRight() does not equal null, the method will recursively
	//call itself to keep getting right. if getRight() is null, then the current
	//node is the maximum.
	public Node findMax(Node current) {
		if(current == null) {
			System.out.println("Root is null.");
			return null;
		}
		if(current.getRight() == null) {
			return current;
		}
		else {
			return findMax(current.getRight());
		}
	} //end find Max


	public void remove(String target, Node current) {
		Node removeNode = findNode(target, this.root);
		//		System.out.println("Remove node: " +removeNode.getData());
		//				System.out.println("Root: " +root);
		Node parentNode = findParent(removeNode.getData().getaWord(), root);
		//		System.out.println("Parent node:" +parentNode.getData());
		if(removeNode.getRight() == null && removeNode.getLeft() == null) {
			if(removeNode.getData().getaWord().compareTo(parentNode.getData().getaWord()) < 0) {
				//				System.out.println("no child left");
				parentNode.setLeft(null);
			}
			else if(removeNode.getData().getaWord().compareTo(parentNode.getData().getaWord()) > 0) {
				//				System.out.println("no child right");
				parentNode.setRight(null);
			}
		} //end remove if leaf node

		else if(removeNode.getRight() != null && removeNode.getLeft() != null) {
			Node minNode = findMin(removeNode.right);
			//			System.out.println("Min node: " +minNode);
			Node minParent = findParent(minNode.getData().getaWord(), root);
			//			System.out.println("Min parent: " +minParent);
			removeNode.setData(minNode.getData());
			//			System.out.println("removeNode: " +removeNode.getData());
			if(minNode.getData().getaWord().compareTo(minParent.getData().getaWord()) > 0) {
				//				System.out.println("minParent setting right: " +minParent.getRight());
				minParent.setRight(null);
			}
			if(minNode.getData().getaWord().compareTo(minParent.getData().getaWord()) < 0) {
				//				System.out.println("minParent setting left: " +minParent.getLeft());
				minParent.setLeft(null);
			}
			if(minNode.getData().getaWord().compareTo(minParent.getData().getaWord()) == 0) {
				//				System.out.println("equalsequalsequals");
				minParent.setRight(null);
			}
		} //end remove if two childs


		//begin remove if node has one child
		else if(removeNode.getData().getaWord().compareTo(parentNode.getData().getaWord()) > 0 && removeNode.getRight() != null) {
			//			System.out.println("setting right: " +removeNode.right);
			parentNode.right = removeNode.right;
		}
		else if(removeNode.getData().getaWord().compareTo(parentNode.getData().getaWord()) > 0 && removeNode.getLeft() != null) {
			//			System.out.println("setting left:" +removeNode.left);
			parentNode.right = removeNode.left;
		}

		else if(removeNode.getData().getaWord().compareTo(parentNode.getData().getaWord()) < 0 && removeNode.getRight() != null) {
			//			System.out.println("setting right: " +removeNode.right);
			parentNode.left = removeNode.right;
		}
		else if(removeNode.getData().getaWord().compareTo(parentNode.getData().getaWord()) < 0 && removeNode.getLeft() != null) {
			//			System.out.println("setting left:" +removeNode.left);
			parentNode.left = removeNode.left;
		} //end remove if node has one child


		else {
			System.out.println("Could not find node to be removed.");
		}
	} // end remove


	public void inOrder(Node current) {
		if(current == null) {
			return;
		}
		inOrder(current.getLeft());
//		if(current.getData().getWordCount() > 25 && current.getData().getaWord().length() > 4) {
			System.out.println(current.getData());
//		}
		inOrder(current.getRight());
	} //end inOrder


	public void preOrder(Node current) {
		if(current.getData().getWordCount() > 25 && current.getData().getaWord().length() > 4) {
			if(current == null) {
				return;
			}
			if(current.getData().getWordCount() > 25 && current.getData().getaWord().length() > 4) {
				System.out.println(current.getData());
			}
			preOrder(current.getLeft());
			preOrder(current.getRight());
		}
	} //end preOrder


	public void postOrder(Node current) {
		if(current.getData().getWordCount() > 25 && current.getData().getaWord().length() > 4) {
			if(current == null) {
				return;
			}
			postOrder(current.getLeft());
			postOrder(current.getRight());
			if(current.getData().getWordCount() > 25 && current.getData().getaWord().length() > 4) {
				System.out.println(current.getData());
			}
		}
	} //end postOrder
}