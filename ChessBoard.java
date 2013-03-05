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

	public void actionPerformed(ActionEvent e)
	{	
		ChessSquare button = (ChessSquare)e.getSource(); 

		// save selected piece to instance variable 
		if(this.selectedPiece != null) {
			// clear all squares if not piece clicked
			for(int i =0;i < 128;i++) {
				if(!hex88(i) && !chessSquare[i].isPiece())
					chessSquare[i].setIcon(new ImageIcon("EmptySquare.jpg"));
			}
			this.selectedPiece = (button.isPiece()) ? null : this.selectedPiece; 
		} 
		if(button.isPiece()) {
			this.selectedPiece = button; 
			System.out.println("Piece " + button.getPieceName() + " selected for moving... (has moved "
								+ button.getMoves() + " times)");
			for(int i =0;i < 128;i++) {
				if(!hex88(i) && this.selectedPiece.canMoveTo(chessSquare[i])) {
					chessSquare[i].setIcon(new ImageIcon("SelectedSquare.jpg"));
					System.out.println("Piece index " +selectedPiece.getIndex()+ 
						" can move to destination index " + i + "!"); 
				}
			}
		}

		// move selected piece if button pressed hasn't got a piece on it
		if(!button.isPiece() && selectedPiece != null && this.selectedPiece.canMoveTo(button)) {
			System.out.println("Piece " + this.selectedPiece.getPieceName() + " moved to new square!");
			this.selectedPiece.moveTo(button); 
			this.selectedPiece = null; 
		} 
	}

	/* 
		Takes the index of a square in the array and
		returns if it is on the 'legal' board or not
	*/
	public boolean hex88(int index) {

		// convert index to hex string then parse as an int then 
		// bitwise AND this with the integer representation of 0x88
		int test = (Integer.parseInt(Integer.toHexString(index), 16) 
					& Integer.parseInt("88", 16));

		return (test != 0) ? true : false; 
	}

	private void initHexChessSquares(int w, int h) {
		String piece = ""; 
		int file = 0; 
		int index = 0; 
		int test = 0; 

		// iterate through each row
		for (int rank=0;rank < 8; file++) {
			index = rank * 16 + file;
			// only need to add square class to array if rank/file passes 0x88 test
			if(!hex88(index)) {				 
				// set piece string if applicable on current rank/file
				piece = (rank == 6) ? "PAWN" : ""; 
				if(rank == 7) {
					switch(file) {
						case 1:
						case 6:
							piece = "KNIGHT";
							break; 
						case 2:
						case 5: 
							piece = "BISHOP";
							break;
						case 3:
							piece = "QUEEN"; 
							break;
						case 4: 
							piece = "KING"; 
							break;
						default: 
							piece = "ROOK"; 
					}
				}

				chessSquare[index] = new ChessSquare(w * file, h * rank, w, h, piece, index); 
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