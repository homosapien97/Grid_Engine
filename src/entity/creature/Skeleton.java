package entity.creature;
import spells.BowSpell;
import tools.Tools;
import world.Chunk;
import world.LoadedChunks;
import entity.Entity;
import entity.Player;
//import general.Tools;



import entity.interfaces.Mobile;
import entity.interfaces.Sighted;
import geometry.Point;

import java.awt.Image;
import java.util.List;

import action.MoveAction;
import action.SpellAction;
import ai.VisionSquare;

public class Skeleton extends Creature implements Mobile, Sighted {
	public static final String filename = "skeleton.png";
	public static Image sprite;
	public MoveAction<Creature> current = null;
	public static BowSpell bow = BowSpell.get(5);
	public SpellAction shot;
	
	public Skeleton(int absoluteX, int absoluteY, Chunk chunk, String sprite, int hp, int arm, double fire, double earth, double water, double plasma){
		super(absoluteX, absoluteY, chunk, sprite, hp, arm, "Skeleton",fire, earth, water, plasma, VisionSquare.r15);
	}
	public Skeleton(int absoluteX, int absoluteY, Chunk chunk, String sprite){
		super(absoluteX, absoluteY, chunk, sprite, 10, 1, "Skeleton", 0.10, -0.10, -0.10, 0.10, VisionSquare.r15);
		
	}
	/*
	 * ---Flavor Text---
	 * 	This is a level 1 spooky skeleton with a maximum health of 10. It is very scary.
	 */

	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}
	
	private List<Entity> entities;
	private boolean followingPlayer = false;
	@Override
	public void tick() {
		if(!(followingPlayer || shot == null)) {
			synchronized(super.vsquare()) {
				super.vsquare().trace(getAbsoluteX(), getAbsoluteY());
				entities = LoadedChunks.entitiesIn(super.vsquare().x - super.vsquare().RADIUS, super.vsquare().y - super.vsquare().RADIUS, 
						super.vsquare().x + super.vsquare().RADIUS, super.vsquare().y + super.vsquare().RADIUS);
				for(Entity e : entities) {
					if(e instanceof Player && super.vsquare().canSee(e.getAbsoluteX(), e.getAbsoluteY())) {
						followingPlayer = super.pathTo(e.getAbsoluteX(), e.getAbsoluteY());
						break;
					}
				}
			}
		} else {
			if(shot.done()) {
				for(Point p : bow.preview(this, Player.player.getAbsoluteX(), Player.player.getAbsoluteY())) {
					if(p.equals(new Point(Player.player.getAbsoluteX(), Player.player.getAbsoluteY()))) {
						shot = new SpellAction(bow, this, Player.player.getAbsoluteX(), Player.player.getAbsoluteY(), true);
					}
				}
			}
		}
		
	}

}
