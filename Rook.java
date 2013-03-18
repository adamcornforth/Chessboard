public class Rook extends ChessPiece{
	public Rook (int i) {
		super (i); 
		this.setFilename("Rook.jpg");
		this.setIsPiece(true); 
	}
	
	public boolean canMoveTo(ChessSquare destinationSquare) {
		int indexDiff = destinationSquare.getChessPiece().getIndex() - this.getIndex(); 
		if( (indexDiff % 16 == 0) // if square is above or below
			 || (indexDiff < 8 && indexDiff > -8) // if square is to the side
			 )
				return !destinationSquare.isPiece(); 
			return false; 
	}
} 