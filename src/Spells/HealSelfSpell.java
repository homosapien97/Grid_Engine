package spells;
import entity.Entity;
import entity.interfaces.Health;
import geometry.PointCollection;

import java.awt.Image;

import tools.Tools;

public class HealSelfSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {0,0,0,0,0,0,0};
	public static final int[] channel = {1,1,1,2,2,2,3};
	public static final int[] healing = {1,2,3,4,5,6,7};
	public static final int[] damage = {0,0,0,0,0,0,0};
	public static final String[] name = {"Heal level 0", "Heal level 1", "Heal level 2", "Heal level 3", "Heal level 4", "Heal level 5", "Heal level 6",};
	public static final String[] filename = {"Heal1.png","Heal2.png","Heal3.png","Heal4.png","Heal5.png","Heal6.png","Heal7.png"};
	public static final Image[] sprite = new Image[7];
	public static final HealSelfSpell[] spells = {new HealSelfSpell(0), new HealSelfSpell(1), new HealSelfSpell(2), new HealSelfSpell(3), new HealSelfSpell(4), new HealSelfSpell(5), new HealSelfSpell(6)};
	
	public HealSelfSpell(int level){
//		super(0,0,0,"Plasma lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static HealSelfSpell get(int n) {
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
		if(caster instanceof Health) {
			((Health) caster).heal(healing[level]);
		}
		return PointCollection.blank;
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		return PointCollection.blank;
	}
	
	@Override
	public String tooltipInfo() {
		return name[level]
				+ ", Heals: " + damage[level]
				+ " Casting: " + casting[level]
				+ " Channel: " + channel[level];
	}

}
