/**
 * This class is the main heart of the program in which we are writing the algorithm that performs a
 * search on a given map file.
 * 
 * @author Benjamin Namo CS1027
 * 
 */
import java.io.IOException;

public class StartSearch {

	private Map map;	//initializing variables
	int energy = 0;
	
	/**
	 * This method takes in a mapFile and assigns it to variable map.
	 * 
	 * @param mapFile file containing map information.
	 */
	public StartSearch(String mapFile) {
		
		try {
			
			map = new Map(mapFile);	//assigning the map file to map.
		}
		
		catch(InvalidMapException e) {	//satisfying compiler errors
			
			System.out.println("Error: File does not exist. Please enter a valid file.");
		}
		
		catch (IOException e) {
	
			System.out.println("Error: File does not exist. Please enter a valid file.");
		}
	}
	
	/**
	 * 
	 * This method determines which cell should be traveled to next from the current cell.
	 * 
	 * @param cell current cell
	 * @return next best cell
	 */
	public MapCell bestCell (MapCell cell) {
		
		MapCell nextCell = null;
		
		try {
				
			if (cell.isStart()) {  //if cell is starting cell, it can go to any other cell.
						
				for (int i = 0; i <= 3; i++) { //iterate through 0,1,2,3 or North, East, South, West
						
					if (cell.getNeighbour(i) != null) {
						
						
						if (cell.getNeighbour(i).isCovid()) { //if neighbor cell is covid 					(this is an issue if an exit cell is iterated before a covid cell)
							
							nextCell = null; //nextCell is null so that current cell can be popped 
							
							i=-1; // i is set to -1 in order to break the iteration loop 
							
							break;
						}
						
						
						else if (cell.getNeighbour(i).isExit()&& !cell.getNeighbour(i).isMarkedInStack()) {	//if neighbor cell is exit, assign nextCell to it and return nextCell immediately since we have found the exit.
							
							nextCell = cell.getNeighbour(i);
							return nextCell;
						}
						
						else if (cell.getNeighbour(i).isDonut()&& !cell.getNeighbour(i).isMarkedInStack()) { //if neighbor cell is Donut and hasnt already been visited
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if (cell.getNeighbour(i).isCrossPath()&& !cell.getNeighbour(i).isMarkedInStack()) { //if neighbor cell is crosspath and hasnt already been visited
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if (cell.getNeighbour(i).isHorizontalPath() || cell.getNeighbour(i).isVerticalPath()&& !cell.getNeighbour(i).isMarkedInStack()) { //if neighbour cell is crosspath and hasnt already been visited
						
							nextCell=cell.getNeighbour(i);
					
						}
					}
				}
						
			}
					
			else if (cell.isDonut()) { //if cell is donut, can go anywhere. Same methods as start.
						
				for (int i = 0; i <= 3; i++) {
						
					if (cell.getNeighbour(i) != null) {
							
						if (cell.getNeighbour(i).isCovid()) {
								
								nextCell = null;
								
								i=-1;
								
								break;
						}
							
						
						else if (cell.getNeighbour(i).isExit()&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if (cell.getNeighbour(i).isDonut()&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if (cell.getNeighbour(i).isCrossPath()&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if ((cell.getNeighbour(i).isHorizontalPath() || cell.getNeighbour(i).isVerticalPath())&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
					
					}
				}
						
			}
					
			else if (cell.isCrossPath()) { //if cell is cross path, can go to any other cells, however horizontal cells must be to the sides (i=1 or i=3) and vertical to top and bottom (i=0 or i=2).
						
				for (int i = 3; i > -1; i--) {
						
					if (cell.getNeighbour(i) != null) {
							
						
						if (cell.getNeighbour(i).isCovid()) {
									
							nextCell = null;
							
							i=-1;
							
							break;
						}
								
						else if (cell.getNeighbour(i).isExit()&& !cell.getNeighbour(i).isMarkedInStack()) {
									
							nextCell = cell.getNeighbour(i);
								
							return nextCell;		
						}
						
						else if (cell.getNeighbour(i).isDonut()&& !cell.getNeighbour(i).isMarkedInStack() ) {
							
							nextCell = cell.getNeighbour(i);	
						}
						
						else if (cell.getNeighbour(i).isCrossPath() && !cell.getNeighbour(i).isMarked()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if (((cell.getNeighbour(i).isHorizontalPath()&& (i==1 || i==3)) || (cell.getNeighbour(i).isVerticalPath()&&(i==0 || i==2)))&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);		
						}
								
					}
				}
			}
				
			else if (cell.isHorizontalPath() || cell.isVerticalPath()) { //horizontal paths and vertical paths can also go anywhere with same restrictions on other horizontal and vertical paths
					
				for (int i = 0; i <= 3; i++) {
						
					if (cell.getNeighbour(i) != null) {
						
						if (cell.getNeighbour(i).isCovid()) {
							
							nextCell = null;
							
							i=-1;
							
							break;
						}
						
						else if (cell.getNeighbour(i).isExit()&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
							return nextCell;
						}
		
						else if (cell.getNeighbour(i).isDonut()&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if (cell.getNeighbour(i).isCrossPath()&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if ((cell.getNeighbour(i).isVerticalPath() && (i==0 || i==2))&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
						else if ((cell.getNeighbour(i).isHorizontalPath() && (i==1 || i==3))&& !cell.getNeighbour(i).isMarkedInStack()) {
							
							nextCell = cell.getNeighbour(i);
						}
						
					}
						
				}
				
			}
			

		}
			
		
		catch (IllegalArgumentException e) { //catching exceptions
			
			System.out.println("");
		}
		
		catch (InvalidNeighbourIndexException e) {
			
			System.out.println("");
		}
		
		return nextCell; //returning nextcell
		
	}
	
	/**
	 * This method finds the most efficient pathway to the destination from the start while keeping track of all steps.
	 * 
	 * @return actionString string format of all steps taken
	 */
	public String findPath() {
	
		ArrayStack<MapCell> stack = new ArrayStack<MapCell>(); //creating new array
		
		String actionString = "";	//Initializing actionString
		
		boolean destinationFound;	//Initializing destinationFound boolean
		
		MapCell currentCell = map.getStart();	//finding beginning cell using MapCell's getStart() method. Assigning this cell to currentCell.
		
		stack.push(currentCell);	//adding this cell to top of stack.
		
		actionString += currentCell;	//adding this cell to actionString
		
		currentCell.markInStack();	//marking cell as in stack
		
		currentCell.markInitial();	//marking cell as initial
		
		energy = 10; //giving energy a value of 10
		
		while (!stack.isEmpty() && !currentCell.isExit()) { //while the stack is not empty and the current cell is not the exit
			
			currentCell = stack.peek();	//current cell is the topmost cell in the stack
			
			MapCell next = bestCell(currentCell); //using bestCell method and currentCell as input, find next best cell.
		
			if (next != null && energy > 0 && !next.isMarkedOutStack()) {
				
				if (next.isExit()){	//if the next cell is the exit, destination has been found.
					
					destinationFound = true;
					energy -= 1; //remove energy since we wont be doing so at the end of the loop (breaking)
					break;
					
					
				}
				
				if (next.isDonut()) { //if next cell is a donut, add 3 energy
					
					energy += 3;
				}
				
				stack.push(next);	//add next to stack
				next.markInStack();	//mark next as in stack
				actionString += "-"+ next;	//add next to action string
				energy -= 1; //remove an energy since we moved 
			}
			
			else {	//if no more valid moves
				
				if (stack.peek() != null) { 
				
					MapCell topCell = stack.pop(); //most recent cell gets popped out of stack
			
					topCell.markOutStack(); //mark that cell as out of stack
					
					if (topCell.isDonut()) {	//if that cell was a donut then we lose 3 energy
					
						energy -= 3;
					}
				
					if (topCell.isStart()) {	//if that cell was the start then break
					
						break;
					}
				
					if (!topCell.isExit()) {	//if that cell is anything else
					
						next = stack.peek(); //Peeking stack since topCell was popped off, and now the cell at the top of the stack is the previous cell
					
						actionString += "-" + next; //adding previous cell to action string
						
						energy += 1; //adding 1 energy since we are now moving backwards.
					}
	
				}
			
				else {
				
					energy -= 1;
				
					destinationFound = true;
					
					break;

				}
			
			}

		}
		
		while (!stack.isEmpty()) { //while stack is not empty
			
			MapCell topCell = stack.pop(); //topcell gets popped and marked out of stack
			
			topCell.markOutStack();
		}
		
		return actionString + "-" + "E" + energy; //returning action string separated by dashes followed by energy level.
	}
	
	public static void main (String[] args) { //main method, given.
		
			if (args.length < 1) {
				
				System.out.println("You must provide the name of the input file");
			}
			
			String mapFile = args[0];
			StartSearch ss = new StartSearch(mapFile);
			
			ss.findPath();
			
	}
			
}
