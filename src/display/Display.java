package display;

import javax.swing.*;
import java.awt.*;

public class Display {
	//dimensions of sprites in screen pixels
	public static final int SPRITE_DIM = 16;
	
	//units are sprites
	public static final int WIDTH = 96;
	public static final int HEIGHT = 54;
	
	//the output
	private static JPanel display = new JPanel();
	
	//screen size
	private static Dimension screenDimensions = new Dimension(WIDTH * SPRITE_DIM, HEIGHT * SPRITE_DIM);
	public static final Dimension DIMENSIONS = screenDimensions;
	
	//initialization
	static {
		//set screen size
		display.setSize(DIMENSIONS);
		
		//background color
		display.setBackground(Color.black);
	}
	
	public static JPanel getDisplay(){
		return display;
	}
}
