package sokoban.game.gameModel;

import sokoban.Directions;

public class Blank extends Movable implements IMovable {

	Blank() {
		super();
		this.isBlank = true;
	}
	
	@Override
	public String toString(){
		return " ";
	}

	@Override
	public void move(Directions direction) {
	}

}
