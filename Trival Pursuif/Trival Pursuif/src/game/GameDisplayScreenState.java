package game;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.xml.lwxs.elements.Element;

public class GameDisplayScreenState extends AbstractAppState implements ScreenController {
	private Nifty nifty;
	private SimpleApplication app;

	// Needed for Nifty to work
	public GameDisplayScreenState() {
	}
	
	public GameDisplayScreenState(SimpleApplication app, Nifty nifty) {
		this.app = app;
		this.nifty = nifty;
	}

	public void moveUp() {
		Spatial player = app.getRootNode().getChild("playerPiece");
		player.move(new Vector3f(0, .1f, 0));
	}
	
	public void moveDown() {
		Spatial player = app.getRootNode().getChild("playerPiece");
		player.move(new Vector3f(0, -.1f, 0));
	}
	
	public void moveLeft() {
		Spatial player = app.getRootNode().getChild("playerPiece");
		player.move(new Vector3f(-.1f, 0, 0));
	}
	
	public void moveRight() {
		Spatial player = app.getRootNode().getChild("playerPiece");
		player.move(new Vector3f(.1f, 0, 0));
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
