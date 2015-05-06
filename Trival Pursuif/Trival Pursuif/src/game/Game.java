package game;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.math.ColorRGBA;
import com.jme3.niftygui.*;

import de.lessvoid.nifty.Nifty;

public class Game extends SimpleApplication {

	public static final float tileSize = 0.1f;
	private final Vector3f boardCenterOffset = new Vector3f(-2f,0,0);
	private static int squareNumber = 0;

	private void buildPlayer(float width, float height,
			String name, ColorRGBA color,
			Vector3f offset) {
		Quad quad = new Quad(width, height);
		Geometry geom = new Geometry(name, quad);
		Material mat = new Material(assetManager, 
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", color);
		geom.setMaterial(mat);
		geom.move(offset.mult(tileSize));
		rootNode.attachChild(geom);
	}

	private void buildBoardSquare(float width, float height, 
			String name, ColorRGBA color, 
			float moveX, float moveY,
			SquareCategory category) {
		Quad quad = new Quad(width, height);
		Geometry geom = new Geometry(name, quad);
		Material mat = new Material(assetManager, 
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", color);
		geom.setMaterial(mat);
		geom.move(new Vector3f(moveX*tileSize, moveY*tileSize, 0));
		geom.setUserData("category", category.toString());
		geom.setUserData("color", color.toString());
		rootNode.attachChild(geom);
	}
	
	private void buildLeftSide() {
		// Blue "headquarters" square
		buildBoardSquare(tileSize, tileSize,
				"blueHq", ColorRGBA.Blue, 
				-6f, 3f, SquareCategory.PLACES);
		for(int i = 1; i < 7; i++) {
			Square sq = SquareIterator.getNextSquare();
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, sq.getColor(), 
				-6f, (float)3-i, sq.getCategory());
			Game.squareNumber++;
		}
	}
	
	private void buildRightSide() {
		// white "headquarters" square
		buildBoardSquare(tileSize, tileSize,
				"whiteHq", ColorRGBA.White, 
				2f, -3f, SquareCategory.EVENTS);
		for(int i = 1; i < 7; i++) {
			Square sq = SquareIterator.getNextSquare();
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, sq.getColor(), 
				2f, (float)-3+i, sq.getCategory());
			Game.squareNumber++;
		}
	}
	
