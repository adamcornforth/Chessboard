/* 
	ChessSquare.java
	Used to represent each square in the GUI
*/

import javax.swing.*; 
import java.awt.*;

public class ChessSquare extends JButton {
	private int xPos, yPos, pieceW, pieceH, index, moves = 0; 
	private boolean canUp = true, canDown = true, canLeft = true, canRight = true; 
	private String piece; 
	private String filename; 

	public ChessSquare(int x, int y, int w, int h, String p, int index) {
		// decide which file to load as icon, 
		// set piece instance variable if file selected, 
		// else set to default
		this.filename = decideFilename(p); 
		this.piece = (filename == "EmptySquare.jpg") ? "NONE" : p; 

		// pass icon to JButton 
		// (would use super but has to be 1st statement)
		ImageIcon i = new ImageIcon(this.filename); 
		this.setIcon(i);  

		// initialise button
		this.setPreferredSize(new Dimension(w, h));
		this.setLocation(x, y); 
		this.setSize(w, h); 

		// set instance variables
		this.xPos = x; 
		this.yPos = y; 
		this.pieceW = w; 
		this.pieceH = h; 
		this.index = index; 
	}

	public void moveTo(ChessSquare destinationSquare) {
		ImageIcon i = new ImageIcon(destinationSquare.getFilename());  
		ImageIcon iNew = new ImageIcon(this.getFilename()); 
		int destMoves = destinationSquare.getMoves(); 
		
		// set new square
		destinationSquare.setPiece(this.getPieceName());
		destinationSquare.setIcon(iNew); 
		destinationSquare.setMoves(this.getMoves());
		destinationSquare.incrementMoves(); 
		
		// set old square as blank
		this.setMoves(destMoves); 
		this.setIcon(i); 
		this.setPiece("NONE"); 
	}

	public boolean canMoveTo(ChessSquare destinationSquare) {
		int index = destinationSquare.index; 
		int indexDiff = destinationSquare.getIndex() - this.index; 
		switch(this.piece) { 
			case "PAWN":
				if( ((destinationSquare.index == this.index - 32) && this.getMoves() == 0) // if square is 2 steps ahead & is pawn's first move
				 || (destinationSquare.index == this.index - 16) // if square is directly above
				 )
					return !destinationSquare.isPiece();
				break;
			case "ROOK": 
				if( (indexDiff % 16 == 0 && this.canUp && this.canDown) // if square is above or below
				 || (indexDiff < 8 && indexDiff > -8 && this.canLeft && this.canRight) // if square is to the side
				 )
					return !destinationSquare.isPiece(); 
				break; 
			case "BISHOP": 
				if( (((indexDiff % 15 == 0) || (indexDiff % 17 == 0)) && this.canUp) // if square is diagonally above
				 || (((indexDiff % -15 == 0) || (indexDiff % -17 == 0)) && this.canDown) // if square is diagonally below
				 )
					return !destinationSquare.isPiece(); 
				break; 
			case "QUEEN":
				if( (((indexDiff % 15 == 0) || (indexDiff % 17 == 0)) && this.canUp) // if square is diagonally above
				 || (((indexDiff % -15 == 0) || (indexDiff % -17 == 0)) && this.canDown) // if square is diagonally below
				 || (indexDiff % 16 == 0 && this.canUp && this.canDown) // if square is above or below 
				 || (indexDiff < 8 && indexDiff > -8 && this.canLeft && this.canRight) // if square is to the side 
				 )
					return !destinationSquare.isPiece(); 
				break;
			case "KING": 
				if( ((destinationSquare.index == this.index - 16) || (destinationSquare.index == this.index + 16)) // directly above and below
				 || ((destinationSquare.index == this.index - 15) || (destinationSquare.index == this.index + 15)) // upper left and below left 
				 || ((destinationSquare.index == this.index - 17) || (destinationSquare.index == this.index + 17)) // upper right and below right
				 || ((destinationSquare.index == this.index - 1) || (destinationSquare.index == this.index + 1)) // if square is directly to the side 
				  )
					return !destinationSquare.isPiece(); 
				break;  
			case "KNIGHT": 
				if( ((destinationSquare.index == this.index - 31)) // if square is above and to the left 
				 || ((destinationSquare.index == this.index - 33)) // above and to the right
				 || ((destinationSquare.index == this.index + 31)) // below and to the left
				 || ((destinationSquare.index == this.index + 33)) // below and to the right
				 || ((destinationSquare.index == this.index - 14)) // above and more to the left
				 || ((destinationSquare.index == this.index - 18)) // above and more to the right
				 || ((destinationSquare.index == this.index + 14)) // below and more to the left 
				 || ((destinationSquare.index == this.index + 18)) // below and more to the right
				 )
					return !destinationSquare.isPiece(); 
			default: 
		}
		return false; 

	}

	public void setPiece(String p) {
		this.filename = decideFilename(p); 
		this.piece = (filename == "EmptySquare.jpg") ? "NONE" : p; 
	}

	private String decideFilename(String p) {
		switch(p) {
			case "PAWN":
				return "Pawn.jpg"; 
			case "ROOK":
				return "Rook.jpg"; 
			case "KNIGHT":
				return "Knight.jpg"; 
			case "BISHOP":
				return "Bishop.jpg"; 
			case "QUEEN":
				return "Queen.jpg"; 
			case "KING":
				return "King.jpg"; 
			default:
				return "EmptySquare.jpg"; 
		} 
	}

	public void setMoves(int m) {
		this.moves = m; 
	} 

	public void incrementMoves() {
		this.moves++;
	}

	public Integer getMoves() {
		return this.moves;
	}

	public Integer getIndex() {
		return this.index; 
	}
 
	public String getFilename() {
		return this.filename; 
	}

	public String getPieceName() {
		return this.piece;  
	}

	public Boolean isPiece() {
		return (this.piece != "NONE") ? true : false; 
	}
}