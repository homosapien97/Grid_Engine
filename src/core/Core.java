package core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import creature.Goblin;
import creature.Human;
import creature.Skeleton;
import run.Main;
import terrain.Quicksand;
import terrain.Stone;
import terrain.Empty;
import display.AboutPage;
import display.Display;
import display.GameDisplay;
import display.ImageTileBackground;
import display.MainMenu;
import display.SettingsPage;
import entity.Player;
import general.Tools;
import key_actions.ToggleHUD;



public class Core {
	//game state
	public static GameState gameState = GameState.INIT;
	
	//graphics
	public static JFrame frame = new JFrame("Grid Game");
	
	private static Container def = null;
	
	public final static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	static GameDisplay gameDisplay;
	static MainMenu mainMenu;
	static SettingsPage settingsPage;
	static AboutPage aboutPage;
	
	//background
	private static Image bg = null;
	private static ImageTileBackground background = null;
	
	//debug
	public static Image redHighlight = null;
	
	//fonts
	public static Font cinzel = null;
	public static Font cinzelDecorative = null;
	public static Font forum = null;
	
	//core
	
	public static void start(){
		//graphics init

		//load fonts
		Core.loadFonts();
		
		//loading
		Core.loadCreatures();
		Core.loadEntities();
		Core.loadTerrain();
		Core.loadHUD();
		Core.loadAdditionalGraphics();
		
		//load pages
		gameDisplay = new GameDisplay();
		mainMenu = new MainMenu();
		settingsPage = new SettingsPage();
		aboutPage = new AboutPage();
		
		//init settings and about pages
		frame.setVisible(false);
		frame.getContentPane().add(settingsPage);
		frame.pack();
		frame.getContentPane().remove(settingsPage);
		frame.getContentPane().add(aboutPage);
		frame.pack();
		frame.getContentPane().remove(aboutPage);
		
		def = frame.getContentPane();
		
		//set background
		background = new ImageTileBackground(bg);
		frame.setContentPane(background);
		
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
		
		//add key binds
		addKeyBinds(gameDisplay);
	}
	
	//key binding
	
	private static void addKeyBinds(GameDisplay game){
		//get maps
		InputMap gameIM = game.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap gameAM = game.getActionMap();
		
		//add to input map
		gameIM.put(KeyStroke.getKeyStroke("H"), "toggleHUD");
		
		//add to action map
		gameAM.put("toggleHUD", new ToggleHUD());
	}
	
	//loading
	
	private static void loadCreatures(){
		Goblin.load();
		Human.load();
		Skeleton.load();
	}
	
	private static void loadEntities(){
		Player.load();
	}
	
	private static void loadTerrain(){
		Stone.load();
		Quicksand.load();
		Empty.load();
	}
	
	private static void loadHUD(){
		gameDisplay.load();
	}
	
	private static void loadAdditionalGraphics(){
		bg = Tools.img.loadImage("mainmenu.png", "backgrounds");
		redHighlight = Tools.img.loadImage("redHighlight.png", "debug");
	}
	
	//game navigation
	
	public static void play(){
		frame.getContentPane().removeAll();
		frame.setContentPane(def);
		frame.getContentPane().add(gameDisplay);
		frame.pack();
		
		gameState = GameState.PLAYING;
		Main.stepGraphics();
	}
	
	public static void mainMenu(){
		frame.getContentPane().removeAll();
		frame.setContentPane(background);
		frame.getContentPane().add(mainMenu);
		frame.setBackground(Color.black);
		frame.pack();
		
		gameState = GameState.IN_MAIN_MENU;
		Main.stepGraphics();
	}
	
	public static void dispSettings(){
		frame.getContentPane().removeAll();
		frame.setContentPane(background);
		frame.getContentPane().add(settingsPage);
		frame.setBackground(Color.black);
		frame.pack();
		
		gameState = GameState.IN_SETTINGS;
		Main.stepGraphics();
	}
	
	public static void dispAbout(){
		frame.getContentPane().removeAll();
		frame.setContentPane(background);
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