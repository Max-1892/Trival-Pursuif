package game;

import java.util.Map;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class EditDataScreenState extends AbstractAppState implements ScreenController {
	private Nifty nifty;
	private SimpleApplication app;

	// Needed for Nifty to work
	public EditDataScreenState() {
	}
	
	public EditDataScreenState(SimpleApplication app, Nifty nifty) {
		this.app = app;
		this.nifty = nifty;
	}

	public void switchScreens(String nextScreen) {
		nifty.gotoScreen(nextScreen);
	}
	
	public void save() {
		Screen screen = nifty.getCurrentScreen();
		ListBox<Question> listBox = screen.findNiftyControl("edit_data_listbox", ListBox.class);
		Question selected = listBox.getSelection().get(0);
		TextField textField;
		String question;
		String correctAnswer;
		String addAns2;
		String addAns3;
		String addAns4;
		String category;
		textField = screen.findNiftyControl("edit_data_question_textfield", TextField.class);
		question = textField.getDisplayedText();
		textField = screen.findNiftyControl("edit_data_correct_answer_textfield", TextField.class);
		correctAnswer = textField.getDisplayedText();
		textField = screen.findNiftyControl("edit_data_answer2_textfield", TextField.class);
		addAns2 = textField.getDisplayedText();
		textField = screen.findNiftyControl("edit_data_answer3_textfield", TextField.class);
		addAns3= textField.getDisplayedText();
		textField = screen.findNiftyControl("edit_data_answer4_textfield", TextField.class);
		addAns4 = textField.getDisplayedText();
		textField = screen.findNiftyControl("edit_data_category_textfield", TextField.class);
		category = textField.getDisplayedText();
		selected.setAllQuestionInfo(question, correctAnswer, addAns2, addAns3, addAns4, category);
		listBox.refresh();
	}

	@NiftyEventSubscriber(id="edit_data_listbox")
	public void onListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<Question> event) {
		Question selection = event.getSelection().get(0);
		Map<String, String> info = selection.getAllQuestionInfo();
		Screen screen = nifty.getCurrentScreen();
		TextField textField;
		textField = screen.findNiftyControl("edit_data_question_textfield", TextField.class);
		textField.setText(info.get("question"));
		textField = screen.findNiftyControl("edit_data_correct_answer_textfield", TextField.class);
		textField.setText(info.get("correctAnswer"));
		textField = screen.findNiftyControl("edit_data_answer2_textfield", TextField.class);
		textField.setText(info.get("additionalAnswer2"));
		textField = screen.findNiftyControl("edit_data_answer3_textfield", TextField.class);
		textField.setText(info.get("additionalAnswer3"));
		textField = screen.findNiftyControl("edit_data_answer4_textfield", TextField.class);
		textField.setText(info.get("additionalAnswer4"));
		textField = screen.findNiftyControl("edit_data_category_textfield", TextField.class);
		textField.setText(info.get("category"));
	}
	
	public void fillMyListBox() {
		Screen screen = nifty.getCurrentScreen();
		ListBox<Question> listBox = screen.findNiftyControl("edit_data_listbox", ListBox.class);
		listBox.addItem(new Question("What is a sample question?", "correctAnswer1", 
				"addAnswer2-1", "addAnswer3-1",
				"addAnswer4-1", "category1"));
		listBox.addItem(new Question("Really though, what is a sample question?", "correctAnswer2", 
				"addAnswer2-2", "addAnswer3-2",
				"addAnswer4-2", "category2"));
		listBox.addItem(new Question("Please? What is a sample question?", "correctAnswer3", 
				"addAnswer2-3", "addAnswer3-3",
				"addAnswer4-3", "category3"));
		listBox.addItem(new Question("Anyone? What is a sample question?", "correctAnswer4", 
				"addAnswer2-4", "addAnswer3-4",
				"addAnswer4-4", "category4"));
		listBox.addItem(new Question("OK. What is a sample question?", "correctAnswer5", 
				"addAnswer2-5", "addAnswer3-5",
				"addAnswer4-5", "category5"));
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
		// TODO Auto-generated method stub
		fillMyListBox();
		
	}
	
}
