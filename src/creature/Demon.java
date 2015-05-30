package creature;
import world.Chunk;
import entity.Creature;

public class Demon extends Creature{
	
	public Demon(int x, int y, Chunk chunk, String sprite){
		super(x, y, 1, 1, chunk, sprite, "name");
	}
	public static void load(){
	}

}
