package run;

import display.About;
import display.Camera;
import display.Display;
import display.GameDisplay;
import display.MainMenu;
import display.Settings;

import core.*;

import entity.Player;

import general.Tools;

import terrain.Stone;

import world.Chunk;
import world.LoadedChunks;

import javax.imageio.ImageIO;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

public class Main {
	//game state
	public static GameState gameState = GameState.INIT;
	
	//example chunk
	static Chunk testChunk = new Chunk(0, 0, true, Stone.get());
	
	//player object
	static Player player = new Player(0, 0, testChunk, "P", 256, 64, "Player");
	
	//graphics
	public static JFrame frame = new JFrame("Grid Game");
	
	static GameDisplay gameDisplay = new GameDisplay();
	static MainMenu mainMenu = new MainMenu();
	static Settings settingsPage = new Settings();
	static About aboutPage = new About();
	
	//Main Program
	public static void main(String[] args) {
		//initializion
		init();
		
		//main loop
		while(gameState != GameState.EXITING){
			switch(gameState){
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
		
		//graphics init
		
		//content pane
		frame.getContentPane().add(mainMenu);
		frame.setSize(Display.getDimensions());
		frame.setBackground(Color.black);
		frame.pack();
	
		//game state
		gameState = GameState.IN_MAIN_MENU;
		
		//icon
		Image gameicon = null;
		
		File icon = new File("resources\\icons\\game_icon.jpg");
		
		try{
			gameicon = ImageIO.read(icon);
		}catch(IOException e){
			System.out.println(e);
		}
		
		frame.setIconImage(gameicon);
		
		//general
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//start
		frame.setVisible(true);
		frame.repaint();
	}

	//Stepping Functions
	public static void step() {
		stepState();
		stepGraphics();
	}
	
	private static void stepGraphics() {
		frame.setVisible(true);
		frame.repaint();
	}
	
	private static void stepState() {
		player.goToRelative((int)(Math.random() * 3) - 1, (int)(Math.random() * 3) - 1);
	}
	
	//Game Functions
	public static void play(){
		frame.getContentPane().remove(mainMenu);
		frame.getContentPane().add(gameDisplay);
		frame.pack();
		
		gameState = GameState.PLAYING;
		stepGraphics();
	}
	
	public static void mainMenu(){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(mainMenu);
		frame.pack();
		
		gameState = GameState.IN_MAIN_MENU;
		stepGraphics();
	}
	
	public static void dispSettings(){
		frame.getContentPane().remove(mainMenu);
		frame.getContentPane().add(settingsPage);
		frame.pack();
		
		gameState = GameState.IN_SETTINGS;
		stepGraphics();
	}
	
	public static void dispAbout(){
		frame.getContentPane().remove(mainMenu);
		frame.getContentPane().add(aboutPage);
		frame.pack();
		
		gameState = GameState.IN_ABOUT;
		stepGraphics();
	}
	
	public static void exit(){
		gameState = GameState.EXITING;
		
		System.runFinalization();
		System.gc();
		System.exit(0);
	}
}
