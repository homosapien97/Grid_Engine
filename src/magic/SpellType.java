package magic;

public enum SpellType {
	BOLT,	//straight shot, instant channel (point)
	RADIAL,	//radial from self, any channel (circle)
	AOE,	//radial from a point, any channel (circle)
	RAY,	//straight line, instant channel (Ray)
	LINE,	//straight line, any channel (line)
	SPOS,	//same position, any channel (point)
	SELF,	//self cast, any channel (player)
}
