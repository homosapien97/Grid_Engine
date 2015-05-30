package key_actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import display.GameDisplay;



public class ToggleHUD implements Action {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("H");
		GameDisplay.toggleHUD();
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {}
	@Override
	public Object getValue(String arg0) { return null; }
	@Override
	public boolean isEnabled() { return false; }
	@Override
	public void putValue(String arg0, Object arg1) {}
	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {}
	@Override
	public void setEnabled(boolean arg0) {}
}
