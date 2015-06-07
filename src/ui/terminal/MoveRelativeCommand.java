package ui.terminal;

import action.MoveAction;
import geometry.PointCollection;
import entity.Player;

public class MoveRelativeCommand extends Command{
	public static final MoveRelativeCommand moveRelativeCommand = new MoveRelativeCommand();
	protected MoveRelativeCommand() {
		super("mr");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean run(String[] args) {
		if(args.length != 3) return false;
		try {
			if(action == null) {
				action = new MoveAction<Player>(Player.player, Player.player.getAbsoluteX() + Integer.parseInt(args[1]), Player.player.getAbsoluteY() + Integer.parseInt(args[2]), true);
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
	private MoveAction<Player> temp;
	@Override
	public PointCollection preview(String[] args) {
//		return player.pathToPreview(player.getAbsoluteX() + Integer.parseInt(args[1]), player.getAbsoluteY() + Integer.parseInt(args[2])).pointsToHighlight();
		if(args.length != 3) return PointCollection.blank;
		temp = Player.player.pathToPreview(Player.player.getAbsoluteX() + Integer.parseInt(args[1]), Player.player.getAbsoluteY() + Integer.parseInt(args[2]));
		if(temp == null) {
			return PointCollection.blank;
		}
		return temp.pointsToHighlight();
	}
}
