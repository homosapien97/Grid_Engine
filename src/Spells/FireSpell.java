package spells;
import entity.Entity;
import entity.interfaces.Health;
import geometry.Circle;
import geometry.Line;
import geometry.PointCollection;

import java.awt.Image;
import java.util.List;

import tools.Tools;
import world.LoadedChunks;

public class FireSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {0,0,0,0,0,0,0};
	public static final int[] channel = {1,2,3,4,5,6,7};
	public static final int[] trueDamage = {0,0,0,0,0,0,0};
	public static final int[] damage = {1,1,1,2,2,2,3};
	public static final String[] name = {"Fire level 0", "Fire level 1", "Fire level 2", "Fire level 3", "Fire level 4", "Fire level 5", "Fire level 6",};
	public static final String[] filename = {"Fire1.png","Fire2.png","Fire3.png","Fire4.png","Fire5.png","Fire6.png","Fire7.png"};
	public static final Image[] sprite = new Image[7];
	public static final FireSpell[] spells = {new FireSpell(0), new FireSpell(1), new FireSpell(2), new FireSpell(3), new FireSpell(4), new FireSpell(5), new FireSpell(6)};
	
	public static Circle area;
	public static final int[] radius = {1,1,2,3,5,8,13};
	public static final int[] range = {2,5,7,9,11,13,15,};
	
	public FireSpell(int level){
//		super(0,0,0,"Fire lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static FireSpell get(int n) {
		return spells[n];
	}
	
	public static void load() {
//		sprite = Tools.img.loadEntitySprite(filename);
		for(int i = 0; i < NUM_LEVELS; i++) {
			sprite[i] = Tools.img.loadCard(filename[i]);
		}
	}



	@Override
	public int casting() {
		return casting[level];
	}


	@Override
	public int channel() {
		return channel[level];
	}


	@Override
	public int trueDamage() {
		return trueDamage[level];
	}


	@Override
	public int damage() {
		return damage[level];
	}


	@Override
	public String name() {
		return name[level];
	}


	@Override
	public String filename() {
		return filename[level];
	}


	@Override
	public Image sprite() {
		return sprite[level];
	}


	@Override
	public PointCollection cast(Entity caster, int x, int y) {
		if(Tools.nav.orthoDistance(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y) <= range[level]) {
			area = new Circle(x, y, radius[level]);
		} else {
			Line temp = new Line(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
			temp = new Line(temp, radius[level]);
			area = new Circle(temp.b.x, temp.b.y, radius[level]);
		}
		PointCollection ret = new PointCollection(area);
		List<Entity> temp = LoadedChunks.entitiesIn(area);
		for(Entity e : temp) {
			if((e != caster) && (e instanceof Health)) {
				((Health) e).hurt(damage[level]);
			}
		}
		return ret;
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		if(Tools.nav.orthoDistance(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y) <= range[level]) {
			area = new Circle(x, y, radius[level]);
		} else {
			Line temp = new Line(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
			temp = new Line(temp, radius[level]);
			area = new Circle(temp.b.x, temp.b.y, radius[level]);
		}
		PointCollection ret = new PointCollection(area);
		return ret;
	}

}
