
public class Main {

	public static void main(String[] args) {
		
		String message = "Sum of the array elements = ";
		
		String [] arrString = {"1", "2", "7", "10"};
		
		ArrayNumbers arrayNumbers = new ArrayNumbers(arrString);
		
		String sum = arrayNumbers.SumNumbersArray();
		System.out.println(message+sum);

	}

}
