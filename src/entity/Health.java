package entity;

public interface Health {
	public int getHealth();
	public int hurt(int damage); //returns health after hurting
	public int heal(int healing);//returns health after healing
	public boolean isAlive(); //returns true if alive
}
