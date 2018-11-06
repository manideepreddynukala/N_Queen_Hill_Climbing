import java.util.*;

public class Node implements Comparable<Node>{

    EightQueens QueensClass = new EightQueens();
    int N = QueensClass.getN();
	public Queen[] state; //the node's state
	private ArrayList<Node> neighbours;
	private int hn; //heuristic score

	public Node(){
		state = new Queen[N]; //empty state
		neighbours = new ArrayList<Node>(); //empty neighbour list
	} 
	
	//Constructor which creates a copy of a node's state
	public Node(Node n){
		state = new Queen[N];
		neighbours = new ArrayList<Node>();
		for(int i=0; i<N; i++)
			state[i] = new Queen(n.state[i].getRow(), n.state[i].getColumn());
		hn=0;
	}

	//Generates neighbours for a given state
	public ArrayList<Node> generateNeighbours(Node startState){
		int count=0;
		
		if(startState==null)
			System.out.println("warning");

		for(int i=0; i<N; i++){
			for(int j=1; j<N; j++){
				neighbours.add(count, new Node(startState));
				neighbours.get(count).state[i].moveQueen(j);
				neighbours.get(count).computeHeuristic();
				
				count++;
			}
		}
		return neighbours;
	}
	
	//calculate the heuristic
	public int computeHeuristic(){
	
		for(int i=0; i<N-1; i++){
			for(int j=i+1; j<N; j++){
				if(state[i].collisions(state[j])){
						hn++;
				}
			}
		}
		
		return hn;
	}

	public int getHeuristic(){
		return hn;
	}

	//compare the heuristic of two queen boards
	public int compareTo(Node n){
		if(this.hn < n.getHeuristic())
			return -1;
		else if(this.hn > n.getHeuristic()) {

            return 1;
        }
		else if(this.hn == n.getHeuristic()) {
			return 0;
		}
		else {
			return 0;
		}
	}
	
	//Getters and setters for the State
	public void setState(Queen[] s){
		for(int i=0; i<N; i++){
			state[i]= new Queen(s[i].getRow(), s[i].getColumn());
		}
	}

	public Queen[] getState(){
		return state;
	}
	
	//toString method print the state of the node
	public String toString(){
		String result="";
		String[][] board = new String[N][N];
		//initialise board with X's to indicate empty spaces
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				board[i][j]="X ";
			//place the queens on the board
		for(int i=0; i<N; i++){
			board[state[i].getRow()][state[i].getColumn()]="Q ";
		}

		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				result+=board[i][j];
			}
			result+="\n";
		}
		
		return result;
	}
}
