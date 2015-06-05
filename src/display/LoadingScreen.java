package display;

import general.Tools;

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
import javax.swing.SwingWorker;

import core.Core;

@SuppressWarnings("serial")
public class LoadingScreen extends Display {
	//use a font that is one "of all widely-supported cross-platform fonts" http://templates.mailchimp.com/design/typography/
	//so we don't have to load one for the loading screen
	public static final Font font = new Font("Trebuchet MS", Font.PLAIN, 20);
	
	//load its resources cause it is running before the load scripts
	public static Image loadingImage = Tools.img.loadImage("GridSplashX.png", "icons");
	
	//the loading bar
	public JProgressBar progressBar = new JProgressBar(0, 100);
	
	public LoadingScreen(){
		super();
		
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
		
		loading.add(Box.createRigidArea(new Dimension(0,50)));
		
		//loading text
		JLabel loadingText = new JLabel("Loading...    ");
		loadingText.setForeground(Color.white);
		loading.add(loadingText);
		
		//loading bar
		progressBar.setForeground(new Color(204, 132, 73));
		loading.add(progressBar);
		
		//show
		main.add(loading);
		this.add(main);
		main.setVisible(true);
	}
	
	public void loadGameResources(){
		(new loadGameResources()).execute();
		
	}
	
	public class loadGameResources extends SwingWorker<Object, Object> {

		@Override
		protected Object doInBackground() {
			//load graphics
			Core.loadEntities();
			Core.loadTerrain();
			Core.loadUIGraphics();
			Core.loadCards();
			Core.loadAdditionalGraphics();
			Core.loadCommands();

			//load fonts
			Core.loadFonts();
			
			//load pages
			Core.gameDisplay = new GameDisplay();
			Core.mainMenu = new MainMenu();
			Core.settingsPage = new SettingsPage();
			Core.aboutPage = new AboutPage();
			
			Core.addKeyBinds(Core.gameDisplay);
			
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
