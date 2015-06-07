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
import tools.Tools;
import ui.AboutPage;
import ui.LoadingScreen;
import ui.MainMenu;
import ui.SettingsPage;
import ui.display.Display;
import ui.display.GameDisplay;
import ui.display.ImageTileBackground;
import ui.key_actions.CloseCMDLog;
import ui.key_actions.ExitToMainMenu;
import ui.key_actions.OpenCMDLog;
import ui.key_actions.PreviewCommand;
import ui.key_actions.SubmitCommand;
import ui.key_actions.ToggleHUD;
import ui.key_actions.ToggleInventory;
import ui.terminal.CastCommand;
import ui.terminal.CastRelativeCommand;
import ui.terminal.MoveCommand;
import ui.terminal.MoveRelativeCommand;
import entity.Player;
import entity.creature.Skeleton;
//import key_actions.TogglePause;



public class Core {
	//game state
	public static GameState gameState = GameState.INIT;
	
	//graphics
	public static JFrame frame = new JFrame("Grid Game");
	
	private static Container def = null;
	
	public final static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	public static GameDisplay gameDisplay;
	public static MainMenu mainMenu;
	public static SettingsPage settingsPage;
	public static AboutPage aboutPage;
	static LoadingScreen loadingScreen;
	
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

//		//load fonts
//		Core.loadFonts();
//		
//		//loading
////		Core.loadCreatures();
//		Core.loadEntities();
//		Core.loadTerrain();
//		Core.loadUIGraphics();
//		Core.loadCards();
//		Core.loadAdditionalGraphics();
//		Core.loadCommands();
//		
//		//load pages
//		gameDisplay = new GameDisplay();
//		mainMenu = new MainMenu();
//		settingsPage = new SettingsPage();
//		aboutPage = new AboutPage();
		loadingScreen = new LoadingScreen();
		
//		//init settings and about pages
//		frame.setVisible(false);
//		frame.getContentPane().add(settingsPage);
//		frame.pack();
//		frame.getContentPane().remove(settingsPage);
//		frame.getContentPane().add(aboutPage);
//		frame.pack();
//		frame.getContentPane().remove(aboutPage);
		
		def = frame.getContentPane();
//		
//		//set background
//		background = new ImageTileBackground(bg);
//		frame.setContentPane(background);
		
		//content pane
		frame.getContentPane().add(loadingScreen);
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
//		addKeyBinds(gameDisplay);
		
		//initialize commands
		
		//load game resources
		loadingScreen.loadGameResources();
	}
	
	//game re-creation
	public static void newGame(){
		//gameDisplay = new GameDisplay();
		//addKeyBinds(gameDisplay);

		//Player.resetPlayer();
	}
	
	//key binding
	
	public static void addKeyBinds(GameDisplay game){
		//get maps
		InputMap gameIM = game.getInputMap(JComponent.WHEN_FOCUSED);
		InputMap gameIM_CMDLINE = GameDisplay.cmdInput.getInputMap(JComponent.WHEN_FOCUSED);
		InputMap gameIM_INVENTORY = game.inventory.getInputMap(JComponent.WHEN_FOCUSED);
		//InputMap gameIM_MASTER = game.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		ActionMap gameAM = game.getActionMap();
		ActionMap gameAM_CMDLINE = GameDisplay.cmdInput.getActionMap();
		ActionMap gameAM_INVENTORY = game.inventory.getActionMap();
		
		//add to input map
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, false), "toggleHUD");
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "openCMDLog");
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "toggleInventory");
		
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "mainMenu");
//		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "togglePause");
		
		gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "closeCMDLog");
		gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "submitCommand");
		gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "previewCommand");
		
		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "toggleInventory");
		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "toggleInventory");
//		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "toggleCMDLog");
		
		//add to action map
		gameAM.put("toggleHUD", new ToggleHUD());
		gameAM.put("openCMDLog", new OpenCMDLog());
		gameAM.put("toggleInventory", new ToggleInventory());
		gameAM.put("mainMenu", new ExitToMainMenu());
