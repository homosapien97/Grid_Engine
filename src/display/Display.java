package display;

import javax.swing.*;

import java.awt.*;

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
}
