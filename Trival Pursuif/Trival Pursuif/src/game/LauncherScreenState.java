package game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.xml.lwxs.elements.Element;

public class LauncherScreenState extends AbstractAppState implements ScreenController {
	private Nifty nifty;
	private SimpleApplication app;

	// Needed for Nifty to work
	public LauncherScreenState() {
	}
	
	public LauncherScreenState(SimpleApplication app, Nifty nifty) {
		this.app = app;
		this.nifty = nifty;
	}

	public void switchScreens(String nextScreen) {
		nifty.gotoScreen(nextScreen);
	}
	
	public int getPlayerNames() {
		Screen screen = nifty.getCurrentScreen();
		int count = 0;
		TextField textField;
		textField = screen.findNiftyControl("red_player_name", TextField.class);
		if (textField.getDisplayedText() != null) {
			System.out.println("red: " + textField.getDisplayedText());
			count++;
		}
		textField = screen.findNiftyControl("white_player_name", TextField.class);
		if (textField.getDisplayedText() != null) {
			System.out.println("white: " + textField.getDisplayedText());
			count++;
		}
		textField = screen.findNiftyControl("blue_player_name", TextField.class);
		if (textField.getDisplayedText() != null) {
			System.out.println("blue: " + textField.getDisplayedText());
			count++;
		}
		textField = screen.findNiftyControl("green_player_name", TextField.class);
		if (textField.getDisplayedText() != null) {
			System.out.println("green: " + textField.getDisplayedText());
			count++;
		}
		System.out.println("We have " + count + " players!");
		return count;
	}

	public void quitGame() {
		// TODO: This doesn't work right now.
		app.stop();
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
		
	}
	
}
