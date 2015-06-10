package spells;
import entity.Entity;
import entity.interfaces.Health;
import geometry.Line;
import geometry.PointCollection;

import java.awt.Image;
import java.util.List;

import tools.Tools;
import world.LoadedChunks;

public class EarthSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {1,1,1,1,1,1,1};
	public static final int[] channel = {0,0,0,0,0,0,0};
	public static final int[] trueDamage = {0,0,0,0,0,0,0};
	public static final int[] damage = {2,3,3,4,4,4,5};
	public static final String[] name = {"Earth level 0", "Earth level 1", "Earth level 2", "Earth level 3", "Earth level 4", "Earth level 5", "Earth level 6",};
	public static final String[] filename = {"Earth1.png","Earth2.png","Earth3.png","Earth4.png","Earth5.png","Earth6.png","Earth7.png"};
	public static final Image[] sprite = new Image[7];
	public static final EarthSpell[] spells = {new EarthSpell(0), new EarthSpell(1), new EarthSpell(2), new EarthSpell(3), new EarthSpell(4), new EarthSpell(5), new EarthSpell(6)};
	
	public static Line area;
	public static final int[] range = {4,5,6,7,8,9,10};
	
	public EarthSpell(int level){
//		super(0,0,0,"Earth lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static EarthSpell get(int n) {
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
	/**
	 * Stops at first entity the spell hits on its way from caster to x, y . If there are multiple entities at the first site of collision, it picks one of them randomly.
	 * The entity picked, if it implements Health, is hurt for damage[this.level] .
	 */
	public PointCollection cast(Entity caster, int x, int y) {
		//This doesn't pass thru enemies. Maybe for higher levels it should? Or should that be a separate spell?
		area = new Line(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		area = new Line(area, range[level]);
		List<Entity> temp;
		int tempi;
		for(int i = 1; i < area.points.length; i++) {
			if(LoadedChunks.heightAt(area.points[i].x, area.points[i].y) > 0) {
				area = new Line(area, i + 1);
				break;
			}
			temp = LoadedChunks.entitiesAt(area.points[i].x, area.points[i].y);
			if(temp.size() > 0) {
				if(temp.size() == 1) {
					if(temp.get(0) instanceof Health) {
						((Health) temp.get(0)).hurt(damage[level]);
					}
				} else {
					tempi = (int)(Math.random() * temp.size());
					if(temp.get(tempi) instanceof Health) {
						((Health) temp.get(tempi)).hurt(damage[level]);
					}
				}
				
				area = new Line(area, i + 1);
				break;
			}
		}
		return new PointCollection(area);
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		area = new Line(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		area = new Line(area, range[level]);
		for(int i = 1; i < area.points.length; i++) {
			if(LoadedChunks.heightAt(area.points[i].x, area.points[i].y) > 0) {
				area = new Line(area, i + 1);
				break;
			}
			if(LoadedChunks.entitiesAt(area.points[i].x,  area.points[i].y).size() > 0) {
				area = new Line(area, i + 1);
				break;
			}
		}
		return new PointCollection(area);
	}

}
