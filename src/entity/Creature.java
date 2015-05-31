package entity;
import world.Chunk;

public class Creature extends Entity implements Health, Armored, Mobile, Sighted, Pathing{
	public int maxHealth;
	public int health;
	public int natArmor;
	public double fireRes;
	public double earthRes;
	public double waterRes;
	public double plasmaRes;
	public String name;
	public boolean alive;
	
	public Creature (int x, int y, int hp, int arm, Chunk c, String s, String n,double fire, double earth, double water, double plasma){
		super(x, y, c, s);
		maxHealth = hp;
		health = hp;
		natArmor = arm;
		name = n;
		alive = true;
		fireRes = fire;
		earthRes = earth;
		waterRes = water;
		plasmaRes = plasma;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int hurt(int hurts) {
		health -= hurts;
		if(maxHealth - health < -10)
			alive = false;
		return health;
	}

	@Override
	public int heal(int heals) {
		if(health != maxHealth)
			health += heals;
		if(maxHealth - health < 0)
			health = maxHealth;
		return health;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	@Override
	public int getFlatArmor() {
		return armor;
	}
	
	@Override
	public int getProportionalArmor() {
		//TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getResistanceFire() {
		return fireRes;
	}
	@Override
	public double getResistanceEarth() {
		return earthRes;
	}
	@Override
	public double getResistanceWater() {
		return waterRes;
	}
	@Override
	public double getResistancePlasma() {
		return plasmaRes;
	}

}
