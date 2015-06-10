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

public class WaterSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {0,0,0,0,0,0,0};
	public static final int[] channel = {1,2,3,3,4,4,5};
	public static final int[] trueDamage = {0,0,0,0,0,0,0};
	public static final int[] damage = {1,1,1,1,1,1,1};
	public static final String[] name = {"Water level 0", "Water level 1", "Water level 2", "Water level 3", "Water level 4", "Water level 5", "Water level 6",};
	public static final String[] filename = {"Water1.png","Water2.png","Water3.png","Water4.png","Water5.png","Water6.png","Water7.png"};
	public static final Image[] sprite = new Image[7];
	public static final WaterSpell[] spells = {new WaterSpell(0), new WaterSpell(1), new WaterSpell(2), new WaterSpell(3), new WaterSpell(4), new WaterSpell(5), new WaterSpell(6)};
	
	public static Line areaL;
	public static Circle areaC;
	public static int[] radius = {1,1,2,2,3,3,4};
	public static int[] range = {3,4,5,6,7,8,9};
	
	public WaterSpell(int level){
//		super(0,0,0,"Water lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static WaterSpell get(int n) {
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
		areaL = new Line(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		areaL = new Line(areaL, range[level]);
		areaC = null;
		List<Entity> temp;
		for(int i = 1; i < areaL.points.length; i++) {
			if(LoadedChunks.heightAt(areaL.points[i].x, areaL.points[i].y) > 0) {
				areaL = new Line(areaL, i + 1);
				areaC = new Circle(areaL.b.x, areaL.b.y, radius[level]);
				temp = LoadedChunks.entitiesIn(areaC);
				for(Entity e : temp) {
					if((e != caster) && (e instanceof Health)) {
						((Health) e).hurt(damage[level]);
					}
				}
				break;
			}
			temp = LoadedChunks.entitiesAt(areaL.points[i].x, areaL.points[i].y);
			if(temp.size() > 0) {
				areaL = new Line(areaL, i + 1);
				areaC = new Circle(areaL.b.x, areaL.b.y, radius[level]);
				temp = LoadedChunks.entitiesIn(areaC);
				for(Entity e : temp) {
					if((e != caster) && (e instanceof Health)) {
						((Health) e).hurt(damage[level]);
					}
				}
				break;
			}
		}
		if(areaC == null) {
			areaC = new Circle(areaL.b.x, areaL.b.y, radius[level]);
		}
		PointCollection ret = new PointCollection(areaL);
		ret.addAll(new PointCollection(areaC));
		return ret;
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		areaL = new Line(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		areaL = new Line(areaL, range[level]);
		for(int i = 1; i < areaL.points.length; i++) {
			if(LoadedChunks.heightAt(areaL.points[i].x, areaL.points[i].y) > 0) {
				areaL = new Line(areaL, i + 1);
//				areaC = new Circle(areaL.b.x, areaL.b.y, radius[level]);
				break;
			}
			if(LoadedChunks.entitiesAt(areaL.points[i].x, areaL.points[i].y).size() > 0) {
				areaL = new Line(areaL, i + 1);
//				areaC = new Circle(areaL.b.x, areaL.b.y, radius[level]);
				break;
			}
		}
		areaC = new Circle(areaL.b.x, areaL.b.y, radius[level]);
		PointCollection ret = new PointCollection(areaL);
		ret.addAll(new PointCollection(areaC));
		return ret;
	}

}
