package entity;


//import display.Camera;
import action.Action;
import action.MoveAction;
import ai.Path;
import ai.VisionSquare;

import java.awt.Image;

import spells.BowSpell;
import spells.EarthSpell;
import spells.FireSpell;
import spells.PlasmaSpell;
import spells.ShieldSpell;
import spells.SwordSpell;
import spells.WaterSpell;
import terrain.Stone;
import magic.Spell;
import general.Tools;
import world.Chunk;
import world.LoadedChunks;

public class Player extends Entity implements Health, Armored, Mobile, Sighted, Pathing<Player> {
	public static final Player player = new Player(0, 0, new Chunk(0, 0, true, Stone.get()), "P", 256, 64, "Player", 0.0, 0.0, 0.0, 0.0, 1, 1);
	public int maxHealth;
	public int health;
	public int natArmor;
	public int armor;
	public int ticksPerTile;
	public double fireRes;
	public double earthRes;
	public double waterRes;
	public double plasmaRes;
	public final String name;
	public boolean alive;
	public final Path<Player> path;
	
//	public final Spell[] spellInventory = new Spell[49];
	public final Spell[] spellInventory = {
		BowSpell.get(0),	//0
		BowSpell.get(1),
		BowSpell.get(2),
		BowSpell.get(3),
		BowSpell.get(4),
		BowSpell.get(5),
		BowSpell.get(6),
		EarthSpell.get(0),	//7
		EarthSpell.get(1),
		EarthSpell.get(2),
		EarthSpell.get(3),
		EarthSpell.get(4),
		EarthSpell.get(5),
		EarthSpell.get(6),
		FireSpell.get(0),	//14
		FireSpell.get(1),
		FireSpell.get(2),
		FireSpell.get(3),
		FireSpell.get(4),
		FireSpell.get(5),
		FireSpell.get(6),
		PlasmaSpell.get(0),	//21
		PlasmaSpell.get(1),
		PlasmaSpell.get(2),
		PlasmaSpell.get(3),
		PlasmaSpell.get(4),
		PlasmaSpell.get(5),
		PlasmaSpell.get(6),
		ShieldSpell.get(0),	//28
		ShieldSpell.get(1),
		ShieldSpell.get(2),
		ShieldSpell.get(3),
		ShieldSpell.get(4),
		ShieldSpell.get(5),
		ShieldSpell.get(6),
		SwordSpell.get(0),	//35
		SwordSpell.get(1),
		SwordSpell.get(2),
		SwordSpell.get(3),
		SwordSpell.get(4),
		SwordSpell.get(5),
		SwordSpell.get(6),
		WaterSpell.get(0),	//42
		WaterSpell.get(1),
		WaterSpell.get(2),
		WaterSpell.get(3),
		WaterSpell.get(4),
		WaterSpell.get(5),
		WaterSpell.get(6),
	};
	

	public final VisionSquare visionSquare;

	public static final String filename = "player.png";;
	public static Image sprite;
	
	public static Spell slectedSpell;
	
	static {
		load();
	}
	
	public Player(int x, int y, Chunk c, String sprite, int hp, int arm, String name, double fire, double earth, double water, double plasma, int ticksPerTile, int maxActions) {
		super(x, y, c, sprite, maxActions);
//		Camera.init(this);
		LoadedChunks.init(c);
		maxHealth = hp;
		health = hp;
		natArmor = arm;
		armor = arm;
		this.name = name;
		alive = true;
		fireRes = fire;
		earthRes = earth;
		waterRes = water;
		plasmaRes = plasma;
		
		this.ticksPerTile = ticksPerTile;

		visionSquare = VisionSquare.r21;
		visionSquare.trace(getAbsoluteX(),getAbsoluteY()); //LC must be init before calling this.
		path = new Path<Player>(this);
	}
	public Player(int x, int y, Chunk c, String sprite) {
		super(x, y, c, sprite, 1);
//		Camera.init(this);
		LoadedChunks.init(c);
		maxHealth = 10;
		health = 10;
		natArmor = 0;
		armor = 0;
		this.name = "Aeneas";
		alive = true;
		fireRes = 0.00;
		earthRes = 0.00;
		waterRes = 0.00;
		plasmaRes = 0.00;
		
		ticksPerTile = 1;
		
		visionSquare = VisionSquare.r21;
		visionSquare.trace(getAbsoluteX(),getAbsoluteY()); //LC must be init before calling this.
		path = new Path<Player>(this);
	}
		@Override
	public int getHealth() {
		return health;
	}
	
