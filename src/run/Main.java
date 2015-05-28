package run;

import display.Camera;

import core.*;

import entity.Player;

import general.Tools;

import terrain.Stone;

import world.Chunk;
import world.LoadedChunks;

public class Main {
	//example chunk
	static Chunk testChunk = new Chunk(0, 0, true, Stone.get());
	
	//player object
	static Player player = new Player(0, 0, testChunk, "P", 256, 64, "Player");
	
	//Main Program
	public static void main(String[] args) {
		//initializion
		init();
		
		//main loop
		while(Core.gameState != GameState.EXITING){
			switch(Core.gameState){
				case PLAYING:
					for(int i = 0; i < 40; i++) {
						step();
						Tools.time.wait(500);
					}
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
		//general init
		Camera.init(player);
		LoadedChunks.init(testChunk);
		
		//startup
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
		Main.player.goToRelative((int)(Math.random() * 3) - 1, (int)(Math.random() * 3) - 1);
	}
}
