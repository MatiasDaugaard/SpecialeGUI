package utils;

public class SwitchRail implements Comparable<SwitchRail>{

	private int start;
	private int end1;
	private int end2;
	private Direction d;
	
	public SwitchRail(int start, int end1, int end2, Direction d) {
		this.setStart(start);
		this.setEnd1(end1);
		this.setEnd2(end2);
		this.setDirection(d);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd1() {
		return end1;
	}

	public void setEnd1(int end1) {
		this.end1 = end1;
	}

	public int getEnd2() {
		return end2;
	}

	public void setEnd2(int end2) {
		this.end2 = end2;
	}

	public Direction getDirection() {
		return d;
	}

	public void setDirection(Direction d) {
		this.d = d;
	}

	@Override
	public int compareTo(SwitchRail r) {
		
		return start-r.start;
	}
	

	
}
