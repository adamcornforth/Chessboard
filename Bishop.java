public class Bishop extends ChessPiece{
	public Bishop(int i) {
		super(i); 
		this.setFilename("Images/Bishop.jpg");
		this.setIsPiece(true); 
	}
	
	public boolean canMoveTo(ChessSquare destinationSquare) {
		int indexDiff = destinationSquare.getChessPiece().getIndex() - this.getIndex(); 
		if( (((indexDiff % 15 == 0) || (indexDiff % 17 == 0))) // if square is diagonally above
			 || (((indexDiff % -15 == 0) || (indexDiff % -17 == 0))) // if square is diagonally below
			 )
				return !destinationSquare.isPiece(); 
			return false;	
	}
} 