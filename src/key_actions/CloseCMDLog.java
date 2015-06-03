package key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import core.Core;
import display.GameDisplay;


@SuppressWarnings("serial")
public class CloseCMDLog extends AbstractAction {

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		GameDisplay.toggleCMDLog();
//	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("cmd closed");
		Core.gameState = OpenCMDLog.oldState;
		GameDisplay.toggleCMDLog();
	}
}