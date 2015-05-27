package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	public static Font cinzel = null;
	public static Font cinzelDecorative = null;
	public static Font forum = null;
	
	//initialization
	public Display() {
		//set screen size
		setPreferredSize(screenDimensions);
		
		//background color
		setBackground(Color.black);
	}
	
	//static methods
	
	//dimensinos
	public static Dimension getDimensions(){
		return screenDimensions;
	}
	
	//customized components
	
	public static JButton newMainButton(String name){
		JButton button = new JButton(name); // new Color(219, 158, 107), new Color(135, 90, 53));

		Color hover = new Color(219, 158, 107);
		Color pressed = new Color(135, 90, 53);
		Color border = new Color(110, 52, 4);
		Color foreground = Color.BLACK;
		Color background = new Color(204, 132, 73);
		
		int borderWidth = 3;
		
		button.setForeground(foreground);
		button.setBackground(background);

		Border line = new LineBorder(border, borderWidth);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		
		Border compound = new CompoundBorder(line, margin);
		
		button.setFocusPainted(false);
		
		button.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if(button.getModel().isPressed()){
					button.setBackground(pressed);
		        }else if(button.getModel().isRollover()){
		        	button.setBackground(hover);
		        }else{
		            button.setBackground(background);
		        }
			}
		});
		
		button.setBorder(compound);
		
		return button;
	}
	
	//fonts
	
	public static void loadFonts(){
		File font = new File("resources\\fonts\\Cinzel.ttf");
		
		try {
			cinzel = Font.createFont(Font.TRUETYPE_FONT, font);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
		
		ge.registerFont(cinzel);
		
		File font2 = new File("resources\\fonts\\CinzelDecorative.ttf");
		
		try {
			cinzelDecorative = Font.createFont(Font.TRUETYPE_FONT, font2);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
		
		ge.registerFont(cinzelDecorative);
		
		File font3 = new File("resources\\fonts\\Forum.ttf");
		
		try {
			forum = Font.createFont(Font.TRUETYPE_FONT, font3);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
		
		ge.registerFont(forum);
	}
	
	public static void debugFonts(){
		Font[] fonts = ge.getAllFonts();
		
		for(Font font : fonts){
			System.out.println(font.getFamily());
		}
	}
}
