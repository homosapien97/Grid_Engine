package ui.key_actions;

import geometry.PointCollection;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.display.Camera;
import ui.display.GameDisplay;
import ui.terminal.Command;

@SuppressWarnings("serial")
public class PreviewCommand extends AbstractAction {
	public static PointCollection current;
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		GameDisplay.submitCommand();
//		System.out.println("cmd submitted");
//		Core.gameState = OpenCMDLog.oldState;
		current = Command.previewCurrent(GameDisplay.cmdInput.getText().split(" "));
		Camera.highlightSnapshot();
	}
}
