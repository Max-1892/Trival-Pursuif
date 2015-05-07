package game;

import java.util.ArrayList;

public class PlayerIterator {
	static private ArrayList<Player> activePlayers;
	static private int numberOfActivePlayers;
	static private int ind;
	static private Player currentPlayer;
	
	public static ArrayList<Player> getActivePlayers() {
		return new ArrayList<Player>(activePlayers);
	}
	public static void setActivePlayers(ArrayList<Player> players) {
		activePlayers = new ArrayList<Player>(players);
		numberOfActivePlayers = activePlayers.size();
	}
	
	public static Player whoseTurn() {
		currentPlayer = activePlayers.get(ind);
		ind = (ind + 1) % numberOfActivePlayers;
		return currentPlayer;
	}
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

}
