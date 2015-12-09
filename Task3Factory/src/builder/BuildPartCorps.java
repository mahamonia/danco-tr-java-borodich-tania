package builder;

import entity.Corps;
import entity.IProductPart;

public class BuildPartCorps implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Build corps");

		Corps corps = new Corps();
		return corps;
	}
}
