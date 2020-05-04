package utils;

public class Signal {
	
	private int location;
	private Direction direction;
	
	public Signal(int location, Direction direction) {
		setLocation(location);
		setDirection(direction);
	}
	
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	

}
