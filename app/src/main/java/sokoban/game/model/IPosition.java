package sokoban.game.model;

import sokoban.Directions;
import sokoban.game.BlockTypes;

public interface IPosition {

	void setFixed(IFixed f);

	void setMaze(IMaze maze);

	IPosition getNeighbour(Directions direction);

	boolean isVacant();

	void attach(IMovable m);

	IMovable detach();

	boolean fixedIsTarget();

	IMovable getMovable();
	//for UI
	BlockTypes getBlock();

}
