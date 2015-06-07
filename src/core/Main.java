package core;

import java.awt.EventQueue;

import action.Action;
import entity.Player;
import entity.creature.Skeleton;
import generation.GenWall;
import terrain.Quicksand;
import terrain.Stone;
import tools.Tools;
import ui.Camera;
import ui.display.GameDisplay;
import ui.key_actions.SubmitCommand;
import ui.terminal.Command;
//import world.Chunk;
import world.LoadedChunks;

public class Main {
	//for debug for now
	public static final String version = "alpha_0_3";
	
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
					
					if(Action.queue.get(Player.player) == null){
						Core.gameState = GameState.PAUSED;
					}
					
					break;
				case PAUSED:
					stepGraphics();
					
					Tools.time.wait(1);
					
					//unpause if the player has queued actions and the cmd line is closed
					if(Action.queue.get(Player.player) != null && !GameDisplay.cmdLogVisible){
						Core.gameState = GameState.PLAYING;
					}
					
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
		@SuppressWarnings("unused")
		Player initPlayer = Player.player;
		
		GenWall testWall = new GenWall(5, 5, 10, 10, Quicksand.get());
		testWall.generate();
		
		GenWall testWall2 = new GenWall(0,-2, 15, -7, Stone.get());
		testWall2.generate();
		
		Skeleton skelly = new Skeleton(3, 3, LoadedChunks.center);
		
		Core.start();
	}

	//Stepping Functions
	public static void step() {
		stepState();
		Camera.terrainImageSnapshot();
		Camera.entityImageSnapshot();
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
		LoadedChunks.reload();
		LoadedChunks.updateAI();
		if(SubmitCommand.last != null) {
			Command.submitCurrent(SubmitCommand.last);
			SubmitCommand.last = null;
		}
		
		Action.runAll();
		System.out.println(Clock.tick());
	}
}
