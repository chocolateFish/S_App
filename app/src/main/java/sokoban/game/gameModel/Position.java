package sokoban.game.gameModel;

import sokoban.Directions;
import sokoban.game.BlockTypes;

public class Position implements IPosition {

	private IMaze maze;
	private IMovable movable;
	private IFixed fixed;
	private int across;
	private int down;

	Position(int across, int down) {
		this.across = across;
		this.down = down;
	}

	public void setFixed(IFixed f) {
		this.fixed = f;
	}

	@Override
	public String toString() {
		String mySymbol = this.fixed.toString();
		if (this.movable.isBlank() == false) {
			mySymbol = this.movable.toString();
		}
		return mySymbol;
	}

	public void setMaze(IMaze maze) {
		this.maze = maze;
	}

	public IPosition getNeighbour(Directions dir) {
		int across = this.across + dir.horizontal;
		int down = this.down + dir.vertical;
		IPosition destination = maze.find(across, down);
		return destination;
	}

	public boolean isVacant() {
		boolean isVacant = true;
		isVacant = this.fixed.isAvailable() && (this.movable.isBlank());
		return isVacant;
	}

	public void attach(IMovable movable) {
		this.movable = movable;
		movable.setPositon(this);
	}

	public IMovable detach() {
		IMovable oldMovable = this.movable;
		this.movable = null;
		return oldMovable;
	}

	public boolean fixedIsTarget() {
		return this.fixed.isTarget();
	}

	public IMovable getMovable() {
		return this.movable;
	}

	public BlockTypes getBlocks(){
		BlockTypes myType;
		String mySymbol = this.fixed.toString() + this.movable.toString();
			switch( mySymbol){
				case "- ":
					myType = BlockTypes.FlOOR;
					break;
				case "# ":
					myType = BlockTypes.WALL;
					break;
				case ". ":
					myType = BlockTypes.TARGET;
					break;
				case "-@":
					myType = BlockTypes.FLOORMAN;
					break;
				case ".+":
					myType = BlockTypes.TARGETMAN;
					break;
				case "-$":
					myType = BlockTypes.FLOORBOX;
					break;
				case ".*":
					myType = BlockTypes.TARGETBOX;
					break;
				default:
					myType = BlockTypes.EMPTY;
					break;
			}
		return myType;
	}

}
