import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T> {
	public class Node {
		public T item;
		public int number;

		public Node rightChild;
		public Node leftChild;

		public Node(T item, int number) {
			this.item = item;
			this.number = number;
		}

	}

	Node root;

	public BstMultiset() {
		root = null;
	}

	public void add(T item) {
		if (search(item) != 0) {
			Node currentNode = root;
			while (!currentNode.item.equals(item)) {
				if (((String) item).compareTo((String) currentNode.item) < 0) {
					currentNode = currentNode.leftChild;
				} else {
					currentNode = currentNode.rightChild;
				}
			}
			currentNode.number++;
		} else {
			Node newNode = new Node(item, 1);
			if (root == null) {
				root = newNode;
			} else {
				Node currentNode = root;
				Node parent;
				while (true) {
					parent = currentNode;
					if (((String) item).compareTo((String) currentNode.item) < 0) {
						currentNode = currentNode.leftChild;
						if (currentNode == null) {
							parent.leftChild = newNode;
							return;
						}
					} else {
						currentNode = currentNode.rightChild;
						if (currentNode == null) {
							parent.rightChild = newNode;
							return;
						}
					}
				}
			}
		}

	}

	public int search(T item) {
		Node currentNode = root;
		if (currentNode != null) {
			while (!currentNode.item.equals(item)) {
				if (((String) item).compareTo((String) currentNode.item) < 0) {
					currentNode = currentNode.leftChild;
				} else {
					currentNode = currentNode.rightChild;
				}
				if (currentNode == null) {
					return 0;
				}
			}
			return currentNode.number;
		} else {
			return 0;
		}
	}

	public void removeOne(T item) {
		if (search(item) > 1) {
			Node currentNode = root;
			if (currentNode != null) {
				while (!currentNode.item.equals(item)) {
					if (((String) item).compareTo((String) currentNode.item) < 0) {
						currentNode = currentNode.leftChild;
					} else {
						currentNode = currentNode.rightChild;
					}
				}
				currentNode.number--;
			}
		} else {
			Node currentNode = root;
			Node parent = root;

			boolean isItALeftChild = true;
			try {
				while (!currentNode.item.equals(item)) {
					parent = currentNode;
					if (((String) item).compareTo((String) currentNode.item) < 0) {
						isItALeftChild = true;
						currentNode = currentNode.leftChild;
					} else {
						isItALeftChild = false;
						currentNode = currentNode.rightChild;
					}
				}
			} catch (NullPointerException e) {

			}
			try {
				if (currentNode.leftChild == null && currentNode.rightChild == null) {
					if (currentNode == root) {
						root = null;
					} else if (isItALeftChild) {
						parent.leftChild = null;
					} else {
						parent.rightChild = null;
					}
				}

				else if (currentNode.rightChild == null) {
					if (currentNode == root) {
						root = currentNode.leftChild;
					} else if (isItALeftChild) {
						parent.leftChild = currentNode.leftChild;
					} else {
						parent.rightChild = currentNode.leftChild;
					}
				} else if (currentNode.leftChild == null) {
					if (currentNode == root) {
						root = currentNode.rightChild;
					} else if (isItALeftChild) {
						parent.leftChild = currentNode.rightChild;
					} else {
						parent.rightChild = currentNode.rightChild;
					}
				} else {
					Node replacement = getReplacementNode(currentNode);
					if (currentNode == root) {
						Node tempNode = root.leftChild;
						root = replacement;
						replacement.leftChild = tempNode;
					} else if (isItALeftChild) {
						parent.leftChild = replacement;
					} else {
						parent.rightChild = replacement;
						replacement.leftChild = currentNode.leftChild;
					}
				}
			} catch (NullPointerException e) {

			}
		}
	}

	public void removeAll(T item) {
		Node currentNode = root;
		Node parent = root;

		boolean isItALeftChild = true;
		try {
			while (!currentNode.item.equals(item)) {
				parent = currentNode;
				if (((String) item).compareTo((String) currentNode.item) < 0) {
					isItALeftChild = true;
					currentNode = currentNode.leftChild;
				} else {
					isItALeftChild = false;
					currentNode = currentNode.rightChild;
				}
			}
		} catch (NullPointerException e) {

		}
		try {
			if (currentNode.leftChild == null && currentNode.rightChild == null) {
				if (currentNode == root) {
					root = null;
				} else if (isItALeftChild) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
			} else if (currentNode.rightChild == null) {
				if (currentNode == root) {
					root = currentNode.leftChild;
				} else if (isItALeftChild) {
					parent.leftChild = currentNode.leftChild;
				} else {
					parent.rightChild = currentNode.leftChild;
				}
			} else if (currentNode.leftChild == null) {
				if (currentNode == root) {
					root = currentNode.rightChild;
				} else if (isItALeftChild) {
					parent.leftChild = currentNode.rightChild;
				} else {
					parent.rightChild = currentNode.rightChild;
				}
			} else {
				Node replacement = getReplacementNode(currentNode);
				if (currentNode == root) {
					Node tempNode = root.leftChild;
					root = replacement;
					replacement.leftChild = tempNode;
				} else if (isItALeftChild) {
					parent.leftChild = replacement;
				} else {
					parent.rightChild = replacement;
					replacement.leftChild = currentNode.leftChild;
				}
			}
		} catch (NullPointerException e) {

		}
	}

	public void print(PrintStream out) {
		inOrderTraverseTree(root);
	}

	public void inOrderTraverseTree(Node currentNode) {
		if (currentNode != null) {
			inOrderTraverseTree(currentNode.leftChild);
			System.out.println(currentNode.item + " | " + currentNode.number);
			inOrderTraverseTree(currentNode.rightChild);
		}
	}

	public Node getReplacementNode(Node node) {
		Node parentNode = node;
		Node replacementNode = node;

		Node currentNode = node.rightChild;
		while (currentNode != null) {
			parentNode = replacementNode;
			replacementNode = currentNode;
			currentNode = currentNode.leftChild;
		}
		if (replacementNode != node.rightChild) {
			parentNode.leftChild = replacementNode.rightChild;
			replacementNode.rightChild = node.rightChild;
		}
		return replacementNode;
	}
}