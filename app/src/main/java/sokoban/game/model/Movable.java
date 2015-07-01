package sokoban.game.model;

import sokoban.Directions;

public abstract class Movable implements IMovable{
	protected IPosition currentPosition;
	protected boolean isBlank;

	Movable() {
	}

	// IMovable
	public void setPositon(IPosition destination) {
		this.currentPosition = destination;
	}
	public boolean isBlank() {return this.isBlank; }
	public abstract boolean move(Directions direction);


	public IPosition getPostion() {
		return this.currentPosition;
	}
}
