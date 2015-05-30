package magic;


public class Spell {
	
	public int casting;
	public int channel;
	public int trueDamage;
	public int damage;
	public String name;
	
	public Spell (int cast, int chan, int dam, String sprite, String n){
		casting = cast;
		channel = chan;
		damage = dam;
		name = n;
	}
}
