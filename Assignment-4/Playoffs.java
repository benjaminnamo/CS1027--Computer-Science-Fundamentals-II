/**
 * 
 * This class represents the NHL Playoffs and contains several methods for updating the playoff
 * tree and the standings table.
 * 
 * @author Benjamin Namo CS1027
 * 
 */
import java.util.Iterator;

public class Playoffs {
	
	private LinkedBinaryTree <String> tree; //declaring the LinkedBinaryTree that will our series
	private HockeySeries[] standings; //declaring the standings table
	
/** 
 * Constructor that creates a string array of the series using a text file of the teams, then utilizes TreeBuilder to turn this
 * array into a Linked Binary Tree. Also creates a standings table containing the series.
 * 
 */
	public Playoffs() {
		
		MyFileReader teams = new MyFileReader("teams.txt"); //import teams text file
		
		String[] array = new String[31]; //create empty string array with 31 slots
		
		for (int i = 0; i < 15; i++) {	// assign first 15 slots of array with TBD (To be determined) followed by the index
			
			array[i] = "TBD "+ i;
		}
		
		for (int i = 15; i<= 30; i++) {	//assign the remaining slots with the teams from the text file
			
			array[i] = teams.readString();
		}
		
		HockeySeries[] standings = new HockeySeries [15];	//creating standings table
		
		
		standings[0] = new HockeySeries(array[15],array[16],0,0);	//assigning pairs of teams in array to standings array indexes, in order
		
		standings[1] = new HockeySeries(array[17],array[18],0,0);
		
		standings[2] = new HockeySeries(array[19],array[20],0,0);
		
		standings[3] = new HockeySeries(array[21],array[22],0,0);
		
		standings[4] = new HockeySeries(array[23],array[24],0,0);
		
		standings[5] = new HockeySeries(array[25],array[26],0,0);
		
		standings[6] = new HockeySeries(array[27],array[28],0,0);
		
		standings[7] = new HockeySeries(array[29],array[30],0,0);
		
		
		@SuppressWarnings("unchecked")
		TreeBuilder <String> seriesTree = new TreeBuilder(array);	//using TreeBuilder to turn the array into a Linked Binary Tree
		
		tree = seriesTree.getTree(); //assigning created tree to tree variable
		
		this.standings = standings; //assigning standings table to standings variable
		
		}
		
		/**
		 * A method to return the tree.
		 * 
		 * @return tree the Linked Binary Tree
		 */
		public LinkedBinaryTree<String> getTree(){
			
			return tree; //returning tree
		}
		
		/**
		 * A method to return the standings table.
		 * 
		 * @return standings the standings table
		 */
		public HockeySeries[] getStandings() {
			
			return standings; //returning standings
		}
		
		/**
		 * A method that in 2 Strings representing team names and 2 ints representing the number
		 * of wins by which to update the standings for these teams.
		 * 
		 * @param teamA the name of the first team
		 * @param teamB the name of the second team
		 * @param winsA the amount of wins of the first team
		 * @param winsB the amount of wins of the second team
		 * @return seriesWinner if a team reaches 4 wins, method returns that team as the series winner
		 */
		public String updateStandings(String teamA,String teamB,int winsA,int winsB) {
			
			
			for (int i=0;i<=14;i++) { //loop through all indexes of standings
						
				if (standings[i] != null) { //if index is not null
				
					if (standings[i].getTeamA().contains(teamA) && standings[i].getTeamB().contains(teamB) ) { //if index contains the two teams
					
						standings[i].incrementWins(winsA, winsB); //incrementWins with the wins parameters
					
						if (standings[i].getTeamAWins() >= 4) { //if team A reaches 4 wins, return team A as the series winner
						
							String seriesWinner = standings[i].getTeamA();
						
							return seriesWinner;
						}
					
						if (standings[i].getTeamBWins() >= 4) {	//if team B reaches 4 wins, return team B as the series winner
						
							String seriesWinner = standings[i].getTeamB();
						
							return seriesWinner;
						}
					}
				}
			}
			
			return null; //if no winners, return null
		}
		
