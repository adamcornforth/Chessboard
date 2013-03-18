public class BlankSquare extends ChessPiece {
	public BlankSquare(int i) {
		super(i); 
		this.setFilename("EmptySquare.jpg");
	}

	public boolean canMoveTo(ChessSquare destinationSquare) {
		return false; 
	}  

}