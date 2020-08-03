package utils;
//Class used to model a rail
public class Rail implements Comparable<Rail>{

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
          
        return (start == r.getStart() && end == r.getEnd())|| (start == r.getEnd() && end == r.getStart()); 
    } 
	

	@Override
	public int compareTo(Rail r) {
		int r1s = Math.min(start, end);
	    int r1e = Math.max(start, end);
	    int r2s = Math.min(r.start, r.end);
	    int r2e = Math.max(r.start, r.end);
	    if(r1s == r2s) {
	    	return r1e-r2e;
	    }
	    return r1s-r2s;
	}
	
}
