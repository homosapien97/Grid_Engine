package terrain;

public interface Thermochemical {
	/*
	 * Temperatures given in kelvin
	 */
	public double getMeltingPoint();
	public double getBoilingPoint();
	/*
	 * Specific heats given in Joules / (tile * kelvin)
	 */
	public double getSpecHeatSolid();
	public double getSpecHeatLiquid();
	public double getSpecHeatGas();
	
	/*
	 * Heats given in Joules / tile
	 */
	public double getHeatOfFusion();
	public double getHeatOfVaporization();
	
	/*
	 * Maximum heats transferable to/from neighbors every tick.
	 */
	public double getMaxHeatInPerTick();
	public double getMaxHeatOutPerTick();
	
	public boolean addHeat(double joules);
	public boolean removeHeat(double joules);
}
