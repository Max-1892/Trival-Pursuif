package game;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.niftygui.*;

import de.lessvoid.nifty.Nifty;

public class Game extends SimpleApplication {

	@Override
	public void simpleInitApp() {
		/*Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box", b);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);*/

		NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
				assetManager, inputManager, audioRenderer, guiViewPort);
		Nifty nifty = niftyDisplay.getNifty();
		
		// Create screen controllers
		StartScreenState startScreenState = new StartScreenState(this, nifty);
		LauncherScreenState launcherScreenState = new LauncherScreenState(this, nifty);
		UpdateQuestionVaultScreenState updateQuestionVaultScreenState = new UpdateQuestionVaultScreenState(this, nifty);
		AddDataScreenState addDataScreenState = new AddDataScreenState(this, nifty);
		EditDataScreenState editDataScreenState = new EditDataScreenState(this, nifty);
		
		// Register with app's stateManager
		stateManager.attach(startScreenState);
		stateManager.attach(launcherScreenState);
		stateManager.attach(updateQuestionVaultScreenState);
		stateManager.attach(addDataScreenState);
		stateManager.attach(editDataScreenState);
		
		// notify boilerplate
		guiViewPort.addProcessor(niftyDisplay);
		flyCam.setDragToRotate(true);
		
		// load XML GUI into nifty
		nifty.fromXml("Interface/screens.xml", "start", 
				startScreenState, launcherScreenState, updateQuestionVaultScreenState,
				addDataScreenState, editDataScreenState);
		inputManager.setCursorVisible(true);
	}

	public static void main(String[] args) {
		Game app = new Game();
		app.start();
	}
}
