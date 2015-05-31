package key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import display.GameDisplay;


@SuppressWarnings("serial")
public class ToggleCMDLine extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GameDisplay.toggleCMDLine();
	}
}
