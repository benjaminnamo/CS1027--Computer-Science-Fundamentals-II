/**
 * This class represents the new city that is being developed in a 2D array
 * 
 * @author Benjamin Namo CS1027
 *
 */

public class City {	//initializing variables

private int width;
private int length;
private Building[][] layout;

/**
 * Constructor taking in parameters for x and y and assigning them to instance variables.
 * 
 * @param width the columns of the city array
 * @param length the rows of the city array
 */

public City (int width, int length) {
	
	this.length = length;
	this.width = width;
	layout = new Building[length][width];	//initializing 2d array using length and width as array size/dimensions
}
/**
 * Function that places building in city array.
 * 
 * @param x the x position value of structure
 * @param y the y position value of structure
 * @param building the building object that we are attempting to add  to the city at x,y position
 * @return if isValid, we may place the building object at location
 */
public boolean addStructure (int x, int y, Building building) {
	boolean isValid = building.isValidPlacement(layout,x,y); //calling isValidPlacement method to check if placement of building is valid
	if (isValid) {
		for (int i = x; i < x + building.getWidth(); i++) { // if valid, loop through all cells in which building will be constructed
            for (int j = y; j < y + building.getLength(); j++) {
                layout[j][i] = building; //point those cells to object
            }
        }
    }
    return isValid;
	}
	
/**
 * Method that converts city array and building placements into a visual grid representation.
 * 
 * @return Returns a visual grid representation of City.
 */
public String toString(){
	
    StringBuilder result = new StringBuilder(); //creating a mutable sequence of string, whereas string itself is immutable

    for(int i = 0; i < length; i++){	//loop through 2d array
        for(int j = 0; j < width; j++){
            if(layout[i][j] != null){	
                result.append(layout[i][j].toString()).append("  ");	//if position is not empty, append building symbol followed by spaces
            }
            else {

               result.append("." + "  ");	//if position is empty, append period followed by two spaces
            }
        }
        if(i != length - 1) // new line once array width is reached
        result.append("\n");
    }

    return result.toString(); // returning final grid representation
}

}
