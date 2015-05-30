package creature;

import entity.Creature;
import world.Chunk;

public class Human extends Creature {
	
	public Human(int x, int y, Chunk chunk, String sprite){
		super(x, y, 1, 1, chunk, sprite, "name");
	}

	public static void load() {
	}

}
