package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.button.ButtonControl;
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
	private Question currentQuestion;
	public de.lessvoid.nifty.elements.Element questionAnswer;
	public de.lessvoid.nifty.elements.Element chooseCate;
	public de.lessvoid.nifty.elements.Element rollAgain;
	public de.lessvoid.nifty.elements.Element correct;
	public de.lessvoid.nifty.elements.Element incorrect;
	public de.lessvoid.nifty.elements.Element gameOver;
	private Player currentPlayer;
	private boolean finalTurn;
	private boolean headquarterQuestion;

	// Needed for Nifty to work
	public GameDisplayScreenState() {
	}

	public void closeGameOverPopup() {
		nifty.closePopup(gameOver.getId());
	}

	public void closeQAPopup() {
		nifty.closePopup(questionAnswer.getId());
	}
	
	public void closechooseCatePopup() {
		nifty.closePopup(chooseCate.getId());
	}

	public void closeIncorrectPopup() {
		nifty.closePopup(incorrect.getId());
	}

	public void closeCorrectPopup() {
		nifty.closePopup(correct.getId());
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
		currentPlayer = PlayerIterator.whoseTurn();
		de.lessvoid.nifty.elements.Element whoseTurnText = nifty.getCurrentScreen().findElementByName("currentPlayer");
		whoseTurnText.getRenderer(TextRenderer.class).setText(currentPlayer.getName());
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
		if (rolled) {
			if (movesRemaining > 0 && rolled) {
				player.move(new Vector3f(0, 1f, 0).mult(Game.tileSize));
				movesRemaining--;
			}
			if (movesRemaining == 0) {
				endTurn();
			}
			updateMovesRemaining();
		}
	}
	
	public void moveDown() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		if (rolled) {
			if (movesRemaining > 0) {
				player.move(new Vector3f(0, -1f, 0).mult(Game.tileSize));
				movesRemaining--;
			}
			if (movesRemaining == 0) {
				endTurn();
			}
			updateMovesRemaining();
		}
	}
	
	public void moveLeft() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		if (rolled) {
			if (movesRemaining > 0) {
				player.move(new Vector3f(-1f, 0, 0).mult(Game.tileSize));
				movesRemaining--;
			}
			if (movesRemaining == 0) {
				endTurn();
			}
			updateMovesRemaining();
		}
	}
	
	public void moveRight() {
		Spatial player = app.getRootNode().getChild(PlayerIterator.getCurrentPlayer().getId());
		if (rolled) {
			if (movesRemaining > 0) {
				player.move(new Vector3f(1f, 0, 0).mult(Game.tileSize));
				movesRemaining--;
			}
			if (movesRemaining == 0) {
				endTurn();
			}
			updateMovesRemaining();
		}
	}
	
	public void endTurn() {
		if (rolled) {
			// Get the category of the square the player is on
			Spatial playerId = app.getRootNode().getChild(currentPlayer.getId());
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
			// hack to tell when we are on a head quarter square
			String name = set.first();
			String category = app.getRootNode().getChild(name).getUserData("category");
			
			// Get question
			// TODO: Replace this with a database call
			queryForQuestion(category);
			
			// Display question
			if (category == SquareCategory.ANY.toString()) {
				if (currentPlayer.hasAllTokens()) {
					// Final turn
					finalTurn = true;
					nifty.showPopup(nifty.getCurrentScreen(), chooseCate.getId(), null);
					LabelControl instr = chooseCate.findControl("instructions", LabelControl.class);
					instr.setText("Other players: Choose a category of question to ask" + currentPlayer.getName());
				} else {
					// Player gets to choose category
					finalTurn = false;
					nifty.showPopup(nifty.getCurrentScreen(), chooseCate.getId(), null);
					LabelControl instr = chooseCate.findControl("instructions", LabelControl.class);
					instr.setText("Choose your own question category!");
				}
			} else if (name == "headquarter") {
				headquarterQuestion = true;
				nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
				populateQAPopup();
			} else if (category == SquareCategory.ROLL_AGAIN.toString()) {
				// skip question
				nifty.showPopup(nifty.getCurrentScreen(), rollAgain.getId(), null);
				rolled = false;
			} else {
				// get question
				nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
				populateQAPopup();
			}
		}
	}
	
	public void queryForQuestion(String category) {
		if (category == SquareCategory.PLACES.toString()) {
			currentQuestion = new Question("Places?", "Places1", "Places2", "Places3", "Places4", SquareCategory.PLACES);
		} else if (category == SquareCategory.PEOPLE.toString()) {
			currentQuestion = new Question("People?", "People1", "People2", "People3", "People4", SquareCategory.PEOPLE);
		} else if (category == SquareCategory.EVENTS.toString()) {
			currentQuestion = new Question("Events?", "Events1", "Events2", "Events3", "Events4", SquareCategory.EVENTS);
		} else if (category == SquareCategory.INDEPENDENCE_DAY.toString()) {
			currentQuestion = new Question("Independence Day", "indep. 1", "indep 2", "indep 3", "indep 4", SquareCategory.INDEPENDENCE_DAY);
		}
	}

	private ArrayList<String> randomizeAnswerPosition() {
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("answer1");
		answers.add("answer2");
		answers.add("answer3");
		answers.add("answer4");
		long seed = System.nanoTime();
		Collections.shuffle(answers, new Random(seed));
		return answers;
	}

	@SuppressWarnings("deprecation")
	public void populateQAPopup() {
		ArrayList<String> answers = randomizeAnswerPosition();
		LabelControl questionAns = questionAnswer.findControl("question", LabelControl.class);
		questionAns.setText(currentQuestion.getQuestion());
		ButtonControl answer1 = questionAnswer.findControl(answers.get(0), ButtonControl.class);
		answer1.setText(currentQuestion.getCorrectAnswer());
		ButtonControl answer2 = questionAnswer.findControl(answers.get(1), ButtonControl.class);
		answer2.setText(currentQuestion.getAdditionalAnswer2());
		ButtonControl answer3 = questionAnswer.findControl(answers.get(2), ButtonControl.class);
		answer3.setText(currentQuestion.getAdditionalAnswer3());
		ButtonControl answer4 = questionAnswer.findControl(answers.get(3), ButtonControl.class);
		answer4.setText(currentQuestion.getAdditionalAnswer4());
	}

	// Event listeners for buttons
	@NiftyEventSubscriber(id="answer1")
	public void onAnswer1Clicked(final String id, final ButtonClickedEvent event) {
		gradeQuestion(event.getButton().getText());
	}
	@NiftyEventSubscriber(id="answer2")
	public void onAnswer2Clicked(final String id, final ButtonClickedEvent event) {
		gradeQuestion(event.getButton().getText());
	}
	@NiftyEventSubscriber(id="answer3")
	public void onAnswer3Clicked(final String id, final ButtonClickedEvent event) {
		gradeQuestion(event.getButton().getText());
	}
	@NiftyEventSubscriber(id="answer4")
	public void onAnswer4Clicked(final String id, final ButtonClickedEvent event) {
		gradeQuestion(event.getButton().getText());
	}
	@NiftyEventSubscriber(id="peopleCate")
	public void onPeoplesCateClicked(final String id, final ButtonClickedEvent event) {
		queryForQuestion(SquareCategory.PEOPLE.toString());
		nifty.closePopup(chooseCate.getId());
		nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
		populateQAPopup();
	}
	@NiftyEventSubscriber(id="eventsCate")
	public void onEventsCateClicked(final String id, final ButtonClickedEvent event) {
		queryForQuestion(SquareCategory.EVENTS.toString());
		nifty.closePopup(chooseCate.getId());
		nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
		populateQAPopup();
	}
	@NiftyEventSubscriber(id="placesCate")
	public void onPlacesCateClicked(final String id, final ButtonClickedEvent event) {
		queryForQuestion(SquareCategory.PLACES.toString());
		nifty.closePopup(chooseCate.getId());
		nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
		populateQAPopup();
	}
	@NiftyEventSubscriber(id="independCate")
	public void onCate1Clicked(final String id, final ButtonClickedEvent event) {
		queryForQuestion(SquareCategory.INDEPENDENCE_DAY.toString());
		nifty.closePopup(chooseCate.getId());
		nifty.showPopup(nifty.getCurrentScreen(), questionAnswer.getId(), null);
		populateQAPopup();
	}

	public void gradeQuestion(String answer) {
		closeQAPopup();
		boolean correctAns = false;
		
		// Is the answer correct?
		if (answer == currentQuestion.getCorrectAnswer()) {
			correctAns = true;
		}
		// What to do if it is and if it isn't.
		if (correctAns) {
			if (finalTurn) {
				nifty.showPopup(nifty.getCurrentScreen(), gameOver.getId(), null);
			} else {
				if (headquarterQuestion && !currentPlayer.hasToken(currentQuestion.getCategory())) {
					currentPlayer.addToken(currentQuestion.getCategory());
				}
				nifty.showPopup(nifty.getCurrentScreen(), correct.getId(), null);
			}
		} else {
				nifty.showPopup(nifty.getCurrentScreen(), incorrect.getId(), null);
				// Move on to next player
				movesRemaining = 0;
				updateCurrentPlayer();
		}
		rolled = false;
		headquarterQuestion = false;
	}
	
	@Override
	public void initialize(AppStateManager stateManager, Application app) {
		super.initialize(stateManager, app);
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
		questionAnswer = nifty.createPopup("questionAnswer");
		rollAgain = nifty.createPopup("rollAgain");
		correct = nifty.createPopup("correct");
		incorrect = nifty.createPopup("incorrect");
		chooseCate = nifty.createPopup("finalTurn");
		currentPlayer = PlayerIterator.getCurrentPlayer();
		gameOver = nifty.createPopup("gameOver");
	}
	
}
