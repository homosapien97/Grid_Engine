package spells;
import entity.Entity;
import entity.interfaces.Health;
import geometry.PointCollection;
import geometry.Ray;

import java.awt.Image;
import java.util.List;

import tools.Tools;
import world.LoadedChunks;

public class PlasmaSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {0,0,0,0,0,0,0};
	public static final int[] channel = {1,1,1,2,2,2,2};
	public static final int[] trueDamage = {0,0,0,0,0,0,0};
	public static final int[] damage = {1,1,1,1,1,2,2};
	public static final String[] name = {"Plasma level 0", "Plasma level 1", "Plasma level 2", "Plasma level 3", "Plasma level 4", "Plasma level 5", "Plasma level 6",};
	public static final String[] filename = {"Plasma1.png","Plasma2.png","Plasma3.png","Plasma4.png","Plasma5.png","Plasma6.png","Plasma7.png"};
	public static final Image[] sprite = new Image[7];
	public static final PlasmaSpell[] spells = {new PlasmaSpell(0), new PlasmaSpell(1), new PlasmaSpell(2), new PlasmaSpell(3), new PlasmaSpell(4), new PlasmaSpell(5), new PlasmaSpell(6)};
	
	public static Ray area;
	public static final int[] range = {5,6,8,11,15,20,26};
	
	public PlasmaSpell(int level){
//		super(0,0,0,"Plasma lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static PlasmaSpell get(int n) {
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
		area = new Ray(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		area = new Ray(area, range[level]);
		for(int i = 0; i < area.points.length; i++) {
			if(LoadedChunks.heightAt(area.points[i].x, area.points[i].y) > 0) {
				area = new Ray(area, i + 1);
				break;
			}
		}
		List<Entity> temp = LoadedChunks.entitiesIn(area);
		for(Entity e : temp) {
			if((e != caster) && (e instanceof Health)) {
				((Health) e).hurt(damage[level]);
			}
		}
		return new PointCollection(area);
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		area = new Ray(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		area = new Ray(area, range[level]);
		for(int i = 0; i < area.points.length; i++) {
			if(LoadedChunks.heightAt(area.points[i].x, area.points[i].y) > 0) {
				area = new Ray(area, i + 1);
				break;
			}
		}
		return new PointCollection(area);
	}

}
