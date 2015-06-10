package ui.key_actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import ui.InventoryPage;
import ui.display.GameDisplay;
import core.Core;
import core.GameState;


@SuppressWarnings("serial")
public class ToggleInventory extends AbstractAction {
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(GameDisplay.inventoryVisible == false){
			//its about to become true, so update it
			InventoryPage.updateCards();
		}
		
		GameDisplay.toggleInventory();
		
		if(Core.gameState == GameState.PAUSED) {
			Core.gameState = TogglePause.oldState;
		} else {
			TogglePause.oldState = Core.gameState;
			Core.gameState = GameState.PAUSED;
		}
	}
}
