
public class Main {

	public static void main(String[] args) {
		
		String message1 = "Generated number ";
		String message2 = "Maximum numeral of number ";
		
		Generator generator = new Generator();
		
		NumberOfThree number = new NumberOfThree(generator.getNumber());
		
		MaxNumeral maxNumeral = new MaxNumeral();
		int max = maxNumeral.SearchMaxNumeral(number);
		
		System.out.println(message1+generator.getNumber());
		System.out.println(message2+max);
		
		

	}

}
