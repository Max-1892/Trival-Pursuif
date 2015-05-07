package game;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.xml.lwxs.elements.Element;

public class GameDisplayScreenState extends AbstractAppState implements ScreenController {
	private Nifty nifty;
	private SimpleApplication app;
	private int movesRemaining = 0;
	private Random rand = new Random();
	private boolean rolled = false;

	// Needed for Nifty to work
	public GameDisplayScreenState() {
	}
	
	public GameDisplayScreenState(SimpleApplication app, Nifty nifty) {
		this.app = app;
		this.nifty = nifty;
	}
	
	private void updateMovesRemaining() {
		de.lessvoid.nifty.elements.Element movesRemainingText = nifty.getCurrentScreen().findElementByName("movesRemaining");
		movesRemainingText.getRenderer(TextRenderer.class).setText(((Integer)movesRemaining).toString());
	}
	
	private void updateCurrentPlayer() {
		Player player = PlayerIterator.whoseTurn();
		de.lessvoid.nifty.elements.Element whoseTurnText = nifty.getCurrentScreen().findElementByName("currentPlayer");
		whoseTurnText.getRenderer(TextRenderer.class).setText(player.getName());
	}

	public void roll() {
		if (!rolled) {
			movesRemaining = rand.nextInt(6) + 1;
			updateMovesRemaining();
			rolled = true;
		}
	}
	
	public void moveUp() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		if (movesRemaining > 0) {
			player.move(new Vector3f(0, 1f, 0).mult(Game.tileSize));
			movesRemaining--;
		}
		if (movesRemaining == 0) {
			endTurn();
		}
		updateMovesRemaining();
	}
	
	public void moveDown() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		if (movesRemaining > 0) {
			player.move(new Vector3f(0, -1f, 0).mult(Game.tileSize));
			movesRemaining--;
		}
		if (movesRemaining == 0) {
			endTurn();
		}
		updateMovesRemaining();
	}
	
	public void moveLeft() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		if (movesRemaining > 0) {
			player.move(new Vector3f(-1f, 0, 0).mult(Game.tileSize));
			movesRemaining--;
		}
		if (movesRemaining == 0) {
			endTurn();
		}
		updateMovesRemaining();
	}
	
	public void moveRight() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		if (movesRemaining > 0) {
			player.move(new Vector3f(1f, 0, 0).mult(Game.tileSize));
			movesRemaining--;
		}
		if (movesRemaining == 0) {
			endTurn();
		}
		updateMovesRemaining();
	}
	
	public void endTurn() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		CollisionResults results = new CollisionResults();
		Ray ray = new Ray(player.getWorldBound().getCenter(), new Vector3f(0,0,1f));
		app.getRootNode().collideWith(ray, results);
		for (int i = 0; i < results.size(); i++) {
			Collection<String> keys = app.getRootNode().getChild((results.getCollision(i).getGeometry().getName())).getUserDataKeys();
			if (keys.contains("category") && keys.contains("color")) {
				System.out.println("Color: " + 
						app.getRootNode().getChild((results.getCollision(i).getGeometry().getName())).getUserData("color"));
				System.out.println("Category: " + 
						app.getRootNode().getChild((results.getCollision(i).getGeometry().getName())).getUserData("category"));
				System.out.println("Here");
			}
		}
		
		// Get question
		//
		
		// Display question and answers
		//de.lessvoid.nifty.elements.Element questionAnswer = nifty.createPopup("questionAnswer");
		//nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
		
		// Move on to next player
		updateCurrentPlayer();
		rolled = false;
	}
	
	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		// TODO: initialize your AppState, e.g. attach spatials to rootNode
		// this is called on the OpenGL thread after the AppState has been attached
		super.initialize(stateManager, app);
		
		/** init the screen */
	}
	
	@Override
	public void update(float tpf) {
		/** any main loop action happens here */
	}
	
	@Override
	public void cleanup() {
		// TODO: clean up what you initialized in the initialize method,
		// e.g. remove all spatials from rootNode
		// this is called on the OpenGL thread after the AppState has been detached
		super.cleanup();
	}

	@Override
	public void bind(Nifty arg0, Screen arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartScreen() {
		updateCurrentPlayer();
	}
	
}
