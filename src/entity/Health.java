package entity;

public interface Health {
	public double getHealth();
	public double hurt(double damage); //returns health after hurting
	public double heal(double healing);//returns health after healing
}
