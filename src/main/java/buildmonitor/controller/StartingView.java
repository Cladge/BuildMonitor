package buildmonitor.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.AnchorPane;
import buildmonitor.model.BuildMonitor;

/**
 * The controller for the main application layout.
 * Provides the starting point for creating the SQL patch: Version, Type, Client, etc.
 * 
 * @author Ian Claridge
 */
public class StartingView extends BaseView {
	
	private static BuildMonitor buildMonitor;

	@FXML
	private AnchorPane anchorpaneStartingView;
	
	@FXML
	private ComboBox<String> comboboxClient;
	
	/**
	 * Return reference to the main AnchorPane.
	 * 
	 * @return Entity AnchorPane.
	 */
	public AnchorPane getEntityPane () {
		return this.anchorpaneStartingView;
	}
	
	/**
	 * Main Initialise method.
	 * <li>Get cast handle of main app.
	 * <li>Populate the Client Combo Box.
	 */
	public void initialise() {
		// TODO Read required values from INI file.
		buildMonitor = (BuildMonitor) mainApp;
		comboboxClient.setItems(FXCollections.observableArrayList("Naples", "Seville", "Trident"));
	}
	
	/*
	 * Opens an about dialog.
	 */
	public void handleAbout() {
		Dialogs.showInformationDialog(buildMonitor.getPrimaryStage(), "Author:\nIan Claridge\n\nWebsite:\nhttp://www.testplant.com", "BuildMonitor v0.1 (beta)", "About");
	}

}
