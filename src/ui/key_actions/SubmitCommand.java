package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.InventoryPage;
import ui.display.Camera;
import ui.display.GameDisplay;
import ui.terminal.Command;
import core.Core;

@SuppressWarnings("serial")
public class SubmitCommand extends AbstractAction {
	public static String[] last;
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		GameDisplay.submitCommand();
//		System.out.println("cmd submitted");
		Core.gameState = TogglePause.oldState;
		last = GameDisplay.submitCommand().split(" ");
		PreviewCommand.current = null;
		if(last != null) {
			Command.submitCurrent(SubmitCommand.last);
			SubmitCommand.last = null;
		}
		Camera.highlightSnapshot();
	}
}
