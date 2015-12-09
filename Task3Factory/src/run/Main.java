package run;
import entity.IProduct;
import entity.Tank;
import assembleline.AssemblyLine;
import builder.BuildPartCorps;
import builder.BuildPartEngine;
import builder.BuildPartTower;
import builder.ILineStep;

public class Main {

	public static void main(String[] args) {
		IProduct tank = new Tank();
	
		ILineStep buildCorps = new BuildPartCorps();
		ILineStep buildTower = new BuildPartTower();
		ILineStep buildEngine = new BuildPartEngine();
	
		AssemblyLine assemblyLine = new AssemblyLine(buildCorps, buildTower, buildEngine );
		System.out.println("To start "+assemblyLine.assembleProduct(tank));

	}
}
