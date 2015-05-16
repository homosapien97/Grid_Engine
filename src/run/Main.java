package run;

import display.Camera;
import display.TextDisplay;
import entity.Player;
import terrain.Stone;
import world.Chunk;
import world.LoadedChunks;

public class Main {
//	Chunk blank = new Chunk();
	static Chunk testChunk = new Chunk(0,0,true,Stone.get());
//	LoadedChunks chunks = new LoadedChunks(testChunk);
//	Camera camera;
//	Display display;
//	TextDisplay debugDisplay;
	
	public static void step() {
		stepState();
		stepGFX();
	}
	
	private static void stepGFX() {
		TextDisplay.print();
	}
	private static void stepState() {
		
	}
	private static void init() {
		Camera.init(new Player(0,0,testChunk, "0", 256, 64, "Player"));
		LoadedChunks.init(testChunk);
	}
	
	public static void main(String[] args) {
		init();
		step();
	}
}
