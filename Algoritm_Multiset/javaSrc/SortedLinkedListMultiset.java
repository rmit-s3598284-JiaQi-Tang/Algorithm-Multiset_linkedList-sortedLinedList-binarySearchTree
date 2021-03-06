import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends Multiset<T> {
	public class Node {
		public T item;
		public int number;
		public Node nextNode;

		public Node(T item, int number) {
			this.item = item;
			this.number = number;
		}
	}

	Node headNode;

	public SortedLinkedListMultiset() {
		headNode = new Node(null, 0);
	}

	public void add(T item) {
		Node currentNode = headNode;
		if (search(item) >= 1) {
			while (currentNode != null) {
				if (currentNode.item != null) {
					if (((String) currentNode.item).equals((String) item)) {
						currentNode.number++;
					}
				}
				currentNode = currentNode.nextNode;
			}
		} else {
			if (currentNode.nextNode != null) {
				boolean added = false;
				while (currentNode.nextNode != null) {

					if (((String) item).compareTo((String) currentNode.nextNode.item) < 0) {
						Node newNode = new Node(item, 1);
						newNode.nextNode = currentNode.nextNode;
						currentNode.nextNode = newNode;
						added = true;
						break;
					}
					currentNode = currentNode.nextNode;
				}
				if (!added) {
					currentNode.nextNode = new Node(item, 1);
				}

			} else {
				currentNode.nextNode = new Node(item, 1);
			}
		}
	}

	public int search(T item) {
		Node currentNode = headNode;
		while (currentNode != null) {
			if (currentNode.item != null) {
				if (((String) currentNode.item).equals((String) item)) {
					return currentNode.number;
				}
			}
			currentNode = currentNode.nextNode;
		}
		return 0;
	}

	public void removeOne(T item) {
		Node currentNode = headNode;
		while (currentNode != null) {
			if (currentNode.nextNode != null) {
				if (((String) currentNode.nextNode.item).equals((String) item)) {
					if (search(item) > 1) {
						currentNode.nextNode.number--;
						break;
					} else {
						currentNode.nextNode = currentNode.nextNode.nextNode;
						break;
					}
				}
			}
			currentNode = currentNode.nextNode;
		}
	}

	public void removeAll(T item) {
		Node currentNode = headNode;
		while (currentNode.nextNode != null) {
			if (((String) currentNode.nextNode.item).equals((String) item)) {
				currentNode.nextNode = currentNode.nextNode.nextNode;
			} else {
				currentNode = currentNode.nextNode;
			}
		}
	}

	public void print(PrintStream out) {
		Node currentNode = headNode;
		while (currentNode != null) {
			if (currentNode.item != null) {
				System.out.println(currentNode.item + " | " + currentNode.number);
			}
			currentNode = currentNode.nextNode;
		}
	}
}