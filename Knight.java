public class Knight extends ChessPiece{
	public Knight (int i) {
		super (i); 
		this.setFilename("Images/Knight.jpg");
		this.setIsPiece(true); 
	}
	
	public boolean canMoveTo(ChessSquare destinationSquare, ChessSquare[] chessSquare) {
		int index = destinationSquare.getChessPiece().getIndex(); 
		if( ((index == this.getIndex() - 31)) // if square is above and to the left 
			 || ((index == this.getIndex() - 33)) // above and to the right
			 || ((index == this.getIndex() + 31)) // below and to the left
			 || ((index == this.getIndex() + 33)) // below and to the right
			 || ((index == this.getIndex() - 14)) // above and more to the left
			 || ((index == this.getIndex() - 18)) // above and more to the right
			 || ((index == this.getIndex() + 14)) // below and more to the left 
			 || ((index == this.getIndex() + 18)) // below and more to the right
			 )
				return !destinationSquare.isPiece(); 
			return false;  
	}

	// don't need to check paths as this guy can jump!
	public boolean scanPath(int start, int end, ChessSquare[] chessSquare) {
		return true;  
	}

} 