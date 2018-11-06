public class Queen {
	private int row;
	private int column;
	EightQueens QueensClass = new EightQueens();
	int N = QueensClass.getN();

	//constructor which assigns the row and column for a queen
	public Queen(int r, int c){
		row = r;
		column  = c;
	}
	
	//Determines whether this queen can attack another

	public boolean collisions(Queen q){
		boolean collision=false;
		
		//test rows and columns
		if(row==q.getRow() || column==q.getColumn())
			collision=true;
		//test diagonal
		else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
			collision=true;
			
		return collision;
	}

	//move the queen in the same column
	public void moveQueen(int spaces){
		row+=spaces;
		if(row>(N-1) && row%(N-1)!=0){
			row=(row%(N-1))-1;
		}
		else if(row>(N-1) && row%(N-1)==0){
			row=(N-1);
		}
	}
	
	//Getters and setters for the row
	public void setRow(int r){
		row = r;
	}

	public int getRow(){
		return row;
	}
	
	//Getters and setters for the column
	public void setColumn(int c){
		column = c;
	}

	public int getColumn(){
		return column;
	}

	// It is used to print the Queen board
	public String toString(){
		return "("+row+", "+column+")";
	}
}
