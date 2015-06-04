package key_actions;

import geometry.PointCollection;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import terminal.Command;
import display.GameDisplay;

@SuppressWarnings("serial")
public class PreviewCommand extends AbstractAction {
	public static PointCollection current;
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		GameDisplay.submitCommand();
//		System.out.println("cmd submitted");
//		Core.gameState = OpenCMDLog.oldState;
		current = Command.previewCurrent(GameDisplay.cmdInput.getText().split(" "));
		
	}
}
