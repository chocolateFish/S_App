package sokoban.game.gameModel;

import sokoban.Directions;

public interface IMovable {

	void setPositon(IPosition destination);

	boolean isBlank();

	void move(Directions direction);

	//unnecessary??
	//IPosition getPostion();

}
