public class Pawn extends ChessPiece{
	public Pawn (int i) {
		super (i); 
		this.setFilename("Pawn.jpg");
		this.setIsPiece(true); 
	}

	public boolean canMoveTo(ChessSquare destinationSquare) {
		int index = destinationSquare.getChessPiece().getIndex(); 
		int indexDiff = destinationSquare.getChessPiece().getIndex() - this.getIndex(); 
		if( ((index == this.getIndex() - 32) && this.getMoves() == 0) // if square is 2 steps ahead & is pawn's first move
			 || (index == this.getIndex() - 16) // if square is directly above
			 )
				return !destinationSquare.isPiece();
			return false; 
	}
} 