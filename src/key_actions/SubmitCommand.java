package key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import core.Core;
import display.GameDisplay;

@SuppressWarnings("serial")
public class SubmitCommand extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
//		GameDisplay.submitCommand();
		System.out.println("cmd submitted");
		Core.gameState = OpenCMDLog.oldState;
		GameDisplay.submitCommand();
	}
}
