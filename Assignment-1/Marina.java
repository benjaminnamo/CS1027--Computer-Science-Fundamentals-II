/**
 *  This class represents a sub-class of Building, representing a Marina.
 * 
 * @author Benjamin Namo CS1027
 *
 */
public class Marina extends Building {

/**	
 *  Constructor that builds Marina object.
 * 
 * @param symbol symbol representing the type of building
 * @param width width of building
 * @param length length of building
 */
    public Marina(String symbol, int width, int length) {
        super(symbol, width, length);
    }
    
/**
 *  Function to determine weather or not Skyscraper can be placed in given position in city.
 *  Same as method in Building.java, however has new restriction of being at edge.
 * 
 */
    public boolean isValidPlacement(Building[][] array, int x, int y) {

        return super.isValidPlacement(array, x, y) && (y + getLength() == array.length || x + getWidth() == array[0].length || x ==0 || y == 0);

    }
}

