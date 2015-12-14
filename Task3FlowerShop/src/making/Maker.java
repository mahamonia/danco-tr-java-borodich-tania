package making;

import entity.Aster;
import entity.Chamomile;
import entity.Rose;

public class Maker implements IMaker {

	public Maker() {

	}

	@Override
	public Bouquet makeBouquet(Rose rose, Aster aster, Chamomile chamomile) {
	
		Bouquet bouquet = new Bouquet();
		
		bouquet.addRose(rose, rose.getAmount());
		bouquet.addAster(aster, aster.getAmount());
		bouquet.addChamomile(chamomile, chamomile.getAmount());
		
		int priceRose = rose.getAmount() * rose.getPrice();
		int priceAster = aster.getAmount() * aster.getPrice();
		int priceChamomile = chamomile.getAmount() * chamomile.getPrice();
		
		bouquet.setPrice(priceRose + priceAster + priceChamomile);
		
	
		return bouquet;
	}

}
