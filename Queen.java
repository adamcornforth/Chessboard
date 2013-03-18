public class Queen extends ChessPiece{
	public Queen (int i) {
		super (i); 
		this.setFilename("Queen.jpg");
		this.setIsPiece(true); 
	}
	
	public boolean canMoveTo(ChessSquare destinationSquare) {
		int indexDiff = destinationSquare.getChessPiece().getIndex() - this.getIndex(); 
		if( (((indexDiff % 15 == 0) || (indexDiff % 17 == 0))) // if square is diagonally above
			 || (((indexDiff % -15 == 0) || (indexDiff % -17 == 0))) // if square is diagonally below
			 || (indexDiff % 16 == 0) // if square is above or below 
			 || (indexDiff < 8 && indexDiff > -8) // if square is to the side 
			 )
				return !destinationSquare.isPiece(); 
			return false; 
	}
} 