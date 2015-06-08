package entity.creature;
import spells.BowSpell;
import tools.Tools;
import world.Chunk;
import entity.Player;
import entity.interfaces.Mobile;
import entity.interfaces.Sighted;
import geometry.Point;

import java.awt.Image;

import action.MoveAction;
import action.SpellAction;
import ai.VisionSquare;

public class Skeleton extends Creature implements Mobile, Sighted {
	public static final String filename = "skeleton.png";
	public static Image sprite;
	public MoveAction<Creature> current = null;
	public static BowSpell bow = BowSpell.get(5);
	public SpellAction shot;
	
	public Skeleton(int absoluteX, int absoluteY, Chunk chunk, int hp, int arm, double fire, double earth, double water, double plasma){
		super(absoluteX, absoluteY, chunk, filename, hp, arm, 2, VisionSquare.r15, fire, earth, water, plasma, "Skeleton");
	}
	public Skeleton(int absoluteX, int absoluteY, Chunk chunk){
		super(absoluteX, absoluteY, chunk, filename, 10, 1, 2, VisionSquare.r15, 0.10, -0.10, -0.10, 0.10, "Skeleton");
		
	}
	/*
	 * ---Flavor Text---
	 * 	This is a level 1 spooky skeleton with a maximum health of 10. It is very scary.
	 */

	public static void load() {
		sprite = Tools.img.loadCreatureSprite(filename);
	}
	
//	private List<Entity> entities;
	private boolean followingPlayer = false;
	@Override
	public void tick() {
		System.out.println("ticking skelly");
		if(!followingPlayer) {
			synchronized(super.vsquare()) {
				super.vsquare().trace(getAbsoluteX(), getAbsoluteY());
//				entities = LoadedChunks.entitiesIn(super.vsquare().x - super.vsquare().RADIUS, super.vsquare().y - super.vsquare().RADIUS, 
//						super.vsquare().x + super.vsquare().RADIUS, super.vsquare().y + super.vsquare().RADIUS);
//				for(Entity e : entities) {
//					if(e instanceof Player && super.vsquare().canSee(e.getAbsoluteX(), e.getAbsoluteY())) {
//						followingPlayer = super.pathTo(e.getAbsoluteX(), e.getAbsoluteY());
//						break;
//					}
//				}
				followingPlayer = super.pathTo(Player.player.getAbsoluteX(), Player.player.getAbsoluteY());
			}
		} else {
			if(shot == null) {
				System.out.println("Skelly: Should I shoot?");
				for(Point p : bow.preview(this, Player.player.getAbsoluteX(), Player.player.getAbsoluteY())) {
					if(p.equals(new Point(Player.player.getAbsoluteX(), Player.player.getAbsoluteY()))) {
						System.out.println("Skelly: Yes!");
						shot = new SpellAction(bow, this, Player.player.getAbsoluteX(), Player.player.getAbsoluteY(), true);
						followingPlayer = false;
					}
				}
			} else if(shot.done()) shot = null;
		}
		
	}
	@Override
	public Image sprite() {
		return sprite;
	}

}
