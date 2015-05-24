package sokoban.game.gameModel;

public class Floor extends Fixed implements IFixed {

	Floor() {
		super();
		this.isAvailable = true;
		this.isTarget = false;
	}
	
	@Override
	public String toString(){
		return "-";
	}

}
