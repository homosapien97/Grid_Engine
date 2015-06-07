package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.display.GameDisplay;
import core.Core;
import core.GameState;


@SuppressWarnings("serial")
public class OpenCMDLog extends AbstractAction {

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		GameDisplay.toggleCMDLog();
//	}
	
	public static GameState oldState = null;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("cmd opened");
		oldState = Core.gameState;
		Core.gameState = GameState.PAUSED;
		GameDisplay.toggleCMDLog();
	}
}
