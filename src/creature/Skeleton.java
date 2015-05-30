package creature;

import entity.Creature;
import world.Chunk;

public class Skeleton extends Creature {
	
	public Skeleton(int x, int y, Chunk chunk, String sprite){
		super(x, y, 1, 1, chunk, sprite, "name");
	}

	public static void load() {
	}

}
