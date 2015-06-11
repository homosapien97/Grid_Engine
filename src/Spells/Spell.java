package spells;

import entity.Entity;
import geometry.PointCollection;

import java.awt.Image;


public abstract class Spell {
	/**
	 * Each of these should be overriden in subclasses, and can be changed to final. There is really no point in having public static uninstantiated arrays in an abstract class, but they're here as a template.
	 */
	public static int[] casting;
	public static int[] channel;
	public static int[] healing;
	public static int[] damage;
	public static String[] name;
	public static String[] filename;
	public static Image[] sprite;
	public static Spell[] spells;
	
	public int level;
	
//	protected Spell (int cast, int chan, int dam, String name, String filename /*,SpellType type*/){
//		casting = cast;
//		channel = chan;
//		damage = dam;
//		this.name = name;
//		this.filename = name;
//	}
	
	protected Spell(int level) {
		this.level = level;
	}
	
	/**
	 * To be overriden by subclass spells
	 * @param num
	 * @return
	 */
	public static Spell get(int n) {
		return null;
	}
	/**
	 * To be overriden by subclass spells
	 */
	public static void load() {
		return;
	}
	
//	public abstract String getSpriteFileName();
	
	public abstract int casting();
	public abstract int channel();
	public abstract int healing();
	public abstract int damage();
	public abstract String name();
	public abstract String filename();
	public abstract Image sprite();
	
	/**
	 * Returns the area over which the spell is cast given x and y as a point collection, and applies the effect of the spell at the points specified.
	 * @param x the x coordinate at which to cast the spell
	 * @param y the y coordinate at which to cast the spell
	 * @return
	 */
	public abstract PointCollection cast(Entity caster, int x, int y);
	public abstract PointCollection preview(Entity caster, int x, int y);
	
	public String tooltipInfo() {
		return name[level]
				+ ", Damage: " + damage[level]
				+ " Casting: " + casting[level]
				+ " Channel: " + channel[level];
	}
}
