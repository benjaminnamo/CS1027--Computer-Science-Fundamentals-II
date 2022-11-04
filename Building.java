/**
 * This class represents a building structure to be placed within the city.
 * 
 * @author Benjamin Namo CS1027
 *
 */
public class Building {	//initializing variables

	private String symbol;
	private int width;
	private int length;
/**
 * Constructor that builds building object
 * 
 * @param symbol symbol representing the type of building
 * @param width width of building
 * @param length length of building
 */
	public Building(String symbol, int width, int length) {
		
		this.symbol = symbol;	//initializing each instance variable with corresponding parameter
		this.width = width;
		this.length = length;
	}
	
	public int getWidth() {	//function to get width of building
		return width;
	}
	
	public int getLength() { //function to get length of building
		return length;
	}
	
	public String toString() { //function to get symbol of building
		return symbol;
	}
	/**
	 * Function to determine weather or not building can be placed in given position in city.
	 * 
	 * @param array the City Array in which the building will be placed
	 * @param x the x coordinate of the building
	 * @param y the y coordinate of the building
	 * @return returns true if building can be placed
	 */
	public boolean isValidPlacement(Building [][] array, int x , int y) {
		
		int rows = array.length;	//find length/rows of city array
		int columns = array[0].length;	//find width/columns of city array
		
		if (x < 0 || x + width > columns || y < 0 || y + length > rows) //if placement is not within bounds return false
			return false;
    
		for (int i = x; i < x + width; i++) { //checking to see if other building is present in placement
			for (int j = y; j < y + length; j++) {
				if (array[j][i] instanceof Building) //if other building is present, return false
                return false;
			}
		 }
    return true; //if all rules are passed, return true

	}
}

