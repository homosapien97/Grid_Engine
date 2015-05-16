package run;

import display.Camera;
import display.Display;
import display.TextDisplay;
import entity.Player;
import terrain.Quicksand;
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
		Camera.init(new Player(0,0,testChunk, "0"));
		LoadedChunks.init(testChunk);
	}
	
	public static void main(String[] args) {
		init();
		step();
//		System.out.println(testChunk);
//		System.out.println(LoadedChunks.chunks[LoadedChunks.RADIUS][LoadedChunks.RADIUS]);
//		System.out.println(LoadedChunks.chunks[LoadedChunks.RADIUS + 1][LoadedChunks.RADIUS + 1]);
//		System.out.println();
//		for(int i = -35; i < 35; i++) {
//			System.out.print(LoadedChunks.spriteAt(i, 0));
//		}
//		System.out.println();
//		System.out.println(LoadedChunks.center.neighbors[1][2]);
	}
}
