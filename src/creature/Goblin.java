package creature;
import world.Chunk;
import entity.Creature;
import general.Tools;
import java.awt.Image;

public class Goblin extends Creature {
	public static final String filename = "goblin.png";
	public static Image sprite;
	
	public Goblin(int x, int y, Chunk chunk, String sprite){
		super(x, y, 1, 1, chunk, sprite, "name");
	}

	public static void load() {
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