	@Override
	public int hurt(int damage) {
		health -= damage;
		if(health - maxHealth < -10)
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
//		System.out.println("gtr " + relativeX + ", " + relativeY);
		goToAbsolute(getAbsoluteX() + relativeX, getAbsoluteY() + relativeY);
		return false;
	}
	
	
	@Override
	public int ticksPerTileWalked() {
		return ticksPerTile;
	}
	
//	//for the use of stepTowards;
//	private static double[][] stepChoices = new double[3][3];
//	private static double lowest = Integer.MAX_VALUE;
//	private static int choices = 0;
//	@Override
//	public boolean moveTowards(int absoluteX, int absoluteY) {
////		lowest = Integer.MAX_VALUE;
////		for(int i = 0; i < 3; i++) {
////			for(int j = 0; j < 3; j++) {
////				if(LoadedChunks.heightAt(x -1 + i, y - 1 + i) == 0) {
////					stepChoices[i][j] = Math.sqrt((x - 1 + i - absoluteX) * (x - 1 + i - absoluteX) + (y - 1 + j - absoluteY) * (y - 1 + j - absoluteY));
////				} else {
////					stepChoices[i][j] = Integer.MAX_VALUE;
////				}
////				if(stepChoices[i][j] < lowest) {
////					choices = 1;
////					lowest = stepChoices[i][j];
////					for(int k = 0; k < 3; k++) {
////						for(int l = 0; l < 3; l++) {
////							if(stepChoices[k][l] == -1) stepChoices[i][j] = -2;
////						}
////					}
////					stepChoices[i][j] = -1;
////				} else if(stepChoices[i][j] == lowest) {
////					choices++;
////					stepChoices [i][j] = -1;
////				}
////			}
////		}
////		if(choices == 0) return false;
////		//repurposing lowest here
////		lowest = Math.random() * choices;
////		for(int i = 0; i < 3; i++) {
////			for(int j = 0; j < 3; j++) {
////				if(stepChoices[i][j] == -1) {
////					if(lowest < 1) {
////						goToRelative(i + 1, j + 1);
////						return true;
////					}
////					lowest -= 1;
////				}
////			}
////		}
////		return false;
//	}

	@Override
	public VisionSquare vsquare() {
		return visionSquare;
	}
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}
	@Override
	public Image sprite() {
		return sprite;
	}
	@Override
	public Path<Player> getPath() {
		return path;
	}
	@Override
	public boolean pathTo(int x, int y) {
//		for(Action a : actions) {
//			if(a instanceof MoveAction) {
//				if(a.done()) {
//					actions.remove(a);
////					return addAction(new MoveAction<Player>(this, x, y));
//				} else return false;
//			}
//		}
		synchronized(actions) {
			actions.removeIf(s -> ((s instanceof MoveAction) && s.done()));
			for(Action a : actions) {
				if(a instanceof MoveAction) return false;
			}
			if(path.constructPathTo(x, y)) {
				return addAction(new MoveAction<Player>(this, x, y, true));
			}
			return false;
		}
	}
	public MoveAction<Player> pathToPreview(int x, int y) {
		if(path.constructPathTo(x, y)) {
			return new MoveAction<Player>(this, x, y, false);
		}
		return null;
	}
}
