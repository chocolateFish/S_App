package sokoban.game.gameModel;

import sokoban.Directions;
import sokoban.game.BlockTypes;

public interface IMaze {
	IPosition find(int across, int down);
	void playTurn(Directions direction);

	//for UI
	int getHeight();
	int getWidth();

	BlockTypes whoIsAt(int across, int down);
}
