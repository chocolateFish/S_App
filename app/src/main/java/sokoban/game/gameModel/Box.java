package sokoban.game.gameModel;
import sokoban.Directions;

public class Box extends Movable implements IMovable {

	Box() {
		super();
		this.isBlank = false;
	}

	@Override
	public String toString(){
		String theSymbol = "$";
		if (this.currentPosition.fixedIsTarget()) {
			theSymbol = "*";
		}
		return theSymbol;
	}

	@Override
	public boolean move(Directions direction) {
        boolean hasMoved = false;
		IPosition from = this.currentPosition;
		IPosition destination = from.getNeighbour(direction);
		if (destination.isVacant()) {
			IMovable oldMovable = destination.detach();
			destination.attach(this);
			from.attach(oldMovable);
            hasMoved = true;
		}
        return hasMoved;
	}
}
