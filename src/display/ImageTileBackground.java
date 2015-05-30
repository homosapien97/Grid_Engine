package display;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Tiled image background
 * @author Michael
 * inspired by http://stackoverflow.com/questions/16750709/how-can-i-add-repeat-background-image-in-jframe
 */
@SuppressWarnings("serial")
public class ImageTileBackground extends JPanel {
	private Image image;
	
	public ImageTileBackground(Image image){
		this.image = image;
	}
	
	@Override
	public void paintComponent(Graphics page){
		super.paintComponent(page);
		
		int width = this.image.getWidth(this);
		int height = this.image.getHeight(this);
		
		if(width > 0 && height > 0){
			//add extra width/height to loop condition so it completly fills up the screen
			for(int x = 0; x < Display.P_WIDTH + width; x += width){
				for(int y = 0; y < Display.P_HEIGHT + height; y += height){
					page.drawImage(image, x, y, width, height, this);
				}
			}
		}
	}
}