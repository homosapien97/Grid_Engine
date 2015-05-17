package display;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
	//dimensions of sprites in screen pixels
	public static final int SPRITE_DIM = 16;
	
	//units are sprites
	public static final int WIDTH = 96;
	public static final int HEIGHT = 54;
	
	//screen size
	private static Dimension screenDimensions = new Dimension(WIDTH * SPRITE_DIM, HEIGHT * SPRITE_DIM);
	
	//initialization
	public Display() {
		//set screen size
		setPreferredSize(screenDimensions);
		
		//background color
		setBackground(Color.black);
	}
	
	public void paintComponent(Graphics page){
		page.setColor(Color.red);
		page.drawLine(0, 0, WIDTH * SPRITE_DIM, HEIGHT * SPRITE_DIM);
		page.setColor(Color.green);
		page.drawLine(WIDTH * SPRITE_DIM, HEIGHT * SPRITE_DIM, WIDTH * SPRITE_DIM, HEIGHT * SPRITE_DIM);
	}
	
	public static Dimension getDimensions(){
		return screenDimensions;
	}
}
