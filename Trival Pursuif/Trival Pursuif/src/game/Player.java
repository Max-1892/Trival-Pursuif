package game;

import java.util.ArrayList;
import java.util.TreeSet;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;

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
	
	public void addToken(Node rootNode, String token) {
		token = token.toLowerCase();
		if (!tokens.contains(token)) {
			tokens.add(token);
			addCategoryToPlayerToken(rootNode, token);
		}
	}
	
	private void addCategoryToPlayerToken(Node rootNode, String token) {
		if (token.equals("events")) {
			Node player = (Node) rootNode.getChild(this.id.toUpperCase());
			Geometry wedge = Game.wedges.get("events");
			player.attachChild(wedge);
			wedge.setLocalTranslation(Game.tileSize*.05f, Game.tileSize*.25f, 0);
		} else if (token.equals("people")) {
			Node player = (Node) rootNode.getChild(this.id.toUpperCase());
			Geometry wedge = Game.wedges.get("people");
			player.attachChild(wedge);
			wedge.setLocalTranslation(Game.tileSize*.25f, Game.tileSize*.25f, 0);
		} else if (token.equals("places")) {
			Node player = (Node) rootNode.getChild(this.id.toUpperCase());
			Geometry wedge = Game.wedges.get("places");
			player.attachChild(wedge);
			wedge.setLocalTranslation(Game.tileSize*.25f, Game.tileSize*.05f, 0);
		} else if (token.equals("independence_day")) {
			Node player = (Node) rootNode.getChild(this.id.toUpperCase());
			Geometry wedge = Game.wedges.get("independence_day");
			player.attachChild(wedge);
			wedge.setLocalTranslation(Game.tileSize*.05f, Game.tileSize*.05f, 0);
		}
	}

	public String getName() {
		return name;
	}
}
