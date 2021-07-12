package game;

import java.awt.Point;

public class Ward implements Comparable{
	String whoPlaced;
	Point point;
	int minutes;
	int seconds;
	
	Ward(String whoPlaced, Point point, int minutes, int seconds){
		this.whoPlaced = whoPlaced;
		this.point = point;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public String toString(){
		String ans = "\""+this.whoPlaced+"\",\"x="+point.x+", y="+point.y+"\","+minutes+","+seconds+"\n";
		return ans;
	}
	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(!o.getClass().equals(this.getClass())) {
			return -1;
		}
		Ward t = (Ward) o;
		if(this.minutes == t.minutes) {
			return this.seconds-t.seconds;
		}
		return this.minutes-t.minutes;
	}

}
