
public class MaxNumeral {
	
	public MaxNumeral(){
		
	}
	
	public int SearchMaxNumeral(NumberOfThree number) {

		int maxNumeral = 0;
		if (number.getFirst() > maxNumeral) {
			maxNumeral = number.getFirst();
		}
		if (number.getSecond() > maxNumeral) {
			maxNumeral = number.getSecond();
		}
		if (number.getThird() > maxNumeral) {
			maxNumeral = number.getThird();
		}

	return maxNumeral;

	}

}
