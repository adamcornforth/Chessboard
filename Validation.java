public class Validation {
	/* 
		Takes an index and returns if it 
		is on the 'legal' board or not 
	*/
	public boolean hex88(int index) {
		// convert index to hex string then parse as an int then 
		// bitwise AND this with the integer representation of 0x88
		int test = (Integer.parseInt(Integer.toHexString(index), 16) 
					& Integer.parseInt("88", 16));

		return (test != 0) ? true : false; 
	}
}