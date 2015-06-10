package spells;
import entity.Entity;
import geometry.Point;
import geometry.PointCollection;

import java.awt.Image;

import tools.Tools;
import world.LoadedChunks;

public class ShieldSpell extends Spell{
	public static final int NUM_LEVELS = 7;
	public static final int[] casting = {1,1,1,1,1,1,0};
	public static final int[] channel = {1,2,3,4,5,6,7};
	public static final int[] trueDamage = {0,0,0,0,0,0,0};
	public static final int[] damage = {0,0,0,0,0,0,0};
	public static final String[] name = {"Shield level 0", "Shield level 1", "Shield level 2", "Shield level 3", "Shield level 4", "Shield level 5", "Shield level 6",};
	public static final String[] filename = {"Shield1.png","Shield2.png","Shield3.png","Shield4.png","Shield5.png","Shield6.png","Shield7.png"};
	public static final Image[] sprite = new Image[7];
	public static final ShieldSpell[] spells = {new ShieldSpell(0), new ShieldSpell(1), new ShieldSpell(2), new ShieldSpell(3), new ShieldSpell(4), new ShieldSpell(5), new ShieldSpell(6)};
	
	public int ticks = 0;
	public static Point[][] area = new Point[3][3];
	public static int[][] oldHeights = new int[3][3];
	
	public ShieldSpell(int level){
//		super(0,0,0,"Shield lv" + level, filenames[level]);
		super(level);
		
	}
	
	
	public static ShieldSpell get(int n) {
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
	public PointCollection cast(Entity caster, int duration, int irrelevant) {
		if(ticks == 0) {
			for(int i = -1; i < 2; i++) {
				for(int j = -1; j < 2; j++) {
					oldHeights[i + 1][j + 1] = LoadedChunks.heightAt(caster.getAbsoluteX() + i, caster.getAbsoluteY() + j);
					if((i != 0 || j != 0) && LoadedChunks.entitiesAt(caster.getAbsoluteX() + i, caster.getAbsoluteY() + j).size() == 0) {
						area[i + 1][j + 1] = new Point(caster.getAbsoluteX() + i, caster.getAbsoluteY() + j);
						LoadedChunks.setHeightAt(caster.getAbsoluteX() + i, caster.getAbsoluteY() + j, 1);
					} else {
						area[i + 1][j + 1] = null;
					}
				}
			}
		}
		if(ticks >= duration) {
			for(int i = -1; i < 2; i++) {
				for(int j = -1; j < 2; j++) {
					LoadedChunks.setHeightAt(caster.getAbsoluteX() + i, caster.getAbsoluteY() + j, oldHeights[i + 1][j + 1]);
				}
			}
			ticks = -1;
		}
		ticks++;
		PointCollection ret = new PointCollection(8);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(area[i][j] != null) ret.add(area[i][j]);
			}
		}
		return ret;
	}


	@Override
	public PointCollection preview(Entity caster, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
