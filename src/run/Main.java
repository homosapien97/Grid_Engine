package run;

import java.awt.EventQueue;

import key_actions.SubmitCommand;
import action.Action;
import action.Clock;
import core.*;
import entity.Player;
import general.Tools;
import generation.GenWall;
import terminal.Command;
import terrain.Quicksand;
import terrain.Stone;
//import world.Chunk;
import world.LoadedChunks;

public class Main {
	//example chunk
//	static Chunk testChunk = new Chunk(0, 0, true, Stone.get());
//	
//	//player object
//	public static Player player = new Player(0, 0, testChunk, "P", 256, 64, "Player", 0.0, 0.0, 0.0, 0.0, 1, 1);
	
	//for debug for now
	public static final String version = "alpha_0_3";
	
	//Main Program
	public static void main(String[] args) {
		//initializion
		init();
		
		//main loop
		while(Core.gameState != GameState.EXITING){
			LoadedChunks.reload();
			switch(Core.gameState){
				case PLAYING:
					step();
					Tools.time.wait(400);
					break;
				case PAUSED:
//					System.out.println("p");
					stepGraphics();
					Tools.time.wait(1);
					break;
				case JUST_QUIT_GAME:
					//creates a new game display object, essentially makes it so you can play a new game
					Core.newGame();
					Tools.time.wait(1);
					Core.gameState = GameState.IN_MAIN_MENU;
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
		@SuppressWarnings("unused")
		Player initPlayer = Player.player;
		GenWall testWall = new GenWall(5, 5, 10, 10, Quicksand.get());
		testWall.generate();
		GenWall testWall2 = new GenWall(0,-2, 15, -7, Stone.get());
		testWall2.generate();
		Core.start();
//		Command.init(Player.player);
	}

	//Stepping Functions
	public static void step() {
		stepState();
		stepGraphics();
	}
	
	public static void stepGraphics() {
		Core.frame.repaint();
		
		EventQueue.invokeLater(new Runnable() {
	        public void run() {
	        	Core.frame.setVisible(true);    
	        }
	    });
		
		Core.frame.getContentPane().repaint();
	}
	

	private static void stepState() {
		if(SubmitCommand.last != null) {
			Command.submitCurrent(SubmitCommand.last);
			SubmitCommand.last = null;
		}
		
		Action.runAll();
		System.out.println(Clock.tick());
	}
}
