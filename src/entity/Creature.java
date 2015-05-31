package entity;
import ai.Path;
import ai.VisionSquare;
import world.Chunk;

public class Creature extends Entity implements Health, Armored, Mobile, Sighted, Pathing<Creature>{
	public int maxHealth;
	public int health;
	public int natArmor;
	public int ticksPerTile;
	public double fireRes;
	public double earthRes;
	public double waterRes;
	public double plasmaRes;
	public String name;
	public boolean alive;
	
	public Creature (int x, int y,Chunk c, String sprite, int hp, int arm, String name, double fire, double earth, double water, double plasma, int maxActions ){
		super(x, y, c, sprite, maxActions);

		maxHealth = hp;
		health = hp;
		natArmor = arm;
		this.name = name;
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
		return natArmor;
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

	@Override
	public Path<Creature> getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisionSquare vsquare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean goToAbsolute(int absoluteX, int absoluteY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goToRelative(int relativeX, int relativeY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int ticksPerTileWalked() {
		return ticksPerTile;
	}

}
