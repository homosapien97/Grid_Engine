package entity;

import general.Tools;
import world.Chunk;
import world.LoadedChunks;

public class Player extends Entity implements Health, Armored, Mobile {
	public int maxHealth;
	public int health;
	public int natArmor;
	public int armor;
	public String name;
	public boolean alive;
	public int ticksPerTile;
	
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
	
	/**
	 * For now, this teleports the player instantly to wherever you tell it (within LoadedChunks)
	 */
	@Override
	public boolean goToAbsolute(int absoluteX, int absoluteY) {
		if(LoadedChunks.isLoaded(absoluteX, absoluteY)) {
			chunk.removeEntity(this);
			chunk = LoadedChunks.chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - LoadedChunks.getTopLeftX()][Tools.nav.absCoordToChunkCoord(absoluteY) - LoadedChunks.getTopLeftY()];
			chunk.addEntity(this);
			x = Tools.nav.absCoordToMinorCoord(absoluteX);
			y = Tools.nav.absCoordToMinorCoord(absoluteY);
			return true;
		}
		return false;
	}
	
	/**
	 * Same as goToAbsolute, but coordinates are relative to the player
	 */
	@Override
	public boolean goToRelative(int relativeX, int relativeY) {
		goToAbsolute(getAbsoluteX() + relativeX, getAbsoluteY() + relativeY);
		return false;
	}
	
	
	@Override
	public int ticksPerTileWalked() {
		return ticksPerTile;
	}
	
	//for the use of stepTowards;
	private static double[][] stepChoices = new double[3][3];
	private static double lowest = Integer.MAX_VALUE;
	private static int choices = 0;
	@Override
	public boolean stepTowards(int absoluteX, int absoluteY) {
		lowest = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(LoadedChunks.heightAt(x -1 + i, y - 1 + i) == 0) {
					stepChoices[i][j] = Math.sqrt((x - 1 + i - absoluteX) * (x - 1 + i - absoluteX) + (y - 1 + j - absoluteY) * (y - 1 + j - absoluteY));
				} else {
					stepChoices[i][j] = Integer.MAX_VALUE;
				}
				if(stepChoices[i][j] < lowest) {
					choices = 1;
					lowest = stepChoices[i][j];
					for(int k = 0; k < 3; k++) {
						for(int l = 0; l < 3; l++) {
							if(stepChoices[k][l] == -1) stepChoices[i][j] = -2;
						}
					}
					stepChoices[i][j] = -1;
				} else if(stepChoices[i][j] == lowest) {
					choices++;
					stepChoices [i][j] = -1;
				}
			}
		}
		if(choices == 0) return false;
		//repurposing lowest here
		lowest = Math.random() * choices;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(stepChoices[i][j] == -1) {
					if(lowest < 1) {
						goToRelative(i + 1, j + 1);
						return true;
					}
					lowest -= 1;
				}
			}
		}
		return false;
	}
}
