package creature;
import world.Chunk;
import entity.Entity;
import entity.Health;

public class Creature extends Entity implements Health{
	public int maxHealth;
	public int health;
	public int natArmor;
	public String name;
	public boolean alive;
	
	public Creature (int x, int y, int hp, int arm, Chunk c, String s, String n){
		super(x, y, c, s);
		maxHealth = hp;
		health = hp;
		natArmor = arm;
		name = n;
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