	private void buildTopSide() {
		// red "headquarters" square
		buildBoardSquare(tileSize, tileSize,
				"redHq", ColorRGBA.Red, 
				2f, 3f, SquareCategory.PEOPLE);
		for(int i = 1; i < 8; i++) {
			Square sq = SquareIterator.getNextSquare();
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, sq.getColor(), 
				(float)2-i, 3f, sq.getCategory());
			Game.squareNumber++;
		}
	}
	
	private void buildBottomSide() {
		// green "headquarters" square
		buildBoardSquare(tileSize, tileSize,
				"greenHq", ColorRGBA.Green, 
				-6f, -3f, SquareCategory.INDEPENDENCE_DAY);
		for(int i = 1; i < 8; i++) {
			Square sq = SquareIterator.getNextSquare();
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, sq.getColor(), 
				(float)-6+i, -3f, sq.getCategory());
			Game.squareNumber++;
		}
	}
	
	private void buildUpSpoke() {
		for(int i = 1; i < 3; i++) {
			Square currentSq = SquareIterator.getNextSquare();
			while (currentSq.getColor() == ColorRGBA.Orange ||
					currentSq.getColor() == ColorRGBA.Blue) {
				currentSq = SquareIterator.getNextSquare();
			}
			
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, currentSq.getColor(), 
				-2, (float)i, currentSq.getCategory());
			Game.squareNumber++;
		}
	}
	private void buildDownSpoke() {
		for(int i = 1; i < 3; i++) {
			Square currentSq = SquareIterator.getNextSquare();
			while (currentSq.getColor() == ColorRGBA.Orange) {
				currentSq = SquareIterator.getNextSquare();
			}
			
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, currentSq.getColor(), 
				-2, (float)-i, currentSq.getCategory());
			Game.squareNumber++;
		}
	}
	private void buildLeftSpoke() {
		for(int i = 1; i < 4; i++) {
			Square currentSq = SquareIterator.getNextSquare();
			while (currentSq.getColor() == ColorRGBA.Orange) {
				currentSq = SquareIterator.getNextSquare();
			}
			
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, currentSq.getColor(), 
				(float)-2-i, 0, currentSq.getCategory());
			Game.squareNumber++;
		}
	}
	private void buildRightSpoke() {
		for(int i = 1; i < 4; i++) {
			Square currentSq = SquareIterator.getNextSquare();
			while (currentSq.getColor() == ColorRGBA.Orange ||
					currentSq.getColor() == ColorRGBA.Red) {
				currentSq = SquareIterator.getNextSquare();
			}
			
			buildBoardSquare(tileSize, tileSize,
				"sq" + Game.squareNumber, currentSq.getColor(), 
				(float)-2+i, 0, currentSq.getCategory());
			Game.squareNumber++;
		}
	}
	
	private void buildBoard() {
		Quad board = new Quad(2, 2);
		Geometry boardGeom = new Geometry("board", board);
		Material boardMat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		boardMat.setColor("Color", ColorRGBA.Gray);
		boardGeom.setMaterial(boardMat);
		boardGeom.move(new Vector3f(-1f, -1f, 0));
		rootNode.attachChild(boardGeom);
		
		// Build all squares
		// Start square
		buildBoardSquare(tileSize, tileSize, 
				"startSquare", ColorRGBA.Black, 
				-2f, 0f, SquareCategory.ANY);

		buildLeftSide();
		buildUpSpoke();
		buildDownSpoke();
		buildRightSide();
		buildTopSide();
		buildBottomSide();
		buildLeftSpoke();
		buildRightSpoke();
		

		// Build all players
		// Player piece
		buildPlayer(tileSize*.5f, tileSize*.5f,
				"redPlayer", ColorRGBA.Red,
				boardCenterOffset);
	}
	
	@Override
	public void simpleInitApp() {
		buildBoard();

		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
				assetManager, inputManager, audioRenderer, guiViewPort);
		Nifty nifty = niftyDisplay.getNifty();
		
		// Create screen controllers
		StartScreenState startScreenState = new StartScreenState(this, nifty);
		LauncherScreenState launcherScreenState = new LauncherScreenState(this, nifty);
		UpdateQuestionVaultScreenState updateQuestionVaultScreenState = new UpdateQuestionVaultScreenState(this, nifty);
		AddDataScreenState addDataScreenState = new AddDataScreenState(this, nifty);
		EditDataScreenState editDataScreenState = new EditDataScreenState(this, nifty);
		GameDisplayScreenState gameDisplayScreenState = new GameDisplayScreenState(this, nifty);
		
		// Register with app's stateManager
		stateManager.attach(startScreenState);
		stateManager.attach(launcherScreenState);
		stateManager.attach(updateQuestionVaultScreenState);
		stateManager.attach(addDataScreenState);
		stateManager.attach(editDataScreenState);
		stateManager.attach(gameDisplayScreenState);
		
		// notify boilerplate
		guiViewPort.addProcessor(niftyDisplay);
		
		// load XML GUI into nifty
		nifty.fromXml("Interface/screens.xml", "start", 
				startScreenState, launcherScreenState, updateQuestionVaultScreenState,
				addDataScreenState, editDataScreenState, gameDisplayScreenState);
		cam.setParallelProjection(true);
		getFlyByCamera().setEnabled(false);
		setDisplayStatView(false);
		setDisplayFps(false);
		inputManager.setCursorVisible(true);
	}

	public static void main(String[] args) {
		Game app = new Game();
		app.start();
	}
}
