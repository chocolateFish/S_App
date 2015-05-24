package sokoban.game.gameModel;

import sokoban.Directions;

public class Man extends Movable implements IMovable {

	Man() {
		super();
		this.isBlank = false;
	}

	@Override
	public String toString() {
		String symbol = "@";
		if (this.currentPosition.fixedIsTarget()) {
			symbol = "+";
		}
		return symbol;
	}

	@Override
	public void move(Directions direction) {
		IPosition from = this.currentPosition;
		IPosition destination = from.getNeighbour(direction);
		// base case
		if (destination.isVacant()) {
			IMovable oldMovable = destination.detach();
			destination.attach(this);
			from.attach(oldMovable);
		} else {
			destination.getMovable().move(direction);
			if (destination.isVacant()) {
				this.move(direction);
			}
		}
	}

}
