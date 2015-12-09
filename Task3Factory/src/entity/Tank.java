package entity;

public class Tank implements IProduct {

	@Override
	public void installFirstPart(IProductPart productPart) {
		System.out.println("install " + productPart);
	}

	@Override
	public void installSecondPart(IProductPart productPart) {
		System.out.println("install " + productPart);
	}

	@Override
	public void installThirdPart(IProductPart productPart) {
		System.out.println("install " + productPart);
	}

	@Override
	public String toString() {
		return "Tank";
	}
}
