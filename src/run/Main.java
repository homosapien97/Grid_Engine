package run;

import core.*;
import entity.Player;
import general.Tools;
import generation.GenWall;
import terrain.Quicksand;
import terrain.Stone;
import world.Chunk;

public class Main {
	//example chunk
	static Chunk testChunk = new Chunk(0, 0, true, Stone.get());
	
	//player object
	public static Player player = new Player(0, 0, testChunk, "P", 256, 64, "Player", 0, 0, 0, 0);
	
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
					step();
					Tools.time.wait(400);
					break;
				default:
					//idle cpu
					Tools.time.wait(1);
					break;
			}
		}
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
		GenWall testWall = new GenWall(5, 5, 10, 10, Quicksand.get());
		testWall.generate();
		GenWall testWall2 = new GenWall(0,-2, 15, -7, Stone.get());
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
	
	private static int _gtx = 5;
	private static  int _gty = 12;
	private static void stepState() {
//		Main.player.goToRelative((int)(Math.random() * 3) - 1, (int)(Math.random() * 3) - 1);
//		Main.player.goToRelative(1,0);
		if(!player.getPath().goTo(_gtx, _gty)) {
			System.out.println("Cant' go to " + _gtx + ", " + _gty);
			for(int j = 0; j < player.getPath().maze[0].length; j++) {
				for(int i = 0; i < player.getPath().maze.length; i++) {
					System.out.print(player.getPath().maze[i][j]);
				}
				System.out.println();
			}
			Tools.time.wait(100000);
		}
//		if(!player.getPath().goTo(_gtx, _gty)) {
//			do {
//				_gtx = (int)(Math.random() * player.vsquare().RADIUS * 2 + 1 - player.vsquare().RADIUS + player.getAbsoluteX());
//				_gty = (int)(Math.random() * player.vsquare().RADIUS * 2 + 1 - player.vsquare().RADIUS + player.getAbsoluteY());
////				System.out.println("new path to " + _gtx + ", " + _gty + " " + player.vsquare().canSee(_gtx, _gty));
//			} while(!player.vsquare().canSee(_gtx, _gty));
//			System.out.println("Going to " + _gtx + ", " + _gty);
//		}
	}
}
