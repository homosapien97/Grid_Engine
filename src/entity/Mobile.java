package entity;

public interface Mobile {
	public boolean goToAbsolute(int absoluteX, int absoluteY);
	public boolean goToRelative(int relativeX, int relativeY);
	public int ticksPerTileWalked();
}
