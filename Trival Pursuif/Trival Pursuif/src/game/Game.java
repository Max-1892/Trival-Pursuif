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

	@Override
	public void simpleInitApp() {
		//Box b = new Box(1, 1, 1);
		Quad b = new Quad(2, 2);
		Geometry board = new Geometry("board", b);
		Material mat = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.White);
		board.setMaterial(mat);
		board.move(new Vector3f(-1f, -1f, 0));
		
		// Start square
		Quad innerB = new Quad(1, 1);
		Geometry geom2 = new Geometry("startSquare", innerB);
		Material mat2 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat2.setColor("Color", ColorRGBA.Red);
		geom2.setMaterial(mat2);
		geom2.scale(.2f);

		Quad midSq = new Quad(1, 1);
		Geometry midS = new Geometry("endSquare", innerB);
		Material mat5 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat5.setColor("Color", ColorRGBA.Yellow);
		midS.setMaterial(mat5);
		midS.move(new Vector3f(0,-.2f,0));
		midS.scale(.2f);

		
		// End square
		Quad endSq = new Quad(1, 1);
		Geometry endS = new Geometry("endSquare", innerB);
		Material mat4 = new Material(assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		mat4.setColor("Color", ColorRGBA.Green);
		endS.setMaterial(mat4);
//		endS.move(new Vector3f(0,-.2f,0));
		endS.move(new Vector3f(-.2f,0,0));
		endS.scale(.2f);

		
		// Player piece
		Quad playerPiece = new Quad(.1f, .1f);
		Geometry player = new Geometry("playerPiece", playerPiece);
		Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat3.setColor("Color", ColorRGBA.Blue);
		player.setMaterial(mat3);
		
		// attach to root
		rootNode.attachChild(board);
		rootNode.attachChild(geom2);
		rootNode.attachChild(player);
		rootNode.attachChild(endS);
		rootNode.attachChild(midS);

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
		//cam.setLocation(new Vector3f(0,0,0.5f));
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
