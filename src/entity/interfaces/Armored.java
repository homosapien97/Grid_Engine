package entity.interfaces;

public interface Armored {
	//-------------------------
	//Other Resistance
	//-------------------------
	public int getFlatArmor();
	public int getProportionalArmor();
	//--------------------------------
	//Elemental Resistance
	//--------------------------------
	public double getResistanceFire();
	public double getResistanceEarth();
	public double getResistanceWater();
	public double getResistancePlasma();
//	public int getResistanceLight();
//	public int getResistanceDark();
//	public int getResistanceElectric();
//	public int getResistancePoison();
//	public int getResistanceHeal();
}
