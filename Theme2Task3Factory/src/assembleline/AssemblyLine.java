package assembleline;

import entity.IProduct;
import entity.IProductPart;
import builder.ILineStep;

public class AssemblyLine implements IAssemblyLine {

	private ILineStep buildCorps;
	private ILineStep buildTower;
	private ILineStep buildEngine;

	public AssemblyLine(ILineStep buildCorps, ILineStep buildTower,
			ILineStep buildEngine) {
		this.buildCorps = buildCorps;
		this.buildTower = buildTower;
		this.buildEngine = buildEngine;
	}

	@Override
	public IProduct assembleProduct(IProduct product) {

		IProductPart corps = buildCorps.buildProductPart();
		IProductPart tower = buildTower.buildProductPart();
		IProductPart engine = buildEngine.buildProductPart();

		product.installFirstPart(corps);
		product.installSecondPart(tower);
		product.installThirdPart(engine);
		System.out.println("assembly.. " + product);
		return product;
	}
}
