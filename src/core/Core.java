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
import spells.HealSelfSpell;
import spells.PlasmaSpell;
import spells.ShieldSpell;
import spells.SwordSpell;
import spells.WaterSpell;
import terrain.Brick;
import terrain.Lava;
import terrain.Pit;
import terrain.Quicksand;
import terrain.Stone;
import terrain.Empty;
import terrain.StoneBrick;
import terrain.StoneTile;
import terrain.Water;
import terrain.Wood;
import tools.Tools;
import ui.AboutPage;
import ui.LoadingScreen;
import ui.MainMenu;
import ui.SettingsPage;
import ui.display.Display;
import ui.display.GameDisplay;
import ui.display.ImageTileBackground;
import ui.key_actions.CloseCMDLog;
import ui.key_actions.CommandNav;
import ui.key_actions.ExitToMainMenu;
import ui.key_actions.OpenCMDLog;
import ui.key_actions.PreviewCommand;
import ui.key_actions.SubmitCommand;
import ui.key_actions.ToggleHUD;
import ui.key_actions.ToggleInventory;
import ui.key_actions.TogglePause;
import ui.terminal.CastCommand;
import ui.terminal.CastRelativeCommand;
import ui.terminal.MoveCommand;
import ui.terminal.MoveRelativeCommand;
import entity.Chest;
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
		loadingScreen = new LoadingScreen();
		
		def = frame.getContentPane();
		
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
	
	public static void addKeyBinds(GameDisplay game, boolean reset){
		//get maps
		InputMap gameIM = game.getInputMap(JComponent.WHEN_FOCUSED);
		InputMap gameIM_CMDLINE = GameDisplay.cmdInput.getInputMap(JComponent.WHEN_FOCUSED);
		InputMap gameIM_INVENTORY = game.inventory.getInputMap(JComponent.WHEN_FOCUSED);
		//InputMap gameIM_MASTER = game.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		
		//reset them?
		if(reset){
			gameIM.clear();
			gameIM_INVENTORY.clear();
		}
		
		ActionMap gameAM = game.getActionMap();
		ActionMap gameAM_CMDLINE = GameDisplay.cmdInput.getActionMap();
		ActionMap gameAM_INVENTORY = game.inventory.getActionMap();
		
		//add to input map
		gameIM.put(KeyStroke.getKeyStroke(Settings.hudKeyBind), "toggleHUD");
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "openCMDLog");
		gameIM.put(KeyStroke.getKeyStroke(Settings.inventoryKeyBind), "toggleInventory");
		
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "mainMenu");
		gameIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "togglePause");
		
		//these shouldn't be reset
		if(!reset){
			gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "closeCMDLog");
			gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "submitCommand");
			gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "previewCommand");

			gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "previousCommandUp");
			gameIM_CMDLINE.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "previousCommandDown");
			
			//gameIM_MASTER.put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, 0, false), "ctrlModifier");
			//gameIM_MASTER.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, false), "altModifier");
		}
		
		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(Settings.inventoryKeyBind), "toggleInventory");
		gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "toggleInventory");
		//gameIM_INVENTORY.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "toggleCMDLog");
		
		//never need to re-add these so don't do it again if reseting
		if(!reset){
			//add to action map
			gameAM.put("toggleHUD", new ToggleHUD());
			gameAM.put("openCMDLog", new OpenCMDLog());
			gameAM.put("toggleInventory", new ToggleInventory());
			gameAM.put("mainMenu", new ExitToMainMenu());
			gameAM.put("togglePause", new TogglePause());
			
			gameAM_CMDLINE.put("closeCMDLog", new CloseCMDLog());
			gameAM_CMDLINE.put("submitCommand", new SubmitCommand());
			gameAM_CMDLINE.put("previewCommand", new PreviewCommand());

			gameAM_CMDLINE.put("previousCommandUp", new CommandNav(CommandNav.direction.UP));
			gameAM_CMDLINE.put("previousCommandDown", new CommandNav(CommandNav.direction.DOWN));
			
			//gameAM.put("ctrlModifier", new ClickModifier());
			//gameAM.put("altModifier", new ClickModifier());
			
			gameAM_INVENTORY.put("toggleInventory", new ToggleInventory());
			//gameAM_INVENTORY.put("toggleCMDLog", new ToggleCMDLog());
		}
	}
	
	//loading
	
	public static void incrementLoadingProgress(int n){
		loadingScreen.progressBar.setValue(loadingScreen.progressBar.getValue() + n);
	}
	
	public static void loadCreatures(){
		LoadingScreen.startupLog("Loading skeleton...");
		Skeleton.load();
		incrementLoadingProgress(3);
	}
	
	public static void loadEntities(){
		loadCreatures();
		LoadingScreen.startupLog("Loading player...");
		Player.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading chest...");
		Chest.load();
		incrementLoadingProgress(3);
	}
	
	public static void loadTerrain(){
		LoadingScreen.startupLog("Loading stone...");
		Stone.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading quicksand...");
		Quicksand.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading stone brick...");
		StoneBrick.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading lava...");
		Lava.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading water...");
		Water.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading stone tile...");
		StoneTile.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading wood...");
		Wood.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading brick...");
		Brick.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading pit...");
		Pit.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading empty...");
		Empty.load();
		incrementLoadingProgress(3);
	}
	
	public static void loadUIGraphics(){
		LoadingScreen.startupLog("Loading game ui graphics...");
		GameDisplay.load();
		incrementLoadingProgress(3);
	}
	
	public static void loadCards(){
		LoadingScreen.startupLog("Loading earth spell...");
		EarthSpell.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading fire spell...");
		FireSpell.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading plasma spell...");
		PlasmaSpell.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading water spell...");
		WaterSpell.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading bow spell...");
		BowSpell.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading shield spell...");
		ShieldSpell.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading sword spell...");
		SwordSpell.load();
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading selfheal spell...");
		HealSelfSpell.load();
		incrementLoadingProgress(3);
	}
	
	public static void loadCommands() {
		LoadingScreen.startupLog("Creating move command...");
		@SuppressWarnings("unused")
		Object a = MoveCommand.moveCommand;
		LoadingScreen.startupLog("Creating cast command...");
		a = CastCommand.castCommand;
		LoadingScreen.startupLog("Creating move relative command...");
		a = MoveRelativeCommand.moveRelativeCommand;
		LoadingScreen.startupLog("Creating cast relative command...");
		a = CastRelativeCommand.castRelativeCommand;
	}
	
	public static void loadAdditionalGraphics(){
		LoadingScreen.startupLog("Loading main menu background...");
		bg = Tools.img.loadImage("mainmenu.png", "backgrounds");
		incrementLoadingProgress(3);
		background = new ImageTileBackground(bg);
		
		LoadingScreen.startupLog("Loading highlights...");
		LoadingScreen.startupLog("Loading blue highlight...");
		blueHighlight = Tools.img.loadImage("blueHighlight.png", "general");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading cyan highlight...");
		cyanHighlight = Tools.img.loadImage("cyanHighlight.png", "general");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading green highlight...");
		greenHighlight = Tools.img.loadImage("greenHighlight.png", "general");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading grey highlight...");
		greyHighlight = Tools.img.loadImage("greyHighlight.png", "general");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading purple highlight...");
		purpleHighlight = Tools.img.loadImage("purpleHighlight.png", "general");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading red highlight...");
		redHighlight = Tools.img.loadImage("redHighlight.png", "general");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading white highlight...");
		whiteHighlight = Tools.img.loadImage("whiteHighlight.png", "general");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading yellow highlight...");
		yellowHighlight = Tools.img.loadImage("yellowHighlight.png", "general");
		incrementLoadingProgress(3);
	}
	
	//game navigation
	
	public static void play(){
		frame.getContentPane().removeAll();
		frame.setContentPane(def);
		frame.getContentPane().add(gameDisplay);
		frame.pack();
		
		gameState = GameState.PLAYING;
//		gameState = GameState.PAUSED;
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
		LoadingScreen.startupLog("Loading Cinzel...");
		cinzel = loadFont("Cinzel.ttf");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading Cinzel Decorative...");
		cinzelDecorative = loadFont("CinzelDecorative.ttf");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading Forum...");
		forum = loadFont("Forum.ttf");
		incrementLoadingProgress(3);
		LoadingScreen.startupLog("Loading Courier...");
		courier = loadFont("Courier.ttf");
		incrementLoadingProgress(3);
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