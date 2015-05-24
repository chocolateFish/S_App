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
	public void move(Directions direction) {
		IPosition from = this.currentPosition;
		IPosition destination = from.getNeighbour(direction);
		if (destination.isVacant()) {
			IMovable oldMovable = destination.detach();
			destination.attach(this);
			from.attach(oldMovable);
		}
	}

}
