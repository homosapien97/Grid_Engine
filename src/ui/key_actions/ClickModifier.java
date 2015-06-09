package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.display.Camera;

@SuppressWarnings("serial")
public class ClickModifier extends AbstractAction {
	public static int keyModifier = ActionEvent.CTRL_MASK;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(keyModifier);
		keyModifier = e.getModifiers();
		System.out.println(keyModifier);
	}

}
