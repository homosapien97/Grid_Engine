package entity;

import world.Chunk;

public class Player extends Entity implements Health, Armored {
	public int maxHealth;
	public int health;
	public int natArmor;
	public int armor;
	public String name;
	public boolean alive;
	
	public Player(int x, int y, int hp, int arm, Chunk c, String s, String n) {
		super(x, y, c, s);
		maxHealth = hp;
		health = hp;
		natArmor = arm;
		armor = arm;
		name = n;
		alive = true;
		
	}

	@Override
	public int getFlatArmor() {
		return armor;
	}

	@Override
	public int getProportionalArmor() {
		// TODO Auto-generated method stub
		return 0;
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
