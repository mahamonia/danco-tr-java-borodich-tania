import java.util.Random;

public class Generator {

	final int min = 100;
	final int max = 999;

	private Integer number; // NumberOfThree

	public Generator() {
		Random randomGenerator = new Random();
		this.setNumber(randomGenerator.nextInt(max - min) + min);
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
