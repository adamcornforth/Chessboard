/* 
	ChessSquare.java
	Used to represent each square in the GUI
*/

import javax.swing.*; 
import java.awt.*;

public class ChessSquare extends JButton {
	private int xPos, yPos, pieceW, pieceH; 
	private ChessPiece chessPiece; 

	public ChessSquare(int x, int y, int w, int h, ChessPiece p) {
		super(new ImageIcon(p.getFilename())); 
		this.chessPiece = p; 

		// pass icon to JButton 
		// (would use super but has to be 1st statement)
		// ImageIcon i = new ImageIcon(chessPiece.getFilename()); 
		// this.setIcon(i);  

		// initialise button 
		this.setPreferredSize(new Dimension(w, h));
		this.setLocation(x, y); 
		this.setSize(w, h); 

		// set instance variables
		this.xPos = x; 
		this.yPos = y; 
		this.pieceW = w; 
		this.pieceH = h; 
	}

	/* 
		Switch the instance variables between this square and the destination square, to simulate movement 
	*/ 
	public void moveTo(ChessSquare destinationSquare) {
		ImageIcon i = new ImageIcon(destinationSquare.getChessPiece().getFilename()); 
		ImageIcon iNew = new ImageIcon(this.getChessPiece().getFilename()); 

		int destIndex = destinationSquare.getChessPiece().getIndex(); 
		int thisIndex = this.getChessPiece().getIndex(); 

		ChessPiece chessPieceNew = destinationSquare.getChessPiece(); 
		
		this.getChessPiece().incrementMoves(); 
 
		// set new square
		destinationSquare.setIcon(iNew); 
		destinationSquare.setChessPiece(this.getChessPiece()); 
		destinationSquare.getChessPiece().setIndex(destIndex);

		// set old square 
		this.setIcon(i); 
		this.setChessPiece(chessPieceNew); 
		this.getChessPiece().setIndex(thisIndex); 


	}


	/*  Getters  */ 

	public ChessPiece getChessPiece() {
		return this.chessPiece; 
	}
 
	public Boolean isPiece() {
		return (this.getChessPiece().isPiece()) ? true : false; 
	}


	/*  Setters  */ 

	public void setChessPiece(ChessPiece p) {
		this.chessPiece = p; 
	}

}