package builder;

import entity.Engine;
import entity.IProductPart;

public class BuildPartEngine implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Build engine");

		Engine engine = new Engine();
		return engine;
	}
}
