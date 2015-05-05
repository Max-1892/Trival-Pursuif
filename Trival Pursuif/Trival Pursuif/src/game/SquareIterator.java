package game;

import java.util.ArrayList;

import com.jme3.math.ColorRGBA;

public class SquareIterator {
	private static int currentIndex = 0;
	private static final ColorRGBA[] colors = {ColorRGBA.Red, ColorRGBA.Gray, ColorRGBA.Orange, ColorRGBA.Blue, ColorRGBA.Green}; 
	private static final SquareCategory[] categories = {SquareCategory.PEOPLE, SquareCategory.EVENTS, SquareCategory.ROLL_AGAIN, SquareCategory.PLACES, SquareCategory.INDEPENDENCE_DAY};

	public static Square getNextSquare() {
		Square result = new Square(categories[currentIndex], colors[currentIndex]);
		currentIndex = (currentIndex + 1) % 4;
		return result;
		
	}

}
