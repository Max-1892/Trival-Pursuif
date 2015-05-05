package game;

import com.jme3.math.ColorRGBA;

public class Square {
	private SquareCategory category;
	private ColorRGBA color;
	
	public Square(SquareCategory category, ColorRGBA color) {
		this.setCategory(category);
		this.setColor(color);
	}

	public SquareCategory getCategory() {
		return category;
	}

	private void setCategory(SquareCategory cat) {
		this.category = cat;
	}

	public ColorRGBA getColor() {
		return color;
	}

	private void setColor(ColorRGBA color) {
		this.color = color;
	}

}
