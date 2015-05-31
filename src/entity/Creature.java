package entity;
import world.Chunk;

public class Creature extends Entity implements Health{
	public int maxHealth;
	public int health;
	public int natArmor;
	public String name;
	public boolean alive;
	
	public Creature (int x, int y, int hp, int arm, Chunk c, String sprite, String name, int maxActions){
		super(x, y, c, sprite, maxActions);
		maxHealth = hp;
		health = hp;
		natArmor = arm;
		this.name = name;
		alive = true;
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

}
