public class Bishop extends ChessPiece{
	public Bishop(int i) {
		super(i); 
		this.setFilename("Images/Bishop.jpg");
		this.setIsPiece(true); 
	} 
	
	public boolean canMoveTo(ChessSquare destinationSquare, ChessSquare[] chessSquare) {
		int indexDiff = destinationSquare.getChessPiece().getIndex() - this.getIndex(); 
		if( (((indexDiff % 15 == 0) || (indexDiff % 17 == 0))) // if square is left up/down or right up/down
			 ) {
				if(scanPath(this.getIndex(), destinationSquare.getChessPiece().getIndex(), chessSquare))
					return !destinationSquare.isPiece(); 
			}
			return false;	
	}

	public boolean scanPath(int start, int end, ChessSquare[] chessSquare) {
		int indexDiff = end - start;
		if(indexDiff == 0) return false; 
		int increment = 0; 

		if(indexDiff % 15 == 0 && indexDiff < 0) { // square is up and to the right of the start square
			increment = -15; 
		}
		else if(indexDiff % 17 == 0 && indexDiff < 0) {  // square is up and to the left of the start square
			increment = -17; 
		}
		else if(indexDiff % 15 == 0 && indexDiff > 0) { // square is down and to the right of the start square
			increment = 15; 
		}
		else if(indexDiff % 17 == 0 && indexDiff > 0) { // square is down and to the left of the start square
			increment = 17; 
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