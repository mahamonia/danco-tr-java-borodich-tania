package run;
import making.Bouquet;
import making.Maker;
import entity.Aster;
import entity.Chamomile;
import entity.Flower;
import entity.Rose;


public class FlowerShop {
	
	public void Shop() {
		
		final String message = "Price bouquet = ";
		
		Rose rose = new Rose("Rose", 20);
		Aster aster = new Aster("Aster", 12);
		Chamomile chamomile = new Chamomile("Chamomile", 5);
		
		Flower [] flower = {rose, aster, chamomile};
		Bouquet bouquet;
		
		Maker maker = new Maker();
		bouquet = maker.makeBouquet(flower);
		
		System.out.println(message+bouquet.getPrice());

	}

}
