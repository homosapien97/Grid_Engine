package magic;

import java.awt.Image;


public abstract class Spell {
	/**
	 * Each of these should be overriden in subclasses
	 */
	public static int[] casting;
	public static int[] channel;
	public static int[] trueDamage;
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
	public abstract int trueDamage();
	public abstract int damage();
	public abstract String name();
	public abstract String filename();
	public abstract Image sprite();
	
}
