package hw5;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	
	private static class Node<E>{
	//data fields
		public E data; //key for the search
		public int priority; //random heap priority
		public Node<E> left; // points to left leaf
		public Node<E> right; // points to right leaf
		/*
		 * constructs new Node 
		 */
		//constructor
		public Node(E data, int priority) {
			if(data == null) {
				throw new IllegalArgumentException("null");
			}
			this.data = data;
			this.priority = priority;
			left = null;
			right = null;
		}
		//rotators
		/*
		 * Rotates tree to the Right
		 */
		Node<E> rotateRight(){
			Node<E> fixRight = this.left.right;
			Node<E> newHead = this.left;
			this.left.right = this;
			this.left = fixRight;
			return newHead;
		}
		/*
		 * Rotates tree to the Left
		 */
		Node<E> rotateLeft(){
			Node<E> fixLeft = this.right.left;
			Node<E> newHead = this.right;
			this.right.left = this;
			this.right = fixLeft;
			return newHead;
		}
		
		/*
		 * Returns the Node's data and priority values
		 */
		public String toString() {
			return "(key = " + data.toString() + ", priority = " + priority + ")";
		}
	}
	
	//instance variables
	private Random priorityGenerator;
	private Node<E> root;
	
	//constructors
	/*
	 * makes new Treap object
	 */
	public Treap() {
		priorityGenerator = new Random();
		root = null;
	}
	
	/*
	 * Creates a new Treap from seed
	 */
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root = null;
	}
	
	/*
	 * adds a new node with a random priority
	 * returns true if it is added, false if it fails
	 */
	boolean add(E key) {
	return add(key, this.priorityGenerator.nextInt());
	}
	
	/*
	 * Adds a node based off of its key with a set priority
	 */
	boolean add(E key, int priority) {	
		Node<E> newNode = new Node<>(key, priority);
		Stack<Node<E>> heritage = new Stack<>();
		Node<E> current = root;
		Node<E> check = current;
		while(current != null) {
			check = current;
			if(key.compareTo(current.data) < 0) {
				heritage.add(current);
				current = current.left;
			} else if(key.compareTo(current.data) > 0) {
				heritage.add(current);
				current = current.left;
			} else {
				return false;
			}
		}
		if(check == null) {
			root = newNode;
			return true;
		} else if(key.compareTo(check.data) > 0) {
			check.right = newNode;
			reheap(newNode, heritage);
		} else {
			check.left = newNode;
			reheap(newNode, heritage);
		}
		return true;
	}
	
	/*
	 * Resorts array after addition or deletion
	 */
	public void reheap(Node<E> current, Stack<Node<E>> heritage) {
		Node<E> parent = heritage.pop();
		while(current.priority > parent.priority) {
			if(heritage.isEmpty()) {
				if(current.equals(parent.right)) {
					this.root = parent.rotateLeft();
					break;
				} else {
					this.root = parent.rotateRight();
					break;
				}
			} else {
			Node<E> grandparent = heritage.pop();
			if(parent.left != null && parent.left.equals(current)) {
				if(parent.data.compareTo(grandparent.data) < 0) {
					grandparent.left = parent.rotateRight();
				} else {
					grandparent.right = parent.rotateRight();
				}
			} else {
				if(parent.data.compareTo(grandparent.data) < 0) {
					grandparent.left = parent.rotateLeft();
				} else {
					grandparent.right = parent.rotateLeft();
				}
			}
			parent = grandparent;
			}
		}
	}
	
	/*
	 * removes a node based off of its key
	 * returns true if node is deleted, returns false if it fails
	 */
	boolean delete (E key) {
	if(!find(key)) {
		return false;
	} else {
	root = deleteHelper(key, root);
	return true;
		}
	}
	
	/*
	 * This helper gets the node that is to be deleted into range, then removes it
	 */
	private Node<E> deleteHelper(E key, Node<E> current){
		if (current == null) {
			return current;
		} else {
			if (current.data.compareTo(key) < 0) {
				current.right = deleteHelper(key, current.right);
			} else {
				if (current.data.compareTo(key) > 0) {
					current.left = deleteHelper(key, current.left);
				} else {
					if (current.right == null) {
						current = current.left;
					} else if (current.left == null) {
						current = current.right;
					} else {
						if (current.right.priority < current.left.priority) {
							current = current.rotateRight();
							current.right = deleteHelper(key, current.right);
						} else {
							current = current.rotateLeft();
							current.left = deleteHelper(key, current.left);
						}
					}
				}
			}
		}
		return current;
	}
	
	/*
	 * finds a Node from a established starting point
	 */
	private boolean find(Node<E> root, E key) {
		Node<E> current = root;
		if(root == null) {
			return false;
		}
		while (current != null && current.data.compareTo(key) != 0) {
			if (key.compareTo(current.data) < 0) {
				current = current.left;
				} else if (key.compareTo(current.data) > 0) {
					current = current.right;
					}
	     }
		if(current == null) {
			return false;
		} else if(current.data.compareTo(key) == 0) {
			return true;
		}
		return false;
	}
	
	/*
	 * finds a node with a specific key
	 * if node is found, returns true, else it returns false
	 */
	public boolean find(E key) {
	return find(root, key);
	}
	
	/*
	 * Prints out a string representation of the Treap
	 * this specific toString() will be called recursively by the "empty" toString()
	 */
	public String toString(Node<E> current, int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<depth;i++) {
			sb.append("\t");
		}
		if (current==null) {
			sb.append("null\n");
		} else {
			sb.append(current.toString()+"\n");
			sb.append(toString(current.left, depth+1));
			sb.append(toString(current.right,depth+1));
		}
		return sb.toString();
	}
	
	/*
	 * This toString() is the starting function that begins a Treap being printed out
	 */
	public String toString() {
		return toString(root, 0);
	}
	
	public static void main(String[] args) {
		Treap<Integer> test1 = new Treap<Integer>(10);
		//System.out.println(test1.toString());
		//test1.add(5,9);
		//test1.add(2,11);
		//test1.delete(5);
		System.out.println(test1.find(1));
	}
}
