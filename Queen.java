public class Queen extends ChessPiece{
	public Queen (int i) {
		super (i); 
		this.setFilename("Images/Queen.jpg");
		this.setIsPiece(true); 
	}
	
	public boolean canMoveTo(ChessSquare destinationSquare, ChessSquare[] chessSquare) {
		int indexDiff = destinationSquare.getChessPiece().getIndex() - this.getIndex(); 
		if( (((indexDiff % 15 == 0) || (indexDiff % 17 == 0))) // if square is diagonally above
			 || (((indexDiff % -15 == 0) || (indexDiff % -17 == 0))) // if square is diagonally below
			 || (indexDiff % 16 == 0) // if square is above or below 
			 || (indexDiff < 8 && indexDiff > -8) // if square is to the side 
			 )
				if(scanPath(this.getIndex(), destinationSquare.getChessPiece().getIndex(), chessSquare)) return !destinationSquare.isPiece();
			return false;  
	}

	public boolean scanPath(int start, int end, ChessSquare[] chessSquare) {
		int indexDiff = end - start;
		if(indexDiff == 0) return false; 
		int increment = 0;  

		if(indexDiff % 15 == 0 && indexDiff < 0) { // square is up and to the right of the start square
			increment = -15; 
		}
		else if(indexDiff % 16 == 0 && indexDiff < 0) { // square is above the start square
			increment = -16; 
		}
		else if(indexDiff % 17 == 0 && indexDiff < 0) {  // square is up and to the left of the start square
			increment = -17; 
		}
		else if(indexDiff < end-8 && indexDiff < 0) { // square is to the left 
			increment = -1; 
		} 
		else if(indexDiff < 8 && indexDiff > 0) { // square is to the right 
			increment = 1; 
		} 
		else if(indexDiff % 17 == 0 && indexDiff > 0) { // square is down and to the left of the start square
			increment = 17; 
		} 
		else if(indexDiff % 16 == 0 && indexDiff > 0) { // square is below the start square
			increment = 16; 
		} 
		else if(indexDiff % 15 == 0 && indexDiff > 0) { // square is down and to the right of the start square
			increment = 15; 
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