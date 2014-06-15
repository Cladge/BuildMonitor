/**
 * 
 */
package buildmonitor.model;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import buildmonitor.controller.StartingView;

/**
 * @author Ian Claridge
 * 
 */
public class BuildMonitor extends Application {

	private static BuildMonitor buildMonitor;
	
	/**
	 * Get the singleton instance of this application.
	 * 
	 * @return The singleton instance
	 */
	public static BuildMonitor getBuildMonitor() {
		return buildMonitor;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Stage stage;
	private Scene scene;
	private VBox mainLayout;

	private StartingView controller;

	private String cssPath;
	private String cssSelectedPath;
	
	private static final String DEFAULT_STYLE = "DefaultTheme.css";
	private static final String DEFAULT_SELECTED_STYLE = "DefaultThemeSelected.css";
	
	private static final String INI_FILE_NAME = "BuildMonitor.xml";	//BuildMonitor.class.getResource("/buildmonitor/model/BuildMonitor.xml").toString();	
	private File iniFilePath;

	/**
	 * Return the main Stage.
	 * 
	 * @return Stage
	 */
	public Stage getPrimaryStage() {
		return stage;
	}
	
	/**
	 * Return the main scene.
	 * 
	 * @return Scene
	 */
	public Scene getScene() {
		return scene;
	}
	
	/**
	 * Return the current CSS theme (stylesheet).
	 * 
	 * @return Default CSS stylesheet path.
	 */
	public String getStyle() {
		return this.cssPath;
	}

	/**
	 * Return the current CSS theme (stylesheet) for highlighting (selecting) items.
	 * 
	 * @return Selected CSS stylesheet path.
	 */
	public String getStyleSelected() {
		return this.cssSelectedPath;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		buildMonitor = this;
		this.stage = primaryStage;
		this.stage.setTitle("BuildMonitor");
		// TODO: Make this configurable - allow the user to choose different themes
		//		 and store this in preferences.
		this.cssPath = BuildMonitor.class.getResource(DEFAULT_STYLE).toString();
		this.cssSelectedPath = BuildMonitor.class.getResource(DEFAULT_SELECTED_STYLE).toString();
		
		// TODO: Get application icon.
		this.stage.getIcons().add(new Image("file:resources/images/book.png"));

		// DEBUG
		// Get the INI file.
		//this.iniFilePath = new File(this.INI_FILE_NAME);
		this.iniFilePath = new File(BuildMonitor.class.getResource(INI_FILE_NAME).toString());
		System.out.println(this.iniFilePath);
		if(this.iniFilePath.exists()) {
			System.out.println(this.INI_FILE_NAME + " exists!");
		}
		else {
			System.out.println(this.INI_FILE_NAME + " does NOT exist!");
		}
		
		System.out.println(System.getProperty("user.home"));
		// DEBUG
		
		try {
			// Load the root layout from the "start" view fxml file.
			FXMLLoader loader = new FXMLLoader(BuildMonitor.class.getResource("/buildmonitor/view/StartingView.fxml"));
			mainLayout = (VBox) loader.load();
			scene = new Scene(mainLayout);
			this.stage.setScene(scene);
			this.stage.setResizable(false);

			// Give the controller access to the main app.
			controller = loader.getController();
			controller.setMainApp(buildMonitor);
			
			// Let the controller perform its initialisation routines.
			controller.initialise();

			// And finally...show the Stage.
			this.stage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}
	
}
