package utils;
//Class used to model signals
public class Signal implements Comparable<Signal>{
	
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

	@Override
    public boolean equals(Object o) {   
		if (!(o instanceof Signal)) { 
            return false; 
        } 
        Signal s = (Signal) o; 
          
        // Compare the data members and return accordingly  
        return (location == s.getLocation() && direction == s.getDirection()); 
    }

	@Override
	public int compareTo(Signal o) {
		int l = o.getLocation();
		Direction d = o.getDirection();
	    if(l == location) {
	    	return d == Direction.Left ? 1 : -1;
	    }
	    return location-l;
	}

}
