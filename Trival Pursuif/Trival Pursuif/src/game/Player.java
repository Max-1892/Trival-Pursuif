package game;

import java.util.ArrayList;
import java.util.TreeSet;

import com.jme3.math.ColorRGBA;

public class Player {
	private String name;
	private ColorRGBA color;
	private TreeSet<String> tokens;
	private String id;
	
	public Player(String name, ColorRGBA color) {
		this.name = name;
		this.color = color;
		if (color == ColorRGBA.Red) {
			id = "redPlayer";
		} else if (color == ColorRGBA.Blue) {
			id = "bluePlayer";
		} else if (color == ColorRGBA.White) {
			id = "whitePlayer";
		} else if (color == ColorRGBA.Green) {
			id = "greenPlayer";
		}
	}
	
	public String getId() {
		return this.id;
	}
	public boolean hasAllTokens() {
		return tokens.contains("blue") &&
		tokens.contains("red") &&
		tokens.contains("white") &&
		tokens.contains("green");
	}
	
	public boolean hasToken(String token) {
		return tokens.contains(token);
	}
	
	public void addToken(String token) {
		token = token.toLowerCase();
		if (!tokens.contains(token)) {
			tokens.add(token);
		}
	}

	public String getName() {
		return name;
	}
}
