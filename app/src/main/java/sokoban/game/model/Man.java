package sokoban.game.model;

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
    //TODO implement this better
	@Override
	public boolean move(Directions direction) {
        boolean hasMoved = false;
		IPosition from = this.currentPosition;
		IPosition destination = from.getNeighbour(direction);
		// base case
		if (destination.isVacant()) {
			IMovable oldMovable = destination.detach();
			destination.attach(this);
			from.attach(oldMovable);
            hasMoved = true;
		} else {
			destination.getMovable().move(direction);
			if (destination.isVacant()) {
				this.move(direction);
                hasMoved = true;
			}
		}

        return hasMoved;
	}

}
