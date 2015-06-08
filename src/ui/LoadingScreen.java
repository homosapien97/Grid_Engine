package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import tools.Tools;
import ui.display.Display;
import ui.display.GameDisplay;
import core.Core;
import core.Main;

@SuppressWarnings("serial")
public class LoadingScreen extends Display {
	//use a font that is one "of all widely-supported cross-platform fonts" (reference on about page)
	//so we don't have to load one for the loading screen
	public static final Font font = new Font("Trebuchet MS", Font.PLAIN, 20);
	
	//load its resources cause it is running before the load scripts
	public static Image loadingImage = Tools.img.loadImage("GridSplashX.png", "icons");
	
	//loading stuff
	public JProgressBar progressBar = new JProgressBar(0, 100);
	public static JTextArea loadLog = new JTextArea("Loading Grid Game " + Main.version + "...\n");
	
	public LoadingScreen(){
		super();
		
		//this
		BoxLayout rootLayout = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(rootLayout);
		
		//main body
		Container main = new Container();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(mainLayout);
		main.setSize(P_WIDTH, P_HEIGHT);
		
		//image
		JLabel image = new JLabel();
		ImageIcon icon = new ImageIcon(loadingImage);
		image.setIcon(icon);
		image.setSize(912, 816);
		image.setVisible(true);
		main.add(image);
		
		//loading container
		Container loading = new Container();
		BoxLayout loadingLayout = new BoxLayout(loading, BoxLayout.X_AXIS);
		loading.setLayout(loadingLayout);
		loading.setSize(P_WIDTH, P_HEIGHT);
		
		loading.add(Box.createRigidArea(new Dimension(0, 50)));
		
		//loading output
		loadLog.setForeground(Color.white);
		loadLog.setBackground(Color.black);
		//loadLog.setPreferredSize(new Dimension(1536, P_HEIGHT)); //don't question that width...just don't
		
		//load log pane
		JScrollPane logScrollPane = new JScrollPane(loadLog);
		logScrollPane.setPreferredSize(new Dimension(1536, P_HEIGHT));
		logScrollPane.setBackground(new Color(0, 0, 0, 0));
		logScrollPane.setForeground(new Color(0, 0, 0, 0));
		logScrollPane.setWheelScrollingEnabled(true);
		logScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		logScrollPane.setBorder(null);
		logScrollPane.setVisible(true);
		
		//loading text
		JLabel loadingText = new JLabel("Loading...    ");
		loadingText.setForeground(Color.white);
		loading.add(loadingText);
		
		//loading bar
		progressBar.setForeground(new Color(204, 132, 73));
		loading.add(progressBar);
		
		//show
		main.add(loading);
		
		this.add(logScrollPane);
		this.add(main);
		this.add(Box.createRigidArea(new Dimension(312, 0)));
		
		main.setVisible(true);
	}
	
	public static void startupLog(String s){
		loadLog.append("\n" + s);
		loadLog.setCaretPosition(loadLog.getDocument().getLength());
	}
	
	public void loadGameResources(){
		(new loadGameResources()).execute();
		
	}
	
	public class loadGameResources extends SwingWorker<Object, Object> {

		@Override
		protected Object doInBackground() {
			startupLog("Loading graphics...");
			//load graphics
			startupLog("Loading entities...");
			Core.loadEntities();
			startupLog("Done loading entities!");
			startupLog("Loading terrain...");
			Core.loadTerrain();
			startupLog("Done loading terrain!");
			startupLog("Loading ui graphics...");
			Core.loadUIGraphics();
			startupLog("Done loading ui graphics!");
			startupLog("Loading cards...");
			Core.loadCards();
			startupLog("Done loading cards!");
			startupLog("Loading additional graphics...");
			Core.loadAdditionalGraphics();
			startupLog("Done loading additional graphics!");
			startupLog("Done loading graphics!");
			
			startupLog("Loading commands...");
			Core.loadCommands();
			startupLog("Done loading commands!");

			//load fonts
			startupLog("Loading fonts...");
			Core.loadFonts();
			startupLog("Done loading fonts!");
			
			//load pages
			startupLog("Creating display pages...");
			startupLog("Creating game display...");
			Core.gameDisplay = new GameDisplay();
			startupLog("Creating main menu...");
			Core.mainMenu = new MainMenu();
			startupLog("Creating settings page...");
			Core.settingsPage = new SettingsPage();
			startupLog("Creating about page...");
			Core.aboutPage = new AboutPage();
			startupLog("Done creating display pages!");

			startupLog("Adding key binds...");
			Core.addKeyBinds(Core.gameDisplay, false);
			startupLog("Done adding keybinds!");
			
			//init settings and about pages
			Core.frame.setVisible(false);
			Core.frame.getContentPane().add(Core.settingsPage);
			Core.frame.pack();
			Core.frame.getContentPane().remove(Core.settingsPage);
			Core.frame.getContentPane().add(Core.aboutPage);
			Core.frame.pack();
			Core.frame.getContentPane().remove(Core.aboutPage);
			
			return null;
		}
		
		@Override
		protected void done(){
			Core.mainMenu();
		}
	}
}
