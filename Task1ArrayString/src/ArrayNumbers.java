
public class ArrayNumbers {
	
	private String [] arrString; //= {"1", "2", "7", "10"}
	private int arrLength; 
	private Integer [] arrInt;// = {1, 2, 7, 10};
	private int sumInt = 0;
	private String sumString;
	
	public ArrayNumbers(String [] arrString){
		this.setArrString(arrString);
		
		setArrLength(arrString.length);
		
		arrInt = new Integer[arrLength];
				
		for (int i = 0; i < getArrLength(); i++) {
			arrInt[i]=Integer.parseInt(getArrString()[i]);
		}		
	}
	
	public String [] getArrString() {
		return arrString;
	}

	public void setArrString(String [] arrString) {
		this.arrString = arrString;
	}
	
	public int getArrLength() {
		return arrLength;
	}

	public void setArrLength(int arrLength) {
		this.arrLength = arrLength;
	}

	public Integer[] getArrInt() {
		return arrInt;
	}

	public void setArrInt(Integer[] arrInt) {
		this.arrInt = arrInt;
	}

	public int getSumInt() {
		return sumInt;
	}

	public void setSumInt(int sumInt) {
		this.sumInt = sumInt;
	}

	public String getSumString() {
		return sumString;
	}

	public void setSumString(String sumString) {
		this.sumString = sumString;
	}

	public String SumNumbersArray (){

		for (int i = 0; i < getArrLength(); i++) {
			setSumInt(getSumInt()+getArrInt()[i]);
		}
		
		setSumString (String.valueOf(getSumInt()));
		return getSumString();
		
	}

}
