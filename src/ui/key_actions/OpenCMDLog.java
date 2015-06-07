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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("cmd opened");
		TogglePause.oldState = Core.gameState;
		Core.gameState = GameState.PAUSED;
		GameDisplay.toggleCMDLog();
	}
}
