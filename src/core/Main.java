package core;

import java.awt.EventQueue;

import action.Action;
import entity.Player;
import generation.GenWall;
import terrain.Quicksand;
import terrain.Stone;
import tools.Tools;
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
			LoadedChunks.reload();
			switch(Core.gameState){
				case PLAYING:
					step();
					Tools.time.wait(400);
					break;
				case PAUSED:
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
		@SuppressWarnings("unused")
		Player initPlayer = Player.player;
		
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
