import java.util.Random;

public class NumberOfThree {

	private Integer number; // NumberOfThree

	final int min = 100;
	final int max = 999;

	private int first;
	private int second;
	private int third;
	private int maxNumeral;

	public NumberOfThree() {
		Random randomGenerator = new Random();
		this.setNumber(randomGenerator.nextInt(max - min) + min);
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public int MaxNumeral(int number) {
		first = (int) number / 100; // select hundreds
		second = (int) (number - first * 100) / 10;// select tens
		third = (int) (number - first * 100 - second * 10); // select units

		maxNumeral = 0;
		if (first > maxNumeral) {
			maxNumeral = first;
		}
		if (second > maxNumeral) {
			maxNumeral = second;
		}
		if (third > maxNumeral) {
			maxNumeral = third;
		}

		return maxNumeral;

	}

}
