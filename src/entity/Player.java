package entity;

import general.Methods;
import world.Chunk;
import world.LoadedChunks;

public class Player extends Entity implements Health, Armored, Mobile {
	public int maxHealth;
	public int health;
	public int natArmor;
	public int armor;
	public String name;
	public boolean alive;
	
	public Player(int x, int y, Chunk c, String s, int hp, int arm, String n) {
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
		//TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getHealth() {
		return health;
	}
	
	@Override
	public int hurt(int damage) {
		health -= damage;
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
	/**
	 * For now, this teleports the player instantly to wherever you tell it (within LoadedChunks)
	 */
	public boolean goToAbsolute(int absoluteX, int absoluteY) {
		if(LoadedChunks.isLoaded(absoluteX, absoluteY)) {
			chunk = LoadedChunks.chunks[Methods.absCoordToChunkCoord(absoluteX) - LoadedChunks.getTopLeftX()][Methods.absCoordToChunkCoord(absoluteY) - LoadedChunks.getTopLeftY()];
			x = Methods.absCoordToMinorCoord(absoluteX);
			y = Methods.absCoordToMinorCoord(absoluteY);
			return true;
		}
		return false;
	}

	@Override
	public boolean goToRelative(int relativeX, int relativeY) {
		goToAbsolute(getAbsoluteX() + relativeX, getAbsoluteY() + relativeY);
		return false;
	}

	@Override
	public int ticksPerTileWalked() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean stepTowards(int absoluteX, int absoluteY) {
		// TODO Auto-generated method stub
		return false;
	}
}
