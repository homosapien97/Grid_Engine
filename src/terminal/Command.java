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
		System.out.println("Creating new command: " + header);
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
	
	public static PointCollection previewCurrent(String[] args) {
		if(args.length > 0) {
			if(commands.get(args[0]) == null) {
				return new PointCollection();
			} else {
				return commands.get(args[0]).preview(args);
			}
		} return new PointCollection();
	}
	public static boolean submitCurrent(String[] args) {
		System.out.println("submitting:");
		if(args.length > 0) {
			for(int i = 0; i < args.length; i++){
				System.out.println("\t" + args[i]);
			}
			if(commands.get(args[0]) == null) {
				System.out.println(">command not found");
				return false;
			} else {
				System.out.println(">For real");
				return commands.get(args[0]).run(args);
			}
		}
		return false;
	}
}
