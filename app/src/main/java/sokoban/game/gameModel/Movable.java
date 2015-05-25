package sokoban.game.gameModel;

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

	public boolean isBlank() {
		return this.isBlank;
	}

	public abstract boolean move(Directions direction);

	//unnecessary
	//public IPosition getPostion() {
		//return this.currentPosition;
	//}
}
