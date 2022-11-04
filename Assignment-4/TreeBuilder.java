/**
 * 
 * This class is used to create the binary trees for the program using a queue-based approach.
 * 
 * @author Benjamin Namo CS1027
 *
 */

public class TreeBuilder<T> {
	
	private LinkedBinaryTree<T> tree;

	/**
	 * Constructor that take in a T array input parameter which contains the set of values with which to
	 * insert in the new tree's nodes.
	 * 
	 * @param array array containing the set of values used for the tree's nodes
	 */
	public TreeBuilder(T array[]) {
		
		
		LinkedQueue<T> dataQueue = new LinkedQueue<T>(); //initialize dataQueue with all elements (from T[ ] parameter) to be added in the nodes in order
		
		LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<BinaryTreeNode<T>>(); //initialize parentQueue as empty queue
		
		for (int i = 0; i < array.length; i++) { //enqueueing elements of array onto dataQueue
			dataQueue.enqueue(array[i]); 
		}
		
		BinaryTreeNode<T> root = new BinaryTreeNode<T>(dataQueue.dequeue());	//creating the tree's root node with the first element of the data queue

		parentQueue.enqueue(root);	//enqueueing root onto parentQueue
		
		while (!dataQueue.isEmpty()) {	//while dataQueue has elements
			
			T a = dataQueue.dequeue();	//dequeue a
			
			T b = dataQueue.dequeue();	//dequeue b
			
			BinaryTreeNode<T> parent = parentQueue.dequeue(); //dequeueing parentQueue gives parent
			
			if (!(a == null)) { //if a is not null
				
				BinaryTreeNode<T> node = new BinaryTreeNode<T>(a); //create node using a as data
				
				parent.setLeft(node); //set parent left as the node
				
				parentQueue.enqueue(node); //enqueue the node onto parentQueue
			}
			
			if (!(b == null)) {	//if b is not null
				
				BinaryTreeNode<T> node = new BinaryTreeNode<T>(b);  //create node using b as data
				
				parent.setRight(node); //set parent right as the node
				
				parentQueue.enqueue(node);	//enqueue the node onto parentQueue
			}
		}

		LinkedBinaryTree <T> newTree = new LinkedBinaryTree <T> (root); //Initializing the LinkedBinaryTree with the root from above
		
		tree = newTree;	//assigning the created LinkedBinaryTree to the tree variable
	}
	
	/**
	 * 
	 * A method to return the tree
	 * 
	 * @return tree the created Linked Binary Tree
	 */
	public LinkedBinaryTree<T> getTree() {
		
		return this.tree;
	}
	
	
}