package game;

import java.util.ArrayList;

import com.jme3.math.ColorRGBA;

public class SquareIterator {
	private static int currentIndex = 0;
	private static final ColorRGBA[] colors = {ColorRGBA.Orange, ColorRGBA.Red, ColorRGBA.White, ColorRGBA.Orange, ColorRGBA.Blue, ColorRGBA.Green}; 
	private static final SquareCategory[] categories = {SquareCategory.ROLL_AGAIN, SquareCategory.PEOPLE, SquareCategory.EVENTS, SquareCategory.ROLL_AGAIN, SquareCategory.PLACES, SquareCategory.INDEPENDENCE_DAY};

	public static Square getNextSquare() {
		Square result = new Square(categories[currentIndex], colors[currentIndex]);
		currentIndex = (currentIndex + 1) % 6;
		return result;
		
	}

}
