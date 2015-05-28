package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;

import creature.Goblin;
import creature.Human;
import creature.Skeleton;

import run.Main;

import terrain.Quicksand;
import terrain.Stone;
import terrain.Void;

import display.About;
import display.Display;
import display.GameDisplay;
import display.MainMenu;
import display.Settings;

import entity.Player;



public class Core {
	//game state
	public static GameState gameState = GameState.INIT;
	
	//graphics
	public static JFrame frame = new JFrame("Grid Game");
	
	public final static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	static GameDisplay gameDisplay;
	static MainMenu mainMenu;
	static Settings settingsPage;
	static About aboutPage;
	
	//fonts
	public static Font cinzel = null;
	public static Font cinzelDecorative = null;
	public static Font forum = null;
	
	//core
	
	public static void start(){
		//graphics init

		//load fonts
		Core.loadFonts();
		
		//load pages
		gameDisplay = new GameDisplay();
		mainMenu = new MainMenu();
		settingsPage = new Settings();
		aboutPage = new About();
		
		//init settings and about pages
		frame.setVisible(false);
		frame.getContentPane().add(settingsPage);
		frame.pack();
		frame.getContentPane().remove(settingsPage);
		frame.getContentPane().add(aboutPage);
		frame.pack();
		frame.getContentPane().remove(aboutPage);
		
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
		
		//loading
		Core.loadCreatures();
		Core.loadEntities();
		Core.loadTerrain();
	}
	
	//loading
	
	private static void loadCreatures(){
		Goblin.load();
		Human.load();
		Skeleton.load();
	}
	
	private static void loadEntities(){
		//Player.load(); file does not exist yet
	}
	
	private static void loadTerrain(){
		Stone.load();
		Quicksand.load();
		Void.load();
	}
	
	//game navigation
	
	public static void play(){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(gameDisplay);
		frame.pack();
		
		gameState = GameState.PLAYING;
		Main.stepGraphics();
	}
	
	public static void mainMenu(){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(mainMenu);
		frame.setBackground(Color.black);
		frame.pack();
		
		gameState = GameState.IN_MAIN_MENU;
		Main.stepGraphics();
	}
	
	public static void dispSettings(){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(settingsPage);
		frame.setBackground(Color.black);
		frame.pack();
		
		gameState = GameState.IN_SETTINGS;
		Main.stepGraphics();
	}
	
	public static void dispAbout(){
		frame.getContentPane().removeAll();
		frame.getContentPane().add(aboutPage);
		frame.setBackground(Color.black);
		frame.pack();
		
		gameState = GameState.IN_ABOUT;
		Main.stepGraphics();
	}
	
	public static void exit(){
		gameState = GameState.EXITING;
		
		System.runFinalization();
		System.gc();
		System.exit(0);
	}
	
	//fonts
	
	public static void loadFonts(){
		File font = new File("resources\\fonts\\Cinzel.ttf");
		
		try {
			cinzel = Font.createFont(Font.TRUETYPE_FONT, font);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
		
		ge.registerFont(cinzel);
		
		File font2 = new File("resources\\fonts\\CinzelDecorative.ttf");
		
		try {
			cinzelDecorative = Font.createFont(Font.TRUETYPE_FONT, font2);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
		
		ge.registerFont(cinzelDecorative);
		
		File font3 = new File("resources\\fonts\\Forum.ttf");
		
		try {
			forum = Font.createFont(Font.TRUETYPE_FONT, font3);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
		
		ge.registerFont(forum);
	}
	
	public static void debugFonts(){
		Font[] fonts = ge.getAllFonts();
		
		for(Font font : fonts){
			System.out.println(font.getFamily());
		}
	}
}