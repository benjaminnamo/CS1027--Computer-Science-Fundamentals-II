/**
 * This class represents a Stack implemented with an array data structure that works with generic type T.
 * 
 * @author Benjamin Namo CS1027
 *
 * 
 */
public class ArrayStack <T> implements StackADT <T> {

	private T[] array;
	
	private int top; 
	
	/**
	 * Constructor that initializes array with 10 slots as well as sets top equal to 9.
	 */
	public ArrayStack() {
		
		array = (T[])(new Object[10]); //creating stack with capacity 10
		top = 9; //top set to 9 
	}
	
	/**
	 * Constructor that takes in parameter N and initializes array with N slots as well as sets top equal to N-1.
	 * 
	 * @param N input parameter
	 */
	public ArrayStack (int N) {
		
		array = (T[])(new Object[N]); //creating stack with capacity of input parameter N
		top = N-1; //top set to input parameter N - 1
	}
	
	/**
	 * Method that takes in a data item and adds it to the top of stack. If stack is full, calls expandCapacity() function before adding the item.
	 * 
	 */
	@Override
	public void push(T dataItem) { //adding element to top of stack
		
		if (size() == array.length) { //checking to see if stack is full
			
			expandCapacity(); //if stack is full, call expandCapacity() function to increase capacity by 5
			top += 5;
		}
		
		array[top] = dataItem; //assign dataItem to top of stack
		top--; //increment top
	}
	
	/**
	 * Method that returns the top item in the stack while also removing it from the stack.
	 * 
	 */
	@Override
	public T pop() throws StackException {
		
		if (isEmpty()) { //	if stack is empty, throws a StackException
			
			throw new StackException ("Stack");
		}
		
		T dataItem = array[top + 1]; //assigning top of stack to dataItem
		array[top+1] = null; //removing element
		top++; //increasing top because we removed an element
		
		return dataItem;
		
		
	}
	/**
	 * Method that returns the top item in the stack without removing it.
	 * 
	 */
	@Override
	public T peek() throws StackException {
		
		if (isEmpty()) { //	if stack is empty, throws a StackException
			
			throw new StackException ("Stack");
		}
		
		T dataItem = array [top + 1];//assigning top of stack to dataItem
		
		return dataItem;
	}

	/**
	 * Method that returns true if stack is empty and false if stack is not empty.
	 * 
	 * @return Empty
	 */
	@Override
	public boolean isEmpty() {
		
		boolean empty = false;
		
		if (array.length - 1 == top) { //if the length of the array -1 is equal to top (empty spaces)
			empty = true; //returns true
		}
		
		return empty;
		
	}
	
	/**
	 * Method that returns the size of the array. 
	 * 
	 * @return size of array
	 */
	@Override
	public int size() {
		
		return array.length - (top + 1) ; //the size is the length of the array minus the amount of empty spaces plus 1
		
	}
	
	/**
	 * Method that returns the length of the array.
	 * 
	 * @return length of array
	 */
	public int getLength() {
		
		return array.length; //returns length of array
		
	}
	/**
	 * This method returns the value of top.
	 * 
	 * @return top
	 */
	public int getTop() {
		
		return top; // returns top
		
	}
	/**
	 * This method returns a string format of the stack.
	 * 
	 */
	public String toString () {
		
		String result = "Stack: ";
		
		if (isEmpty()) {	//if isEmpty() is true, prints a message
			result = "The stack is empty."; 
		}
		
		for (int i = (top + 1); i < array.length; i++) {
			
			if (i != (array.length - 1)){ // if next element is a valid element
				result += array[i] + ", "; //add element to result followed by comma
			}
			
			else {
				result += array[i] + "."; // if next element is not valid, add element followed by period
				break;
			}

		}
		
		return result;
	}
	
	/**
	 * This method expands the capacity of an array.
	 * 
	 */
	private void expandCapacity() { //method to expand the capacity of the array by 5.
		
		@SuppressWarnings("unchecked")
		
		T[] newArray = (T[]) (new Object [array.length + 5]); //creating new array that has 5 more slots than original
		
		for (int i = 0; i < array.length; i++) {  //copying over elements of original array
			newArray[i+5] = array[i]; //copying elements while leaving front empty
			
			
		}
		
	
		array = newArray; //assigning newArray to array variable
		
	}
	
	

}
