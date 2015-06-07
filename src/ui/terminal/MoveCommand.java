package ui.terminal;

import action.MoveAction;
import geometry.PointCollection;
import entity.Player;

public class MoveCommand extends Command{
	public static final MoveCommand moveCommand = new MoveCommand();
	protected MoveCommand() {
		super("move");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean run(String[] args) {
		if(args.length != 3) return false;
		try {
			if(action == null) {
				action = new MoveAction<Player>(Player.player, Integer.parseInt(args[1]), Integer.parseInt(args[2]), true);
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
		if(args.length != 3) return PointCollection.blank;
		temp = Player.player.pathToPreview(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		if(temp == null) {
			return PointCollection.blank;
		}
		return temp.pointsToHighlight();
	}
}
