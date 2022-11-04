/**
 * This class represents a sub-class of Building, representing a Skyscraper building.
 * 
 * @author Benjamin Namo CS1027
 *
 */
public class Skyscraper extends Building {
	
	private int height;	// declaring new variable of height
	
/**
 * Constructor for Skyscraper building object with 4 parameters.
 * 
 * @param symbol symbol representing the type of building
 * @param width width of building
 * @param length length of building
 * @param height height of building
 */
	public Skyscraper(String symbol, int width, int length,int height) {

		super(symbol, width, length); // superconstructor since same parameters as superclass
		this.height = height; // new parameter, height, set to instance variable
	}
	
/**
 *  Function to determine weather or not Skyscraper can be placed in given position in city.
 *  Same as method in Building.java, however has new restriction of building height.
 */
	public boolean isValidPlacement(Building [][] arr, int x, int y) {

		boolean building = this.height<10 && this.height>=(this.getLength()* this.getWidth()); // creating boolean using new rules
	
		building = building && super.isValidPlacement(arr, x, y); // using previous isValidPlacement

		return building; // returning boolean
	}
	
/**
 * Overriding the superclass' toString() by returning the height of skyscraper.
 * 
 * @return string height of skyscraper
 */
	public String toString()

	{

		return Integer.toString(height);

	}

  

}