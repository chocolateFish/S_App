package sokoban.game.gameModel;

public class Wall extends Fixed implements IFixed {
	Wall() {
		super();
		this.isAvailable = false;
		this.isTarget = false;
	}
	
	@Override
	public String toString(){
		return "#";
	}

}
