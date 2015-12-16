
public class NumberOfThree {

	private int first;
	private int second;
	private int third;

	public NumberOfThree(int number) {

		this.first = (int) number / 100; // select hundreds
		this.second = (int) (number - this.first * 100) / 10;// select tens
		this.third = (int) (number - this.first * 100 - this.second * 10); // select units
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getThird() {
		return third;
	}

	public void setThird(int third) {
		this.third = third;
	}


}
