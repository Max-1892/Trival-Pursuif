package game;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

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
import de.lessvoid.nifty.controls.label.LabelControl;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.xml.lwxs.elements.Element;
import de.lessvoid.xml.xpp3.Attributes;

public class GameDisplayScreenState extends AbstractAppState implements ScreenController {
	private Nifty nifty;
	private SimpleApplication app;
	private int movesRemaining = 0;
	private Random rand = new Random();
	private boolean rolled = false;
	public de.lessvoid.nifty.elements.Element questionAnswer;
	public de.lessvoid.nifty.elements.Element rollAgain;

	// Needed for Nifty to work
	public GameDisplayScreenState() {
	}
	
	public void closeQAPopup() {
		nifty.closePopup(questionAnswer.getId());
	}
	
	public void closeRollAgainPopup() {
		nifty.closePopup(rollAgain.getId());
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
	
	public void finalTurn() {
		Player player = PlayerIterator.whoseTurn();
		de.lessvoid.nifty.elements.Element finalTurnInstructionsText = nifty.getCurrentScreen().findElementByName("instructions");
		finalTurnInstructionsText.getRenderer(TextRenderer.class).setText("Other players: Choose a category of question to ask" + player.getName());
	}

	public void endTurn() {
		// Get the category of the square the player is on
		Spatial playerId = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		CollisionResults results = new CollisionResults();
		Ray ray = new Ray(playerId.getWorldBound().getCenter(), new Vector3f(0,0,1f));
		app.getRootNode().collideWith(ray, results);
		TreeSet<String> set = new TreeSet<String>();
		for (int i = 0; i < results.size(); i++) {
			Collection<String> keys = app.getRootNode().getChild((results.getCollision(i).getGeometry().getName())).getUserDataKeys();
			if (keys.contains("category") && keys.contains("color")) {
				set.add(results.getCollision(i).getGeometry().getName());
			}
		}
		String name = set.first();
		String category = app.getRootNode().getChild(set.first()).getUserData("category");
		Player player = PlayerIterator.getCurrentPlayer();
		
		// Get question
		Question question = new Question();
		if (category == SquareCategory.PLACES.toString()) {
			question = new Question("Places?", "Places1", "Places2", "Places3", "Places4", SquareCategory.PLACES);
		} else if (category == SquareCategory.PEOPLE.toString()) {
			question = new Question("People?", "People1", "People2", "People3", "People4", SquareCategory.PEOPLE);
		} else if (category == SquareCategory.EVENTS.toString()) {
			question = new Question("Events?", "Events1", "Events2", "Events3", "Events4", SquareCategory.EVENTS);
		} else if (category == SquareCategory.INDEPENDENCE_DAY.toString()) {
			question = new Question("Independence Day", "indep. 1", "indep 2", "indep 3", "indep 4", SquareCategory.INDEPENDENCE_DAY);
		}
		
		// Display question
		// TODO: Randomize
		if (category == SquareCategory.ANY.toString()) {
			if (player.hasAllTokens()) {
				// Final turn
				finalTurn();
			} else {
				// Player gets to choose category
				de.lessvoid.nifty.elements.Element InstructionsText = nifty.getCurrentScreen().findElementByName("instructions");
				InstructionsText.getRenderer(TextRenderer.class).setText("Choose your own question category!");
			}
		} else if (name == "headquarter") {
			questionAnswer = nifty.createPopup("questionAnswer");
			nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
			populateQAPopup(question);
			if (player.hasToken(question.getCategory())) {
				if (true/*correct*/) {
					// add token
					rollAgain = nifty.createPopup("rollAgain");
					nifty.showPopup(nifty.getCurrentScreen(), rollAgain.getId(), null);
					rolled = false;
				} else {
					// Move on to next player
					movesRemaining = 0;
					updateCurrentPlayer();
					rolled = false;
				}
			} else {
				if (true/*correct*/) {
					// Roll again
					rollAgain = nifty.createPopup("rollAgain");
					nifty.showPopup(nifty.getCurrentScreen(), rollAgain.getId(), null);
					rolled = false;
				} else {
					// Move on to next player
					movesRemaining = 0;
					updateCurrentPlayer();
					rolled = false;
				}
			}
		} else if (category == SquareCategory.ROLL_AGAIN.toString()) {
			// skip question
			rollAgain = nifty.createPopup("rollAgain");
			nifty.showPopup(nifty.getCurrentScreen(), rollAgain.getId(), null);
			rolled = false;
		} else {
			// get question
			questionAnswer = nifty.createPopup("questionAnswer");
			nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
			populateQAPopup(question);
			if (false /*correct*/) {
				rollAgain = nifty.createPopup("rollAgain");
				nifty.showPopup(nifty.getCurrentScreen(), rollAgain.getId(), null);
				rolled = false;
			} else {
				// Move on to next player
				movesRemaining = 0;
				updateCurrentPlayer();
				rolled = false;
			}
		}
	}
	
	public void populateQAPopup(Question question) {
		LabelControl questionAns = questionAnswer.findControl("question", LabelControl.class);
		if (questionAns != null) {
			questionAns.setText(question.getQuestion());
		} else {
			System.out.println("as;lkdfj;alskdjf;laksjdf;lakjsdf");
		}
		//de.lessvoid.nifty.elements.Element questionAns= nifty.getTopMostPopup().findElementByName("question");
		//questionAnswer.getRenderer(TextRenderer.class).setText(question.getQuestion());
		//de.lessvoid.nifty.elements.Element answer1Text = nifty.getCurrentScreen().findElementByName("answer1");
		//answer1Text.getRenderer(TextRenderer.class).setText(question.getCorrectAnswer());
		//de.lessvoid.nifty.elements.Element answer2Text = nifty.getCurrentScreen().findElementByName("answer2");
		//answer2Text.getRenderer(TextRenderer.class).setText(question.getAdditionalAnswer2());
		//de.lessvoid.nifty.elements.Element answer3Text = nifty.getCurrentScreen().findElementByName("answer3");
		//answer3Text.getRenderer(TextRenderer.class).setText(question.getAdditionalAnswer3());
		//de.lessvoid.nifty.elements.Element answer4Text = nifty.getCurrentScreen().findElementByName("answer4");
		//answer4Text.getRenderer(TextRenderer.class).setText(question.getAdditionalAnswer4());
	}

	public boolean gradeQuestion(Question question) {
		boolean result = false;
		
		return result;
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
