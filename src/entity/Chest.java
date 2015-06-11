package entity;

import java.awt.Image;

import entity.interfaces.Intelligent;
import spells.Inventory;
import spells.Spell;
import tools.Tools;
import world.Chunk;

public class Chest extends Entity implements Intelligent{
	public static final String filename = "chest.png";
	public static Image sprite;
	public Inventory inventory;
	
	public Chest(int absoluteX, int absoluteY, Chunk chunk, Inventory inventory) {
		super(absoluteX, absoluteY, chunk, filename);
		this.inventory = inventory;
	}
	
	@Override
	public Image sprite() {
		return sprite;
	}
	
	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

	@Override
	public void tick() {
		if(super.absoluteX == Player.player.absoluteX && super.absoluteY == Player.player.absoluteY) {
			Player.player.spellInventory.add(inventory);
			this.flagForRemoval();
		}
	}

	@Override
	public boolean cast(Spell s) {
		return false;
	}
}
