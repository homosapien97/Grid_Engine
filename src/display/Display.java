package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel {
	//dimensions of sprites in screen pixels
	public static final int SPRITE_DIM = 16;
	
	//units are sprites
	public static final int WIDTH = 96;
	public static final int HEIGHT = 54;
	
	//pixel units
	protected static final int P_WIDTH = WIDTH * SPRITE_DIM;
	protected static final int P_HEIGHT = HEIGHT * SPRITE_DIM;
	
	//screen size
	private static Dimension screenDimensions = new Dimension(P_WIDTH, P_HEIGHT);

	//graphics environment
	public final static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	//fonts
	public static Font roboto = null;
	
	//initialization
	public Display() {
		//set screen size
		setPreferredSize(screenDimensions);
		
		//background color
		setBackground(Color.black);
	}
	
	public void paintComponent(Graphics page){
	}
	
	public static Dimension getDimensions(){
		return screenDimensions;
	}
	
	public static void loadFonts(){
		File font = new File("resources\\fonts\\roboto.ttf");
		
		try {
			roboto = Font.createFont(Font.TRUETYPE_FONT, font);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ge.registerFont(roboto);
	}
}
