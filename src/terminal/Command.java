package terminal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import entity.Player;
import geometry.PointCollection;
import action.Action;

public abstract class Command {
	public static Map<String, Command> commands = Collections.synchronizedMap(new HashMap<String, Command>());
	public final String header;
	public Action action;
	protected static Player player = null;
	
	protected Command(String header) {
		this.header = header;
		commands.put(header, this);
	}
	
	public static boolean init(Player p) {
		if(player == null) {
			player = p;
		}
		return false;
	}
	
	public abstract boolean run(String[] args);
	
	public abstract PointCollection preview(String[] args);
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Command) {
			return ((Command) o).header.equals(header);
		}
		return false;
	}
	
	public static boolean isCommand(String header) {
		return (commands.get(header) != null);
	}
	
	public static boolean alias(Command c, String s) {
		if(commands.get(s) == null) {
			commands.put(s, c);
			return true;
		} else {
			return false;
		}
	}
}
