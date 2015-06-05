package display;

import general.Tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LoadingScreen extends Display {
	//use a font that is one "of all widely-supported cross-platform fonts" http://templates.mailchimp.com/design/typography/
	//so we don't have to load one for the loading screen
	public static final Font font = new Font("Trebuchet MS", Font.PLAIN, 20);
	
	//load its resources cause it is running before the load scripts
	public static Image loadingImage = Tools.img.loadImage("GridSplashX.png", "icons");
	
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
		
		//loading.add(Box.createRigidArea(new Dimension(0,0)));
		
		//loading text
		JLabel loadingText = new JLabel("Loading...");
		loadingText.setForeground(Color.white);
		loadingText.setAlignmentX(CENTER_ALIGNMENT);
		loading.add(loadingText);
		
		//show
		main.add(loading);
		this.add(main);
		main.setVisible(true);
	}
}
