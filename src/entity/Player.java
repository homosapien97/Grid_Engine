package entity;


import action.Action;
import action.MoveAction;
import action.WaitAction;
import ai.Path;
import ai.VisionSquare;

import java.awt.Image;

import core.Core;
import core.GameState;
import entity.interfaces.Armored;
import entity.interfaces.Health;
import entity.interfaces.Mobile;
import entity.interfaces.Pathing;
import entity.interfaces.Sighted;
import spells.Spell;
import terrain.Stone;
import tools.Tools;
import world.Chunk;
import world.LoadedChunks;

public class Player extends Entity implements Health, Armored, Mobile, Sighted, Pathing<Player> {
	public static final Player player = new Player(0, 0, new Chunk(0, 0, true, Stone.get()), "P", 256, 64, "Player", 0.0, 0.0, 0.0, 0.0, 1);
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
	public final VisionSquare visionSquare;
	
	public final Inventory spellInventory = new Inventory();
	public static Spell slectedSpell;
	
	public static final String filename = "player.png";;
	public static Image sprite;
	
	static {
		load();
	}
	
	public Player(int x, int y, Chunk c, String sprite, int hp, int arm, String name, double fire, double earth, double water, double plasma, int ticksPerTile) {
		super(x, y, c, sprite);
//		Camera.init(this);
		
		LoadedChunks.init(c);	//Don't think this needs to exist anymore.
		
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
		visionSquare.trace(super.absoluteX,super.absoluteY); //LC must be init before calling this.
		path = new Path<Player>(this);
		
		for(int i = 0; i < Inventory.spells.length; i++) {
			spellInventory.set(i, 7 - (i % 7));
		}
		
		new WaitAction(this, 0);
	}
	public Player(int x, int y, Chunk c, String sprite) {
		super(x, y, c, sprite);
//		Camera.init(this);
		
		LoadedChunks.init(c);	//Don't think this needs to exist anymore. //NOPE it does
		
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
		synchronized(visionSquare) {
			visionSquare.trace(super.absoluteX,super.absoluteY); //LC must be init before calling this.
		}
		path = new Path<Player>(this);
		
		for(int i = 0; i < Inventory.spells.length; i++) {
			spellInventory.set(i, 7 - (i % 7));
		}
	}
		@Override
	public int getHealth() {
		return health;
	}
	
	@Override
	public int hurt(int damage) {
		health -= damage;
		if(health < 0) {
			alive = false;
//			this.delete();
			Core.gameState = GameState.JUST_QUIT_GAME;
		}
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
			synchronized(chunk.entities) {
				chunk.removeEntity(this);
			}
			chunk = LoadedChunks.chunks[Tools.nav.absCoordToChunkCoord(absoluteX) - LoadedChunks.getTopLeftX()][Tools.nav.absCoordToChunkCoord(absoluteY) - LoadedChunks.getTopLeftY()];
			synchronized(chunk.entities) {
				chunk.addEntity(this);
			}
//			x = Tools.nav.absCoordToMinorCoord(absoluteX);
//			y = Tools.nav.absCoordToMinorCoord(absoluteY);
			super.absoluteX = absoluteX;
			super.absoluteY = absoluteY;
			return true;
		}
		return false;
	}
	
	/**
	 * Same as goToAbsolute, but coordinates are relative to the player
	 */
	@Override
	public boolean goToRelative(int relativeX, int relativeY) {
		return goToAbsolute(super.absoluteX + relativeX, super.absoluteY + relativeY);
	}
	
	
	@Override
	public int ticksPerTileWalked() {
		return ticksPerTile;
	}

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
	public MoveAction<Player> pathTo(int x, int y) {
//		synchronized(actions) {
//			actions.removeIf(s -> ((s instanceof MoveAction) && s.done()));
//			for(Action a : actions) {
//				if(a instanceof MoveAction) return false;
//			}
			if(path.constructPathTo(x, y)) {
//				return addAction(new MoveAction<Player>(this, x, y, true));
				return new MoveAction<Player>(this, x, y, true);
			}
			return null;
//		}
	}
	
	@Override
	public MoveAction<Player> pathToPreview(int x, int y) {
		if(path.constructPathTo(x, y)) {
			return new MoveAction<Player>(this, x, y, false);
		}
		return null;
	}
	@Override
	public boolean cast(Spell s) {
		if(spellInventory.canUse(Inventory.indexOf(s))) {
			spellInventory.use(Inventory.indexOf(s));
			return true;
		}
		return false;
	}
}
