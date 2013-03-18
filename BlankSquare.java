public class BlankSquare extends ChessPiece {
	public BlankSquare(int i) {
		super(i); 
		this.setFilename("Images/EmptySquare.jpg");
	}

	public boolean canMoveTo(ChessSquare destinationSquare, ChessSquare[] chessSquare) {
		return false; 
	}  

	public boolean scanPath(int start, int end, ChessSquare[] chessSquare) {
		return false;  
	}

}