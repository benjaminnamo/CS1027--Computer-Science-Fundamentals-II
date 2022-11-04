/**
 * 
 * This class represents Sudoku as an array of linked data structures. 
 * 
 * @author Benjamin Namo
 */
public class Sudoku {

	private LinearNode <Integer>[] board;	//declaring board array

	
	@SuppressWarnings("unchecked")
	public Sudoku(int[][] array) {
	
		board = new LinearNode[9]; //initializing board with 9 LinearNode slots
		
		for (int i = 0; i < board.length; i++) {	//looping through 9 slots of board
			board[i] = new LinearNode <Integer> (array[i][0]); //initializing boards 9 slots with values from column 1 of array

			LinearNode <Integer> firstNode = board[i]; // leftmost pointer
			
			LinearNode <Integer> currentNode; //current pointer
			
			for (int j = 1; j < array.length; j++) { //looping through array
				currentNode = new LinearNode<Integer> (array[i][j]); //creating node and adding in 2D values from array
				firstNode.setNext(currentNode); //link nodes
				firstNode = currentNode; //set firstNode pointer to currentNode
			}
		}
	}
		
public boolean isValidRow(int row) {
		
		LinearNode<Integer> currentNode = board[row - 1]; // beginning pointer
		UniqueArray<Integer> tempArray = new UniqueArray<Integer>(); // creating unique array
		
		while (currentNode != null) {
			
			if (currentNode.getElement() >= 1 && currentNode.getElement() <= 9) { //checking to see if number is valid
				
				tempArray.addItem(currentNode.getElement()); //if number is valid, appending node number to unique array using addItem which checks if it already exists
				currentNode = currentNode.getNext(); //change pointer
			}
			
			else {
				
				return false; // if number is not valid, return false
			}
		}
		
		if (tempArray.getNumElements() == 9) { //checking to see if there is 9 elements (1-9), returns true if there is
			return true;
		}
		
		return false;

	}
	public boolean isValidCol(int col) {
		
		UniqueArray<Integer> tempArray = new UniqueArray<Integer>(); //creating unique array
		
		for (int row = 0; row < board.length; row++) { //check all rows
			LinearNode<Integer> current = board[row]; //start at leftmost node
			
			for (int i = 0; i < col - 1;i++) { //iterate until desired column
				current = current.getNext(); //adjust pointer
			}
			
			
			if (current.getElement() >= 1 || current.getElement() <= 9) { //check if value is between 1-9
				tempArray.addItem(current.getElement()); //if it is, add current value to temparray
			} else {
				
				return false; // if it isn't, return false
			}

		}
		
		if (tempArray.getNumElements() == 9) { //checking to see if there is 9 elements (1-9), returns true if there is
			return true;
			
		}
		
		return false;
	}

	public boolean isValidBox(int row, int col) {
		
		row = row - 1; //adjusting row and column for 0-8
		col = col - 1;
		
		int count = 0; //counter
		
		UniqueArray<Integer> tempArray = new UniqueArray<Integer>(); //creating unique array

		while (count < 3) { //while loop that goes 3 times (counter less than 3)
			
			LinearNode<Integer> current = board[row]; //beginning pointer
			
			for (int i = 0; i < col; i++) { //iterate through indexes until before desired slot
				current = current.getNext();
			}
			
			for (int j = 0; j < 3; j++) { //iterate through right 3 slots
	
				if (current.getElement() >= 1 && current.getElement() <= 9) { // if the column value (element) doesn't follow the sudoku's rules then return false
					
					tempArray.addItem(current.getElement());	//add into test array
					current = current.getNext();	//adjust pointer
				}
				else {
					
					return false;
				}
			}
			
			row++; //incrementing row and count
			count++;
		}
		
		if (tempArray.getNumElements() == 9) { //checking to see if there is 9 elements (1-9), returns true if there is
			return true;
		}
		return false;
		
	}

	public boolean isValidSolution() {
		
		for (int i = 1; i <= 9; i++) { //go through rows
			
			if (isValidRow(i) == false) { //if row is false, return false
				return false;
			}
		}
		
		for (int j = 1; j <= 9; j++) {  // go through columns
			
			if (isValidCol(j) == false) { // if column is false, return false
				return false;
			}
		}
		
		for (int row = 1; row <= 7; row = row + 3) {	//go through 3x3 boxes (rows)
			
			for (int col = 1; col <= 7; col = col + 3) {	//3x3 boxes (columns)

				if (isValidBox(row, col) == false) { 	//if box is false, return false.
					return false;
				}
			}
		}
		
		return true;
	}

}