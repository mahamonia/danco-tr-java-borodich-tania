package making;

import entity.Aster;
import entity.Chamomile;
import entity.Flower;
import entity.Rose;

public class Maker implements IMaker {

	public Maker() {

	}

	@Override
	public Bouquet makeBouquet(Flower [] flower) {
	
		Bouquet bouquet = new Bouquet();
		
			bouquet.addFlower(flower[0]);
			bouquet.addFlower(flower[1]);
			bouquet.addFlower(flower[2]);
	
		return bouquet;
	}


}
