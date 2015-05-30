package creature;
import world.Chunk;
import entity.Creature;
import general.Tools;
import java.awt.Image;

public class Human extends Creature {
	public static final String filename = "human.png";
	public static Image sprite;
	
	public Human(int x, int y, Chunk chunk, String sprite){
		super(x, y, 1, 1, chunk, sprite, "name");
	}

	public static void load(){
		sprite = Tools.img.loadEntitySprite(filename);
	}

}
