package utils;
//Class used to model trains
public class Train {

	private int startLocation;
	private int endLocation;
	private Direction direction;
	
	public Train(int start, int end, Direction direction) {
		setStartLocation(start);
		setEndLocation(end);
		this.setDirection(direction);
	}

	public int getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(int startLocation) {
		this.startLocation = startLocation;
	}

	public int getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(int endLocation) {
		this.endLocation = endLocation;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}
