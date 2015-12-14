package builder;

import entity.IProductPart;
import entity.Tower;

public class BuildPartTower implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Build tower");

		Tower tower = new Tower();
		return tower;
	}
}
