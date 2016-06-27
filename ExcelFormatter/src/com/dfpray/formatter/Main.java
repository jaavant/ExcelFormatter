package com.dfpray.formatter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.dfpray.data.BusinessCard;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {
	
	public static void main(String args[]){
		/* TODO 
		 * Controller
		 * Add export to excel
		 * Add read from excel
		 * Add combine excel sheet
		 * Add a save functions ("All information format?")
		 * GUI
		 * View: Prevent users from saving if B.C don't have required data
		 * ...
		*/
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ArrayList<BusinessCard> myList = new ArrayList<BusinessCard>();
			
		// File Chooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft Excel Worksheet (.xlsx)", "*.xlsx"));
		
		
		//Root Pane
		VBox root = new VBox();
		
		//Scene
		Scene scene = new Scene(root, 1300, 900);    
		
		//Menu////////////////////////////////////////
        MenuBar menuBar = new MenuBar();
        menuBar.setMinHeight(30);
        	Menu fileFile = new Menu("File");
        		MenuItem saveMI = new MenuItem("Save");
        		MenuItem openMI = new MenuItem("Open");
        		MenuItem exportMI = new MenuItem("Export");
        		MenuItem saveAsMI = new MenuItem("Save As");
        		MenuItem exitMI = new MenuItem("Exit");
        		
        		//Event Handlers///////
        		exitMI.setOnAction(new EventHandler<ActionEvent>(){
        			public void handle(ActionEvent e) {
        				System.exit(0);
        		    }
        		});
        		
        		openMI.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e) {
						File file = fileChooser.showOpenDialog(stage);
	                    if (file != null) {
	                       //TODO  openFile(file);
	                    }
					}      			
        		});    		
        		
        		
        	fileFile.getItems().addAll( openMI, saveMI, saveAsMI, exportMI,exitMI);
        		
        	Menu helpFile = new Menu("Help"); 
        menuBar.getMenus().addAll(fileFile, helpFile);
        root.getChildren().add(menuBar);
 	
		//SplitPlane///////////////////////////////////
		SplitPane splitPane = new SplitPane();
		splitPane.setPrefSize(1300,900);		
	
			//Left Pane (List)
			AnchorPane leftPane = new AnchorPane();

			leftPane.setMaxWidth(200);
			leftPane.setMinWidth(175);
			
			ListView<BusinessCard> listView = new ListView<BusinessCard>();
			ObservableList<BusinessCard> observableList = FXCollections.observableList((List<BusinessCard>) myList);
			                                                  //listView.setStyle("-fx-background-color: red");
			listView.setItems(observableList);
			listView.setMinHeight(895);
			leftPane.getChildren().add(listView);
			
			
			
			
			//Right Pane  
			AnchorPane rightPane = new AnchorPane();
			rightPane.setStyle("-fx-background-color: white");
			//leftPane.setMinSize(425,325);
			
		splitPane.getItems().addAll(leftPane, rightPane);           //splitPane.setStyle("-fx-background-color: blue");
		root.getChildren().add(splitPane);	
		/////////////////////////////////////////////
			
		
		//BottomPane////////////////////////////////
		HBox bottomPane = new HBox();
		bottomPane.setMaxHeight(45);
		bottomPane.setMinHeight(45);
		                                               //bottomPane.setStyle("-fx-background-color: red");
		root.getChildren().add(bottomPane);
		////////////////////////////////////////////
		
		
		//Stage
		stage.setTitle("Formatter");
		stage.setMinWidth(1300);
		stage.setMinHeight(900);
		stage.setScene(scene);
		stage.show();
		
	}
	

}





