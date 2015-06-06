package terminal;

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
		if(args.length != 4 && Integer.parseInt(args[1]) < Player.player.spellInventory.length) return false; 
		try {
			if(action == null) {
				action = new SpellAction(Player.player.spellInventory[Integer.parseInt(args[1])], Player.player, Player.player.getAbsoluteX() + Integer.parseInt(args[2]),
						Player.player.getAbsoluteY() + Integer.parseInt(args[3]), true);
				action = null; //set action to null once executed.
				return true;
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
		if(args.length != 4) return new PointCollection();
		try {
			action = new SpellAction(Player.player.spellInventory[Integer.parseInt(args[1])], Player.player, Player.player.getAbsoluteX() + Integer.parseInt(args[2]),
					Player.player.getAbsoluteY() + Integer.parseInt(args[3]), false);
			return action.pointsToHighlight();
		} catch(NumberFormatException e) {
			return new PointCollection();
		}
	}

}