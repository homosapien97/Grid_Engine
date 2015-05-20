package terrain;

import java.awt.Image;

import general.Tools;

public class Stone extends Terrain implements Thermochemical {
	private static final Stone stone = new Stone();
	private static final String filename = "stone.png";
	private static char c = 'A';
	
	public static final Image sprite = Tools.img.loadTerrainSprite(filename);
	
	private Stone() {
		super("stone", filename);
	}

	public static Stone get() {
		return stone;
	}
	
	public String toString() {
		return "X";
	}
	
	@Override
	public double getMeltingPoint() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getBoilingPoint() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getSpecHeatSolid() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getSpecHeatLiquid() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getSpecHeatGas() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getHeatOfFusion() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public double getHeatOfVaporization() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxHeatInPerTick() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxHeatOutPerTick() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addHeat(double joules) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeHeat(double joules) {
		// TODO Auto-generated method stub
		return false;
	}
}