//		gameAM.put("togglePause", new TogglePause());
		
		gameAM_CMDLINE.put("closeCMDLog", new CloseCMDLog());
		gameAM_CMDLINE.put("submitCommand", new SubmitCommand());
		gameAM_CMDLINE.put("previewCommand", new PreviewCommand());
		
		gameAM_INVENTORY.put("toggleInventory", new ToggleInventory());
//		gameAM_INVENTORY.put("toggleCMDLog", new ToggleCMDLog());
	}
	
	//loading
	
	public static void incrementLoadingProgress(int n){
		loadingScreen.progressBar.setValue(loadingScreen.progressBar.getValue() + n);
	}
	
	public static void loadCreatures(){
		Skeleton.load();
		incrementLoadingProgress(2);
	}
	
	public static void loadEntities(){
		loadCreatures();
		Player.load();
		incrementLoadingProgress(2);
	}
	
	public static void loadTerrain(){
		Stone.load();
		incrementLoadingProgress(4);
		Quicksand.load();
		incrementLoadingProgress(4);
		Empty.load();
		incrementLoadingProgress(4);
	}
	
	public static void loadUIGraphics(){
		GameDisplay.load();
		incrementLoadingProgress(4);
	}
	
	public static void loadCards(){
		EarthSpell.load();
		incrementLoadingProgress(4);
		FireSpell.load();
		incrementLoadingProgress(4);
		PlasmaSpell.load();
		incrementLoadingProgress(4);
		WaterSpell.load();
		incrementLoadingProgress(4);
		
		BowSpell.load();
		incrementLoadingProgress(4);
		ShieldSpell.load();
		incrementLoadingProgress(4);
		SwordSpell.load();
		incrementLoadingProgress(4);
	}
	
	public static void loadCommands() {
		@SuppressWarnings("unused")
		Object a = MoveCommand.moveCommand;
		a = CastCommand.castCommand;
		a = MoveRelativeCommand.moveRelativeCommand;
		a = CastRelativeCommand.castRelativeCommand;
	}
	
	public static void loadAdditionalGraphics(){
		bg = Tools.img.loadImage("mainmenu.png", "backgrounds");
		incrementLoadingProgress(4);
		background = new ImageTileBackground(bg);
		
		blueHighlight = Tools.img.loadImage("blueHighlight.png", "general");
		incrementLoadingProgress(4);
		cyanHighlight = Tools.img.loadImage("cyanHighlight.png", "general");
		incrementLoadingProgress(4);
		greenHighlight = Tools.img.loadImage("greenHighlight.png", "general");
		incrementLoadingProgress(4);
		greyHighlight = Tools.img.loadImage("greyHighlight.png", "general");
		incrementLoadingProgress(4);
		purpleHighlight = Tools.img.loadImage("purpleHighlight.png", "general");
		incrementLoadingProgress(4);
		redHighlight = Tools.img.loadImage("redHighlight.png", "general");
		incrementLoadingProgress(4);
		whiteHighlight = Tools.img.loadImage("whiteHighlight.png", "general");
		incrementLoadingProgress(4);
		yellowHighlight = Tools.img.loadImage("yellowHighlight.png", "general");
		incrementLoadingProgress(4);
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
	
	public static void quitGame(){
		frame.getContentPane().removeAll();
		frame.setContentPane(background);
		frame.getContentPane().add(mainMenu);
		frame.setBackground(Color.black);
		frame.pack();
		
		gameState = GameState.JUST_QUIT_GAME;
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
		incrementLoadingProgress(4);
		cinzelDecorative = loadFont("CinzelDecorative.ttf");
		incrementLoadingProgress(4);
		forum = loadFont("Forum.ttf");
		incrementLoadingProgress(4);
		courier = loadFont("Courier.ttf");
		incrementLoadingProgress(4);
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