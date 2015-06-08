package entity.creature;
import spells.BowSpell;
import tools.Tools;
import world.Chunk;
import entity.Player;
import entity.interfaces.Mobile;
import entity.interfaces.Sighted;
import geometry.Point;

import java.awt.Image;

import action.Action;
import action.MoveAction;
import action.SpellAction;
import ai.VisionSquare;

public class Skeleton extends Creature implements Mobile, Sighted {
	public static final String filename = "skeleton.png";
	public static Image sprite;
	public MoveAction<Creature> current = null;
	public static BowSpell bow = BowSpell.get(3);
	public Action action;
	
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
	
	@Override
	public void tick() {
		System.out.println("ticking skelly");
		if(action != null && action.done()){
			System.out.println(">Skelly: Prev action done");
			action = null;
		}
		System.out.println(">Skelly: Attempting to shoot player");
		Point playerPos = new Point(Player.player.getAbsoluteX(), Player.player.getAbsoluteY());
		if(!(action instanceof SpellAction)) {
			for(Point p : bow.preview(this, Player.player.getAbsoluteX(), Player.player.getAbsoluteY())) {
				if(p.equals(playerPos)) {
					action = new SpellAction(bow, this, Player.player.getAbsoluteX(), Player.player.getAbsoluteY(), true);
					break;
				}
			}
		}
		if(action instanceof MoveAction && super.vsquare().canSee(Player.player.getAbsoluteX(), Player.player.getAbsoluteY())) {
			action = null;
		}
		if(action == null) {
			System.out.println(">Skelly: Could not shoot player. Attempting to path to player");
			super.vsquare().trace(super.getAbsoluteX(), super.getAbsoluteY());
			action = super.pathTo(Player.player.getAbsoluteX(), Player.player.getAbsoluteY());
		}
	}
	@Override
	public Image sprite() {
		return sprite;
	}

}
