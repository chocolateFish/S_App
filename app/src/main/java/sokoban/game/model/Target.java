package sokoban.game.model;

public class Target extends Fixed implements IFixed {

	Target() {
		super();
		this.isAvailable = true;
		this.isTarget = true;
	}
	
	@Override
	public String toString(){
		return ".";
	}

}
