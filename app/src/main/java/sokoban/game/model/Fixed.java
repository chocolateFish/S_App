package sokoban.game.model;


public abstract class Fixed implements IFixed {
	protected boolean isTarget;
	protected boolean isAvailable;
	
	Fixed() {
	}

	public boolean isTarget() {
		return this.isTarget;
	}

	public boolean isAvailable() {
		return this.isAvailable;
	}

}
