package display;

import java.awt.Graphics;


/**
 * Handle's the displaying and interface of the main menu and any pages that are included in that.
 * @author Michael
 */
@SuppressWarnings("serial")
public class MainMenu extends Display{
	
	public MainMenu(){
		super();
	}
	
	public void paintComponent(Graphics page){
		//start basic
		page.drawString("GRID", 10, 10);
		page.drawString("GAME", 20, 100);
	}
}
