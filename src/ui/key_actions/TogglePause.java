package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import core.Core;
import core.GameState;


@SuppressWarnings("serial")
public class TogglePause extends AbstractAction {
	
	public static GameState oldState = GameState.PAUSED;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(Core.gameState == GameState.PAUSED) {
			Core.gameState = oldState;
		} else {
			oldState = Core.gameState;
			Core.gameState = GameState.PAUSED;
		}
	}
}
