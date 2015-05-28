package run;

import display.Camera;
import core.*;
import entity.Player;
import general.Tools;
import generation.GenWall;
import terrain.Stone;
import world.Chunk;
import world.LoadedChunks;

public class Main {
	//example chunk
	static Chunk testChunk = new Chunk(0, 0, true, Stone.get());
	
	//player object
	static Player player = new Player(0, 0, testChunk, "P", 256, 64, "Player");
	
	//for debug for now
	public static final String version = "1.2";
	
	//Main Program
	public static void main(String[] args) {
		//initializion
		init();
		
		//main loop
		while(Core.gameState != GameState.EXITING){
			switch(Core.gameState){
				case PLAYING:
					for(int i = 0; i < 10; i++) {
						step();
						Tools.time.wait(500);
					}
					Tools.time.wait(5000);
					break;
				default:
					//idle cpu
					Tools.time.wait(1);
					break;
			}
		}
		
		System.out.println(Core.frame.getSize());
	}
		
	//Initialization
	public static void init() {
		//player init
//		Camera.init(player);
//		LoadedChunks.init(testChunk);
		//These player init functions have been moved to the player constructor.
		
		//startup
//		testChunk.heightmap[10][10] = 1;
//		testChunk.heightmap[11][10] = 1;
//		testChunk.heightmap[5][13] = 1;
		GenWall testWall = new GenWall(5, 5, 10, 10, Stone.get());
		testWall.generate();
		GenWall testWall2 = new GenWall(-2,-2, 20, -7, Stone.get());
		testWall2.generate();
		Core.start();
	}

	//Stepping Functions
	public static void step() {
		stepState();
		stepGraphics();
	}
	
	public static void stepGraphics() {
		Core.frame.setVisible(true);
		Core.frame.repaint();
		Core.frame.getContentPane().repaint();
	}
	
	private static void stepState() {
//		Main.player.goToRelative((int)(Math.random() * 3) - 1, (int)(Math.random() * 3) - 1);
		Main.player.goToRelative(1,0);
	}
}
