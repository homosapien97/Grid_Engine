package key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import core.Core;


@SuppressWarnings("serial")
public class ExitToMainMenu extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Core.quitGame();
	}
}
