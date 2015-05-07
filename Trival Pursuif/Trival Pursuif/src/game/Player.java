package game;

import java.util.ArrayList;

import com.jme3.math.ColorRGBA;

public class Player {
	private String name;
	private ColorRGBA color;
	private ArrayList<ColorRGBA> tokens;
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
		return tokens.contains(ColorRGBA.Blue) &&
		tokens.contains(ColorRGBA.Red) &&
		tokens.contains(ColorRGBA.White) &&
		tokens.contains(ColorRGBA.Green);
	}
	
	public boolean hasToken(ColorRGBA token) {
		return tokens.contains(token);
	}
	
	public void addToken(ColorRGBA token) {
		if (!tokens.contains(token)) {
			tokens.add(token);
		}
	}

	public String getName() {
		return name;
	}
}
