package utils;

public enum Direction {

	Left,Right,Up,Down;
	
	@Override
	  public String toString() {
	    switch(this) {
	      case Left: return "L";
	      case Right: return "R";
	      case Up: return "U";
	      case Down: return "D";
	      default: return "NULL";
	      
	    }
	  }
	
}
