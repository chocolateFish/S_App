package sokoban.game.model;

import sokoban.Directions;

public interface IMovable {

	void setPositon(IPosition destination);

	boolean isBlank();

	boolean move(Directions direction);

	IPosition getPostion();

}
