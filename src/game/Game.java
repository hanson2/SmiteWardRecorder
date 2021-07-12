package game;

import java.awt.Point;
import java.util.TreeSet;

public class Game {
	Board board;
	static TreeSet<Ward> wards = new TreeSet<Ward>();
	Game(Board board){
		this.board = board;
	}
	
	public static void addWard(String person, Point point, int minutes, int seconds) {
		try {
			wards.add(new Ward(person, point, minutes, seconds));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void storeWards() {
		for (Ward ward : wards) {
			System.out.println(ward);
		}
	}
}
