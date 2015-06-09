package spells;

import entity.Entity;
import entity.interfaces.Health;
import geometry.PointCollection;
import geometry.Ray;

import java.awt.Image;
import java.util.List;

import tools.Tools;
import world.LoadedChunks;

public class BowSpell extends Spell {
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {0,0,0,0,0,0,0};
	public static final int[] channel = {1,1,1,1,1,1,1};
	public static final int[] trueDamage = {1,1,1,1,1,1,1};
	public static final int[] damage = {1,2,2,3,3,3,4};
	public static final String[] name = {"Bow level 0", "Bow level 1", "Bow level 2", "Bow level 3", "Bow level 4", "Bow level 5", "Bow level 6",};
	public static final String[] filename = {"Bow1.png","Bow2.png","Bow3.png","Bow4.png","Bow5.png","Bow6.png","Bow7.png"};
	public static final Image[] sprite = new Image[7];
	public static final BowSpell[] spells = {new BowSpell(0), new BowSpell(1), new BowSpell(2), new BowSpell(3), new BowSpell(4), new BowSpell(5), new BowSpell(6)};
	
	public static Ray area;
	public static final int[] range = {4,6,8,10,12,14,16};

	

	
	protected BowSpell(int level){
//		super(0,0,0,"Bow lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static BowSpell get(int n) {
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
		area = new Ray(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		area = new Ray(area, range[level]);
		List<Entity> temp;
		int tempi;
		for(int i = 1; i < area.points.length; i++) {
			if(LoadedChunks.heightAt(area.points[i].x, area.points[i].y) > 0) {
				area = new Ray(area, i + 1);
				break;
			}
			temp = LoadedChunks.entitiesAt(area.points[i].x, area.points[i].y);
			if(temp.size() > 0) {
				System.out.println(">Bow: It's a hit!");
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
				
				area = new Ray(area, i + 1);
				break;
			}
		}
		return new PointCollection(area);
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		area = new Ray(caster.getAbsoluteX(), caster.getAbsoluteY(), x, y);
		area = new Ray(area, range[level]);
		for(int i = 1; i < area.points.length; i++) {
			if(LoadedChunks.heightAt(area.points[i].x, area.points[i].y) > 0) {
				area = new Ray(area, i + 1);
				break;
			}
			if(LoadedChunks.entitiesAt(area.points[i].x,  area.points[i].y).size() > 0) {
				area = new Ray(area, i + 1);
				break;
			}
		}
		return new PointCollection(area);
	}

}
