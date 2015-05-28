package sokoban.game.model;

public class Empty extends Fixed implements IFixed {

	Empty() {
		super();
		this.isAvailable = false;
		this.isTarget = false;
	}
	
	@Override
	public String toString(){
		return " ";
	}

}
