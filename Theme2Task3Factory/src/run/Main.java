package run;
import entity.Corps;
import entity.Engine;
import entity.IProduct;
import entity.IProductPart;
import entity.Tank;
import entity.Tower;
import assembleline.AssemblyLine;
import builder.BuildPartCorps;
import builder.BuildPartEngine;
import builder.BuildPartTower;
import builder.ILineStep;

public class Main {

	public static void main(String[] args) {
		
		IProductPart corps = new Corps();
		IProductPart engine = new Engine();
		IProductPart tower = new Tower();
		
		IProduct tank = new Tank(corps, engine, tower);
	
		ILineStep buildCorps = new BuildPartCorps();
		ILineStep buildTower = new BuildPartTower();
		ILineStep buildEngine = new BuildPartEngine();
	
		AssemblyLine assemblyLine = new AssemblyLine(buildCorps, buildTower, buildEngine );
		System.out.println("To start "+assemblyLine.assembleProduct(tank));

	}
}
