/* 
	ChessBoard.java
	Used to represent the GUI of the application
*/

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class ChessBoard implements ActionListener {
	private int frameWidth, frameHeight; 
	private String frameTitle;
	private ChessSquare[] chessSquare = new ChessSquare[128];  
 
	private JFrame boardFrame; 
	private JPanel content; 
	private ChessSquare selectedPiece = null;  

	private Validation validator = new Validation(); 

	public ChessBoard(String title, int w, int h) { 
		frameWidth = w; 
		frameHeight = h; 
		frameTitle = title; 

		// initialise frame 
	 	boardFrame = new JFrame(title);  
		boardFrame.setSize(w, h); 
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		boardFrame.setResizable(false);

		// initialise panel
		content = new JPanel(); 
		content.setLayout(null); 
		boardFrame.setContentPane(content); 

		// add buttons (squares)
		initHexChessSquares(44, 44); 
		
		boardFrame.setVisible(true);  
	}

	/*
		Whenever a button is pressed:
		- Handles the clearing of orange squares if a new piece is selected
		- Discards selected pieces & orange squares if an unavailable square is clicked on 
		- If a piece is clicked on, works out what moves are valid for that piece
		- Moves a piece if a piece is selected and the button pressed is legal and isn't a piece
	*/
	public void actionPerformed(ActionEvent e)
	{	
		ChessSquare button = (ChessSquare)e.getSource(); 

		// clear all squares if there's a piece previously selected
		if(this.selectedPiece != null) {
			for(int i =0;i < 128;i++) {
				if(!this.validator.hex88(i) && !chessSquare[i].isPiece())
					chessSquare[i].setIcon(new ImageIcon("Images/EmptySquare.jpg"));
			}

			// can't move to new selection or it's a new piece? discard the previous selection
			if(!this.selectedPiece.getChessPiece().canMoveTo(button, chessSquare) || button.isPiece())
				this.selectedPiece = null; 
		} 

		// if user clicks on a piece, we need to work out what moves are valid for that piece
		if(button.isPiece()) {
			this.selectedPiece = button; // remember this selection for later
			for(int i =0;i < 128;i++) {
				if(!this.validator.hex88(i) && !chessSquare[i].isPiece() && this.selectedPiece.getChessPiece().canMoveTo(chessSquare[i], chessSquare)) {
					chessSquare[i].setIcon(new ImageIcon("Images/SelectedSquare.jpg"));
				}
			}
			System.out.println("\n \n*** Moves Considered *** \n \n"); 
		} 

		// move selected piece (if it exists) if button pressed hasn't got a piece on it, and button can be moved to 
		if(!button.isPiece() && this.selectedPiece != null && this.selectedPiece.getChessPiece().canMoveTo(button, chessSquare)) {
			this.selectedPiece.moveTo(button); 
			this.selectedPiece = null; 
			System.out.println("\n \n*** Piece Moved *** \n \n"); 
		} 
	}

	/*
		Creates a linear array for the chessboard
		- 128 elements (as per the 0x88 implementation of a chessboard)
		- iterates through every rank and file, converting each into an 'index' integer to be stored
		- only store a chessSquare object if the index integer passes the 0x88 test
		- switch statement used to determine if a certain rank/file holds a piece
		- increments the rank when the end of a file reached
	*/ 
	private void initHexChessSquares(int w, int h) {
		ChessPiece piece; 
		int file = 0; 
		int index = 0; 
		int test = 0; 

		// iterate through each file
		for (int rank=0; rank < 8; file++) {
			index = rank * 16 + file;
			// only need to add square class to array if rank/file passes 0x88 test
			if(!this.validator.hex88(index)) {				 
				// set piece string if applicable on current rank/file
				piece = (rank == 6) ? new Pawn(index) : new BlankSquare(index); 
				if(rank == 7) {
					switch(file) {
						case 1:
						case 6:
							piece = new Knight(index);
							break; 
						case 2:
						case 5: 
							piece = new Bishop(index);
							break;
						case 3:
							piece = new Queen(index); 
							break;
						case 4: 
							piece = new King(index); 
							break;
						default: 
							piece = new Rook(index); 
					}
				}

				chessSquare[index] = new ChessSquare(w * file, h * rank, w, h, piece); 
				content.add(chessSquare[index]); 
	 			chessSquare[index].addActionListener(this); 
			} else {
				chessSquare[index] = null; 
			}

			// increment to next rank if reach end of file (which
			// is 15 squares long) in the 0x88 implementation
			if(file == 15) { 
	 			rank++;
	 			file = -1;
	 		}
		}
	}

}