package sokoban.game.model;

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
	public boolean move(Directions direction) {
        return false;
	}

}
