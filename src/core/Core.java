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
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import creature.Skeleton;
import run.Main;
import spells.BowSpell;
import spells.EarthSpell;
import spells.FireSpell;
import spells.PlasmaSpell;
import spells.ShieldSpell;
import spells.SwordSpell;
import spells.WaterSpell;
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
import key_actions.ExitToMainMenu;
import key_actions.ToggleCMDLine;
import key_actions.ToggleHUD;
import key_actions.ToggleInventory;



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
	
	//highlight
	public static Image blueHighlight = null;
	public static Image cyanHighlight = null;
	public static Image greenHighlight = null;
	public static Image greyHighlight = null;
	public static Image purpleHighlight = null;
	public static Image redHighlight = null;
	public static Image whiteHighlight = null;
	public static Image yellowHighlight = null;
	
	//fonts
	public static Font cinzel = null;
	public static Font cinzelDecorative = null;
	public static Font forum = null;
	public static Font courier = null;
	
	//core
	
	public static void start(){
		//graphics init

		//load fonts
		Core.loadFonts();
		
		//loading
//		Core.loadCreatures();
		Core.loadEntities();
		Core.loadTerrain();
		Core.loadUIGraphics();
		Core.loadCards();
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
		InputMap gameIM = game.getInputMap(JComponent.WHEN_FOCUSED);
		InputMap gameIM_CMDLINE = game.cmdInput.getInputMap(JComponent.WHEN_FOCUSED);
		InputMap gameIM_INVENTORY = game.inventory.getInputMap(JComponent.WHEN_FOCUSED);
		//InputMap gameIM_MASTER = game.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		ActionMap gameAM = game.getActionMap();
		ActionMap gameAM_CMDLINE = game.cmdInput.getActionMap();
		ActionMap gameAM_INVENTORY = game.inventory.getActionMap();
		
		//add to input map
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, false), "toggleHUD");
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "toggleCMDLine");
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "toggleInventory");
		
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "mainMenu");
		
		gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "toggleCMDLine");
		
		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "toggleInventory");
		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "toggleInventory");
		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "toggleCMDLine");
		
		//add to action map
		gameAM.put("toggleHUD", new ToggleHUD());
		gameAM.put("toggleCMDLine", new ToggleCMDLine());
		gameAM.put("toggleInventory", new ToggleInventory());
		gameAM.put("mainMenu", new ExitToMainMenu());
		
		gameAM_CMDLINE.put("toggleCMDLine", new ToggleCMDLine());
		
		gameAM_INVENTORY.put("toggleInventory", new ToggleInventory());
		gameAM_INVENTORY.put("toggleCMDLine", new ToggleCMDLine());
	}
	
	//loading
	
	private static void loadCreatures(){
		Skeleton.load();
	}
	
	private static void loadEntities(){
		loadCreatures();
		Player.load();
	}
	
	private static void loadTerrain(){
		Stone.load();
		Quicksand.load();
		Empty.load();
	}
	
	private static void loadUIGraphics(){
		GameDisplay.load();
	}
	
	private static void loadCards(){
		EarthSpell.load();
		FireSpell.load();
		PlasmaSpell.load();
		WaterSpell.load();
		
		BowSpell.load();
		ShieldSpell.load();
		SwordSpell.load();
	}
	
	private static void loadAdditionalGraphics(){
		bg = Tools.img.loadImage("mainmenu.png", "backgrounds");
		blueHighlight = Tools.img.loadImage("blueHighlight.png", "general");
		cyanHighlight = Tools.img.loadImage("cyanHighlight.png", "general");
		greenHighlight = Tools.img.loadImage("greenHighlight.png", "general");
		greyHighlight = Tools.img.loadImage("greyHighlight.png", "general");
		purpleHighlight = Tools.img.loadImage("purpleHighlight.png", "general");
		redHighlight = Tools.img.loadImage("redHighlight.png", "general");
		whiteHighlight = Tools.img.loadImage("whiteHighlight.png", "general");
		yellowHighlight = Tools.img.loadImage("yellowHighlight", "general");
	}
	
	//game navigation
	
	public static void play(){
		frame.getContentPane().removeAll();
		frame.setContentPane(def);
		frame.getContentPane().add(gameDisplay);
		frame.pack();
		
		gameState = GameState.PLAYING;
		gameDisplay.requestFocusInWindow();
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
		cinzel = loadFont("Cinzel.ttf");
		cinzelDecorative = loadFont("CinzelDecorative.ttf");
		forum = loadFont("Forum.ttf");
		courier = loadFont("Courier.ttf");
	}
	
	public static Font loadFont(String filename){
		Font font = null;
		File file = new File("resources\\fonts\\" + filename);
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, file);
		} catch (FontFormatException e) {
			e.printStackTrace();
			System.out.println("Font Error! (" + filename + ")");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Font Error! (" + filename + ")");
		}
		
		ge.registerFont(font);
		
		return font;
	}
	
	public static void debugFonts(){
		Font[] fonts = ge.getAllFonts();
		
		for(Font font : fonts){
			System.out.println(font.getFamily());
		}
	}
}