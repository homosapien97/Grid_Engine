package spells;
import entity.Entity;
import entity.interfaces.Health;
import geometry.Point;
import geometry.PointCollection;

import java.awt.Image;
import java.util.List;

import tools.Tools;
import world.LoadedChunks;

public class SwordSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {0,0,0,0,0,0,0};
	public static final int[] channel = {1,1,1,1,1,1,1};
	public static final int[] healing = {0,0,0,0,0,0,0};
	public static final int[] damage = {4,5,5,6,6,7,8};
	public static final String[] name = {"Sword level 0", "Sword level 1", "Sword level 2", "Sword level 3", "Sword level 4", "Sword level 5", "Sword level 6",};
	public static final String[] filename = {"Sword1.png","Sword2.png","Sword3.png","Sword4.png","Sword5.png","Sword6.png","Sword7.png"};
	public static final Image[] sprite = new Image[7];
	public static final SwordSpell[] spells = {new SwordSpell(0), new SwordSpell(1), new SwordSpell(2), new SwordSpell(3), new SwordSpell(4), new SwordSpell(5), new SwordSpell(6)};
	
	public static Point area;
	
	public SwordSpell(int level){
//		super(0,0,0,"Sword lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static SwordSpell get(int n) {
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
	public int healing() {
		return healing[level];
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
//		area = new Point(x, y);
		List<Entity> temp;
		if(Math.abs(caster.getAbsoluteX() - x) < 2 && Math.abs(caster.getAbsoluteY() - y) < 2) {
			area = new Point(x, y);
			temp = LoadedChunks.entitiesAt(x, y);
		} else {
			area = new Point(caster.getAbsoluteX(), caster.getAbsoluteX());
			temp = LoadedChunks.entitiesAt(caster.getAbsoluteX(), caster.getAbsoluteY());
		}
		if(temp.size() > 0) {
			int tempi = (int)(Math.random() * temp.size());
			if((temp.get(tempi) != caster) && (temp.get(tempi) instanceof Health)) {
				((Health) temp.get(tempi)).hurt(damage[level]);
			}
		}
		return new PointCollection(area);
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		area = new Point(x, y);
		if(Math.abs(caster.getAbsoluteX() - x) < 2 && Math.abs(caster.getAbsoluteY() - y) < 2) {
			area = new Point(x, y);
		} else {
			area = new Point(caster.getAbsoluteX(), caster.getAbsoluteX());
		}
		return new PointCollection(area);
	}
	
	@Override
	public String tooltipInfo() {
		return name[level]
				+ ", Damage: " + damage[level]
				+ " Casting: " + casting[level]
				+ " Channel: " + channel[level];
	}

}
