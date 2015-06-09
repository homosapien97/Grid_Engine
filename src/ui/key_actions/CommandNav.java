package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.display.GameDisplay;

@SuppressWarnings("serial")
public class CommandNav extends AbstractAction {
	
	private direction dir = direction.UP;
	
	public CommandNav(direction dir){
		this.dir = dir;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		GameDisplay.navLog(this.dir);
	}
	
	public static enum direction {UP, DOWN}
}
