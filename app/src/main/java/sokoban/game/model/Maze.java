package sokoban.game.model;


import java.util.ArrayList;
import java.util.List;
import sokoban.Directions;
import sokoban.game.BlockTypes;
import sokoban.game.HasMovedCallback;

public class Maze implements IMaze {
    //TODO
    HasMovedCallback hasMovedCallback;
	List<IPosition> allPositions;
	private int height;
	private int width;
	private IMovable man;

	Maze(int width, int height) {
		this.set(width, height);
		this.allPositions = new ArrayList<IPosition>();
	}

	// constructor is public so it can be accessed by tests.
	public Maze(String mazeStr, HasMovedCallback callback) {
        this.hasMovedCallback = callback;
		this.setHeightWidthFromStr(mazeStr);
		this.allPositions = new ArrayList<IPosition>();
		this.addAllPositions(mazeStr);
	}

	private void set(int width, int height) {
		this.width = width;
		this.height = height;
	}

	private void setHeightWidthFromStr(String mazeString) {
		int size = mazeString.replace("\n", "").length();
		int width = mazeString.indexOf("\n");
		int height = size / width;
		if (height > 0 && width > 0) {
			this.set(width, height);
		} else {
			throw new RuntimeException(mazeString
					+ " is an invalid maze. Please load another maze.");
		}
	}

	private void addPostion(Character theChar, int across, int down) {
		IPosition position = new Position(across, down);
		this.allPositions.add(position);
		position.setMaze(this);
		switch (theChar) {
		case ' ':
			this.addEmpty(position);
			this.addBlank(position);
			break;
		case '#':
			this.addWall(position);
			this.addBlank(position);
			break;
		case '-':
			this.addFloor(position);
			this.addBlank(position);
			break;
		case '.':
			this.addTarget(position);
			this.addBlank(position);
			break;
		case '@':
			this.addFloor(position);
			this.addMan(position);
			break;
		case '+':
			this.addTarget(position);
			this.addMan(position);
			break;
		case '$':
			this.addFloor(position);
			this.addBox(position);
			break;
		case '*':
			this.addTarget(position);
			this.addBox(position);
			break;
		default:
			assert false : theChar;
			// throw new RuntimeException(theChar + " is unknown symbol");
		}
	}

	// No exception handling for invalid string - done by Game?
	private void addAllPositions(String mazeStr) {
		int down = 0;
		int across = 0;
		for (int index = 0; index < mazeStr.length(); index++) {
			Character theChar = mazeStr.charAt(index);
			if (theChar.equals('\n') == false && across != this.width) {
				this.addPostion(theChar, across, down);
				across++;

			} else {
				across = 0;
				down++;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int across = 0;
		for (IPosition thePosition : this.allPositions) {
			builder.append(thePosition.toString());
			across++;
			if (across == this.width) {
				builder.append('\n');
				across = 0;
			}
		}
		return builder.toString();
	}

	public IPosition find(int across, int down) {
		int index = across + (this.width * down);
		return this.allPositions.get(index);
	}

	private void addMan(IPosition pos) {
		IMovable man = new Man();
		pos.attach(man);
		this.man = man;
	}

	private void addBox(IPosition pos) {
		IMovable box = new Box();
		pos.attach(box);
	}

	private void addBlank(IPosition pos) {
		IMovable blank = new Blank();
		pos.attach(blank);
	}

	private void addFloor(IPosition pos) {
		IFixed floor = new Floor();
		pos.setFixed(floor);
	}

	private void addTarget(IPosition pos) {
		IFixed target = new Target();
		pos.setFixed(target);
	}

	private void addWall(IPosition pos) {
		IFixed wall = new Wall();
		pos.setFixed(wall);
	}

	private void addEmpty(IPosition pos) {
		IFixed empty = new Empty();
		pos.setFixed(empty);
	}

	public void playTurn(Directions direction) {
        boolean hasMoved = this.man.move(direction);
        if (hasMoved) {
            this.hasMovedCallback.hasMoved();
        }

	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	public BlockTypes whoIsAt(int across, int down){
		IPosition thePos = this.find(across, down);
		return thePos.getBlock();
	}

}
