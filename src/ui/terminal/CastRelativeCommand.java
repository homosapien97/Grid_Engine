package ui.terminal;

import entity.Inventory;
import entity.Player;
import geometry.PointCollection;
import action.SpellAction;

public class CastRelativeCommand extends Command{
	public static final CastRelativeCommand castRelativeCommand = new CastRelativeCommand();
	protected CastRelativeCommand() {
		super("cr");
	}

	@Override
	public boolean run(String[] args) {
		if(args.length != 4 || !(Integer.parseInt(args[1]) < Inventory.spells.length)) return false; 
		try {
			if(action == null) {
				if(Player.player.spellInventory.use(Integer.parseInt(args[1])) > -1) {
					action = new SpellAction(Inventory.spells[Integer.parseInt(args[1])], Player.player, Player.player.getAbsoluteX() + Integer.parseInt(args[2]),
							Player.player.getAbsoluteY() + Integer.parseInt(args[3]), true);
					action = null; //set action to null once executed.
					return true;
				}
				return false;
			} else {
				action.addToQueue();
				action = null;
				return true;
			}
		} catch(NumberFormatException e) {
			return false;
		}
	}

	@Override
	public PointCollection preview(String[] args) { //takes args including first token, which should be same as header.
		if(args.length != 4 || !(Integer.parseInt(args[1]) < Inventory.spells.length)) return new PointCollection();
		try {
			if(Player.player.spellInventory.use(Integer.parseInt(args[1])) > -1) {
				action = new SpellAction(Inventory.spells[Integer.parseInt(args[1])], Player.player, Player.player.getAbsoluteX() + Integer.parseInt(args[2]),
						Player.player.getAbsoluteY() + Integer.parseInt(args[3]), false);
				return action.pointsToHighlight();
			}
			return PointCollection.blank;
		} catch(NumberFormatException e) {
			return PointCollection.blank;
		}
	}

}
