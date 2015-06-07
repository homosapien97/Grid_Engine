package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.display.GameDisplay;


@SuppressWarnings("serial")
public class ToggleHUD extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GameDisplay.toggleHUD();
	}
}
