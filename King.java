public class King extends ChessPiece{
	public King (int i) {
		super (i); 
		this.setFilename("Images/King.jpg");
		this.setIsPiece(true); 
	}

	public boolean canMoveTo(ChessSquare destinationSquare) {
		int index = destinationSquare.getChessPiece().getIndex(); 
		if( ((index == this.getIndex() - 16) || (index == this.getIndex() + 16)) // directly above and below
			 || ((index == this.getIndex() - 15) || (index == this.getIndex() + 15)) // upper left and below left 
			 || ((index == this.getIndex() - 17) || (index == this.getIndex() + 17)) // upper right and below right
			 || ((index == this.getIndex() - 1) || (index == this.getIndex() + 1)) // if square is directly to the side 
			  )
				return !destinationSquare.isPiece(); 
			return false; 
	}
} 