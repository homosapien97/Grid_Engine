package entity.creature;
import entity.Entity;
//import entity.Player;
import entity.interfaces.Armored;
import entity.interfaces.Health;
import entity.interfaces.Intelligent;
import entity.interfaces.Mobile;
import entity.interfaces.Pathing;
import entity.interfaces.Sighted;
import action.MoveAction;
import ai.Path;
import ai.VisionSquare;
import tools.Tools;
import world.Chunk;
import world.LoadedChunks;

public abstract class Creature extends Entity implements Health, Armored, Mobile, Sighted, Intelligent, Pathing<Creature>{
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
	
	public Path<Creature> path = null;
	public VisionSquare visionSquare;
	
	public Creature (int x, int y,Chunk c, String sprite, int hp, int arm, int ticksPerTile, VisionSquare vsquare, double fire, double earth, double water, double plasma, String name){
		super(x, y, c, sprite);

		maxHealth = hp;
		health = hp;
		natArmor = arm;
		this.ticksPerTile = ticksPerTile;
		alive = true;
		fireRes = fire;
		earthRes = earth;
		waterRes = water;
		plasmaRes = plasma;
		this.name = name;
		
		visionSquare = vsquare;
		synchronized(visionSquare) {
			visionSquare.trace(super.absoluteX, super.absoluteY);
		}
		path = new Path<Creature>(this);
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int hurt(int hurts) {
		health -= hurts;
//		if(maxHealth - health < -10)
//			alive = false;
		if(health < 0) {
			alive = false;
			this.delete();
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
		return path;
	}

	@Override
	public VisionSquare vsquare() {
		return visionSquare;
	}

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
			super.absoluteX = absoluteX;
			super.absoluteY = absoluteY;
			return true;
		}
		return false;
	}

	@Override
	public boolean goToRelative(int relativeX, int relativeY) {
		return goToAbsolute(super.absoluteX + relativeX, super.absoluteY + relativeY);
	}

	@Override
	public int ticksPerTileWalked() {
		return ticksPerTile;
	}

	@Override
	public MoveAction<Creature> pathTo(int x, int y) {
		if(path.constructPathTo(x, y)) {
			return new MoveAction<Creature>(this, x, y, true);
		}
		return null;
	}
	
	@Override
	public MoveAction<Creature> pathToPreview(int x, int y) {
		if(path.constructPathTo(x, y)) {
			return new MoveAction<Creature>(this, x, y, false);
		}
		return null;
	}
}
