package entity.creature;
import world.Chunk;
import world.LoadedChunks;
import entity.Entity;
import entity.Player;
//import general.Tools;


import java.awt.Image;
import java.util.List;

public class Skeleton extends Creature {
	public static final String filename = "skeleton.png";
	public static Image sprite;
	
	public Skeleton(int absoluteX, int absoluteY, Chunk chunk, String sprite, int hp, int arm, double fire, double earth, double water, double plasma){
		super(absoluteX, absoluteY, chunk, sprite, hp, arm, "Skeleton",fire, earth, water, plasma);
	}
	public Skeleton(int absoluteX, int absoluteY, Chunk chunk, String sprite){
		super(absoluteX, absoluteY, chunk, sprite, 10, 1, "Skeleton", 0.10, -0.10, -0.10, 0.10);
		
	}
	/*
	 * ---Flavor Text---
	 * 	This is a level 1 spooky skeleton with a maximum health of 10. It is very scary.
	 */

	public static void load() {
		//sprite = Tools.img.loadCreatureSprite(filename);
	}
	
	private List<Entity> entities;
	@Override
	public void tick() {
		synchronized(super.vsquare()) {
			super.vsquare().trace(getAbsoluteX(), getAbsoluteY());
			entities = LoadedChunks.entitiesIn(super.vsquare().x - super.vsquare().RADIUS, super.vsquare().y - super.vsquare().RADIUS, 
					super.vsquare().x + super.vsquare().RADIUS, super.vsquare().y + super.vsquare().RADIUS);
			for(Entity e : entities) {
				if(e instanceof Player && super.vsquare().canSee(e.getAbsoluteX(), e.getAbsoluteY())) {
					super.pathTo(e.getAbsoluteX(), e.getAbsoluteY());
				}
			}
		}
	}

}
