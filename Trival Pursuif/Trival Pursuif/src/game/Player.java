package game;

import java.util.ArrayList;
import java.util.TreeSet;

import com.jme3.math.ColorRGBA;

public class Player {
	private String name;
	private ColorRGBA color;
	private TreeSet<String> tokens = new TreeSet<String>();
	private String id;
	public static final ColorRGBA White = new ColorRGBA(0.75f,0.75f,0.75f,1f);
	public static final ColorRGBA Red = new ColorRGBA(0.5f,0f,0f,1f);
	public static final ColorRGBA Green = new ColorRGBA(0f,0.5f,0f,1f);
	public static final ColorRGBA Blue = new ColorRGBA(0f,0f,0.5f,1f);
	
	public Player(String name, ColorRGBA color) {
		this.name = name;
		this.color = color;
		if (color == Player.Red) {
			id = "redPlayer";
		} else if (color == Player.Blue) {
			id = "bluePlayer";
		} else if (color == Player.White) {
			id = "whitePlayer";
		} else if (color == Player.Green) {
			id = "greenPlayer";
		}
	}
	
	public String getId() {
		return this.id;
	}
	public boolean hasAllTokens() {
		return tokens.contains("events") &&
		tokens.contains("places") &&
		tokens.contains("people") &&
		tokens.contains("independence_day");
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
