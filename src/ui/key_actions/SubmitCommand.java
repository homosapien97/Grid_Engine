package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.display.GameDisplay;
import core.Core;

@SuppressWarnings("serial")
public class SubmitCommand extends AbstractAction {
	public static String[] last;
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		GameDisplay.submitCommand();
		System.out.println("cmd submitted");
		Core.gameState = OpenCMDLog.oldState;
		last = GameDisplay.submitCommand().split(" ");
		PreviewCommand.current = null;
	}
}