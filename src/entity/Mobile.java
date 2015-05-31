package entity;

public interface Mobile {
	public boolean goToAbsolute(int absoluteX, int absoluteY);
	public boolean goToRelative(int relativeX, int relativeY);
//	public boolean moveTowards(int absoluteX, int absoluteY);
	public int ticksPerTileWalked();
}
