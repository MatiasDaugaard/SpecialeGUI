package utils;

public class Rail {

	private int start;
	private int end;
	
	public Rail(int start, int end) {
		this.setStart(start);
		this.setEnd(end);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	@Override
    public boolean equals(Object o) {   
		if (!(o instanceof Rail)) { 
            return false; 
        } 
        Rail r = (Rail) o; 
          
        // Compare the data members and return accordingly  
        return (start == r.getStart() && end == r.getEnd())|| (start == r.getEnd() && end == r.getStart()); 
    } 
	
}
