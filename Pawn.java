public class Pawn extends ChessPiece{
	public Pawn (int i) {
		super (i); 
		this.setFilename("Images/Pawn.jpg");
		this.setIsPiece(true); 
	}

	public boolean canMoveTo(ChessSquare destinationSquare, ChessSquare[] chessSquare) {
		int index = destinationSquare.getChessPiece().getIndex(); 
		int indexDiff = destinationSquare.getChessPiece().getIndex() - this.getIndex(); 
		if( ((index == this.getIndex() - 32) && this.getMoves() == 0) // if square is 2 steps ahead & is pawn's first move
			 || (index == this.getIndex() - 16) // if square is directly above
			 )
				if(scanPath(this.getIndex(), destinationSquare.getChessPiece().getIndex(), chessSquare)) return !destinationSquare.isPiece();
			return false; 
	}

	public boolean scanPath(int start, int end, ChessSquare[] chessSquare) {
		int indexDiff = end - start;
		if(indexDiff == 0) return false; 
		int increment = 1; 

		if(end == start - 32) { // square is 2 above
			increment = -16; 
		}
		else if(end == start - 16) { // square is 1 above
			increment = -16;  
		} 

		if(increment != 0) {
			for (int i = (end-=increment); i != start; i-=increment) {
				if(chessSquare[i].isPiece()) 
					return false;
			}
		}

		return true;  
	}

} 