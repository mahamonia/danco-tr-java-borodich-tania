package run;
import making.Bouquet;
import making.Maker;
import entity.Aster;
import entity.Chamomile;
import entity.Rose;


public class FlowerShop {
	
	static final int amountRose = 5;
	static final int amountAster = 1;
	static final int amountChamomile = 1;
	
	static final int priceRose = 20;
	static final int priceAster = 12;
	static final int prisceChamomile = 3;
	
	public static void main(String[] args) {
		
		final String message = "Price bouquet = ";
		
		Rose rose = new Rose("Rose", amountRose, priceRose);
		Aster aster = new Aster("Aster", amountAster, priceAster);
		Chamomile chamomile = new Chamomile("Chamomile", amountChamomile, prisceChamomile);
		
		Bouquet bouquet;
		
		Maker maker = new Maker();
		bouquet = maker.makeBouquet(rose, aster, chamomile);
		System.out.println(message+bouquet.getPrice());

	}

}
