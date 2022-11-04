/**
 * This class represents a regular array with the requirement that duplicate
 * elements are not allowed.
 * 
 * @author Benjamin Namo
 *
 */

public class UniqueArray <T> {

	T[] array;	//declaring array and num variables.
	int num;
	
	@SuppressWarnings("unchecked")
	public UniqueArray() { //initializing array with length 5 and assigning num the value of 0.
		
		array = (T[]) new Object[5];
		this.num = 0;
	}
/**
 * 
 * A method to expand the capacity of an array by 5 slots.	
 * 
 */
	@SuppressWarnings("unchecked")
	private void expandCapacity() { //method to expand the capacity of the array by 5.
		
		T[] newArray = (T[]) (new Object [array.length + 5]); //creating new array that has 5 more slots than original
		for (int i = 0; i < array.length; i++) {  //copying over elements of original array
			newArray[i] = array[i];
		}
		array = newArray; //assigning newArray to array variable
	
	}
/**
 * A method to check if an element exists within an array and add it to the
 * array if it does not. If the array is full, this method also calls expandCapacity() to
 * create space.
 * 	
 * @param element
 */
	public void addItem (T element) {
		
		       for (int i = 0 ; i < array.length; i++) {
		           if (array[i] == element)
		               return;
		 
		       }
		       if (num < array.length) {
		       array[num]=element;
		       num++;
		       }
		       else {
		    	   expandCapacity();
		    	   array[num]=element;
			       num++;
		       }
		    
		       }
			
	
	public int getLength() {
	return array.length;
	}
	
	public int getNumElements() {
		int numElements = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				numElements += 1;
			}
		}
		return numElements;
	}
	
	public String toString() {
		
		String result = "";
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null && array[i+1] != null) {
				result += array[i] + ", ";
			}
			else if (array[i] != null && array[i+1] == null) {
				result += array[i];
				break;
			}

		}
		
		return result;
	}
}