		/**
		 * A method that takes in a round value and loads the corresponding round file. From this file, determines the updated 
		 * standings.
		 * 
		 * @param round the series round
		 */
		public void updateRound(int round) {
			
		MyFileReader scores = new MyFileReader("scores"+round+".txt"); //loading in scores file for given round, ie "scores1.txt"
			
				
			for (int i=0; i<=100; i++) { //looping through lines in scores file

				if (scores.endOfFile() == false) { //if end of file has not been reached
						
					String[] game = scores.readString().split(","); //splitting line in order to get 4 strings (teamA, teamB, scoreA, scoreB)
						
						if (Integer.parseInt(game[2]) > Integer.parseInt(game[3]) ) { //if score A is greater than score B
							
							game[2] = "1";	//assign a 1 to scoreA, meaning team A has 1 more wins
							game[3] = "0";	//assign a 0 to scoreB, meaning team B has 0 more wins
						}
						
						if (Integer.parseInt(game[2]) < Integer.parseInt(game[3]) ) { //if score B is greater than score A
							
							game[2] = "0";	//assign a 0 to scoreA, meaning team A has 0 more wins
							game[3] = "1";	//assign a 1 to scoreB, meaning team B has 1 more wins
						}
						
						if (Integer.parseInt(game[2]) == Integer.parseInt(game[3]) ) { //if score is a tie
							
							game[2] = "0";	//assign a 0 to scoreA, meaning team A has 0 more wins
							game[3] = "0";	//assign a 0 to scoreB, meaning team B has 0 more wins
						}
						
						String newStanding = updateStandings(game[0],game[1],Integer.parseInt(game[2]),Integer.parseInt(game[3])); //updating standings using the values of teamA,teamB,scoreA,scoreB
																														    	   //converting scoreA and scoreB from string to integer
						if (newStanding != null) { //if newStandings isn't null, meaning a winner has been returned from updateStandings
							
							BinaryTreeNode<String> winner = findParent(game[0],game[1]); //utilize findParent() to find the parent node of the two teams
							
							winner.setData(newStanding); //assign the parent node to the winning team returned from updateStandings
						}
				}	
			}
		}
			
		/**
		 * This method takes in two team names and finds the parent node of those teams in the LinkedBinaryTree using a queue-based level-order
		 * traversal.
		 * 
		 * @param teamA the string name of the first team
		 * @param teamB the string name of the second team
		 * @return node the parent node of the first and second team
		 */
		public BinaryTreeNode<String> findParent(String teamA, String teamB){
			
			ArrayUnorderedList<String> tempList = new ArrayUnorderedList<String>(); //creating a temporary list to hold traversal values
			LinkedQueue<BinaryTreeNode<String>> queue = new LinkedQueue<BinaryTreeNode<String>>(); //creating a queue to put the tree values into
			BinaryTreeNode<String> node = null; //initializing nodes as null
			BinaryTreeNode<String> leftNode = null;
			BinaryTreeNode<String> rightNode = null;

			queue.enqueue(tree.getRoot()); //creates queue using the tree values

			while (!queue.isEmpty()) { //while the queue has values in it
				
				node = queue.dequeue(); //set current node to the node at the front
				
				tempList.addToRear(node.getData()); //add the current node to the rear of the temporary list
				
	
				if (node.getLeft() != null) { //if left node isn't null, enqueue node onto queue
					
					queue.enqueue(node.getLeft());
				}
				
				if (node.getRight() != null) { //if right node isn't null, enqueue node onto queue
					
					queue.enqueue(node.getRight());
				}
				
				if (node.getLeft() != null && node.getRight() != null) { //if both left and right arent null
				
					leftNode = node.getLeft();	//assign leftNode to left node
					rightNode = node.getRight();	//assign rightNode to right node
					
					// if left node and right node of current node are the desired teams, node is therefore the parent and is returned
					if ((leftNode.getData().contains(teamA) && rightNode.getData().contains(teamB)) || (leftNode.getData().contains(teamB) && rightNode.getData().contains(teamB))) {
						
						return node;
					}
				}
			}
			
			if (!queue.isEmpty()) { //if queue isn't empty, return node
				
				return node;
			}
			
			else {
				
				node = null;
			}
			
			return node;
		}
		
	/**
	 * This method adds the new series to the standings array before a new round begins. It does this using an iterator
	 * from the tree and skipping over the nodes that are still unknown until it gets to the nodes from the new round.
	 * It then takes two teams at a time from the iterator and creates a new series in the standings array for those
	 * two teams. The series standings (number of wins for each team) are set to 0 by default. 
	 */
	public void addNewStandings (int numSkips, int sIndex, int eIndex) {
		Iterator<String> iter = tree.iteratorLevelOrder();
		int i;
		String team1, team2;
		for (i = 0; i < numSkips; i++) {
			iter.next();
		}
		for (i = sIndex; i <= eIndex; i++) {
			team1 = iter.next();
			team2 = iter.next();
			standings[i] = new HockeySeries(team1, team2, 0, 0);
		}
	}
	
	/**
	 * This method simply prints out the standings table in a cleanly formatted table structure.
	 */
	public void printStandings () {
		String str;
		for (int k = 0; k < standings.length; k++) {
			if (standings[k] != null) {
				str = String.format("%-15s\t%-15s\t%3d-%d", standings[k].getTeamA(), standings[k].getTeamB(), standings[k].getTeamAWins(), standings[k].getTeamBWins());
				System.out.println(str);
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		Playoffs pl = new Playoffs();
		pl.updateRound(1);

		// Uncomment each pair of lines when you are ready to run the subsequent rounds. 
		
		//pl.addNewStandings(7, 8, 11); // Ensure you execute this line before calling updateRound(2).
		//pl.updateRound(2);

		
		//pl.addNewStandings(3, 12, 13); // Ensure you execute this line before calling updateRound(3).
		//pl.updateRound(3);

		
		//pl.addNewStandings(1, 14, 14); // Ensure you execute this line before calling updateRound(4).
		//pl.updateRound(4);
	}

}
