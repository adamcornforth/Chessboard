/* 
	ChessPiece.java
	Used to represent each square in the GUI
*/

import javax.swing.*; 
import java.awt.*;

public abstract class ChessPiece {
	private int moves = 0; 
	private int index;  
	private String filename = ""; 
	private boolean isPiece = false; 

	public ChessPiece(int i) {
		this.index = i; 
	}

	/* 
		Check if this square can moved to the passed destination square 
			First check the piece of this square. The logic is different for each piece. 
			- Pawn is done manually and simply deducting -16 from this square's index reveals the index of the only possible destination. -32 for two squares ahead (only on pawn's 1st move). 
			- Sliding pieces can be done using by checking if a) the index difference is in the same rank and b) the index difference % 16 == 0
			- Diagonal pieces work on the same principle as sliding b. Simply do modulo 15, -15, 17, -17, etc. 
			- King & Knight done painstakingly and manually. 

			If any of the conditions are met in the associated case in the switch statement, 
			return the *opposite* of the true/false value of whether the destination square is a piece. 
	*/
	public abstract boolean canMoveTo(ChessSquare destinationSquare);  


	/*  Getters  */ 

	public boolean isPiece() {
		return isPiece; 
	}

	public Integer getIndex() {
		return this.index; 
	}

	public Integer getMoves() {
		return this.moves;
	} 

	public String getFilename() {
		return this.filename; 
	}

	/*  Setters	 */ 

	public void setIsPiece(boolean v) {
		isPiece = v; 
	}

	public void setFilename(String filename) {
		this.filename = filename; 
	}

	public void setIndex(int i) {
		this.index = i; 
	}

	public void setMoves(int m) {
		this.moves = m; 
	} 

	public void incrementMoves() {
		this.moves++; 
	}

}