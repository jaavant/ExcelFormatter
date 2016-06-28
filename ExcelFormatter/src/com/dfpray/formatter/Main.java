package com.dfpray.formatter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dfpray.data.BusinessCard;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String args[]){
		/* TODO 
		 * Controller
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
		splitPane.setPadding(new Insets(10,10,10,10));
	
			//Left Pane (List)////////////////////////
	
			VBox leftPane = new VBox(5);
			leftPane.setAlignment(Pos.CENTER);

			leftPane.setMaxWidth(220);
			leftPane.setMinWidth(190);
			
				
		    	TextField searchTF = new TextField();
		    	searchTF.setPromptText("Search");
		    	searchTF.setStyle("-fx-font: 14 Verdana;");
		    	searchTF.setMaxWidth(190);
		   
		    	ListView<BusinessCard> listView = new ListView<BusinessCard>();
		    	ObservableList<BusinessCard> observableList = FXCollections.observableList((List<BusinessCard>) myList);
		    	listView.setItems(observableList);
				listView.setMinHeight(700); 
				listView.setMaxWidth(190);
		    	
		    	Button addAccBtn = new Button(" New Contact  ");
		    	addAccBtn.setStyle("-fx-font: 19 verdana; -fx-base: #b6e7c9;");
		    	
		    	Button delAccBtn = new Button("Delete Contact");
		    	delAccBtn.setStyle("-fx-font: 19 verdana; -fx-base: #F74D60");

			leftPane.getChildren().addAll(searchTF,listView,addAccBtn,delAccBtn);
			/////////////////////////////////////////////////////////////////////

			
			
			
			
			//Right Pane/////////////////////////////////////
			ScrollPane scrollPane;
			VBox rightPane = new VBox();
		    rightPane.setPadding(new Insets(75,5,5,5));
			rightPane.setStyle("-fx-background-color: white");
			rightPane.setAlignment(Pos.TOP_CENTER);
			rightPane.setMinSize(1025, 1500);

		
				Label busLabel = new Label("Business Name");
				busLabel.setStyle("-fx-font: 45 Verdana;");
				
				TitledPane comNotesTP;
					TextArea comNotesTA = new TextArea();
					comNotesTA.setWrapText(true);
					comNotesTA.setStyle("-fx-font:15 Verdana");
					
								
				comNotesTP = new TitledPane("Company Notes",comNotesTA);
				comNotesTP.setMaxWidth(800);
				comNotesTP.setMaxHeight(200);
				comNotesTP.setCollapsible(false);
				comNotesTP.setPadding(new Insets(70,0,0,0));
				
				TitledPane mandTP = new TitledPane();
					GridPane mandGP = new GridPane();
						mandGP.setHgap(13);
						mandGP.setVgap(10);
						Label lbComName = new Label("Company Name:");
						lbComName.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbComName, HPos.RIGHT);
						TextField tfComName = new TextField();
							
						Label lbFaxNum = new Label("Fax Number:");
						lbFaxNum.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbFaxNum, HPos.RIGHT);
						TextField tfFaxNum = new TextField();
							
						Label lbEmail = new Label("Email Address:");
						lbEmail.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbEmail, HPos.RIGHT);
						TextField tfEmail = new TextField();
							
						Label lbcsiCode = new Label("CSI Code:");
						lbcsiCode.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbcsiCode, HPos.RIGHT);
						TextField tfcsiCode = new TextField();
							
						Label lbComFunc = new Label("Company Function:");
						lbComFunc.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbComFunc, HPos.RIGHT);
						TextField tfComFunc = new TextField();
				
						mandGP.add(lbComName, 0,0);  mandGP.add(tfComName, 1, 0);
						mandGP.add(lbFaxNum, 0, 1);  mandGP.add(tfFaxNum, 1, 1);
						mandGP.add(lbEmail, 0, 2);   mandGP.add(tfEmail, 1, 2);
						mandGP.add(lbcsiCode, 2, 0); mandGP.add(tfcsiCode, 3, 0);
						mandGP.add(lbComFunc, 2, 1); mandGP.add(tfComFunc, 3, 1);
							
						//mandGP.setGridLinesVisible(true);
						mandGP.setPadding(new Insets(10,10,10,10));
							
				mandTP.setText("Mandatory");
				mandTP.setContent(mandGP);
				mandTP.setCollapsible(false);
				mandTP.setMaxWidth(800);
					
				
				TitledPane conTP = new TitledPane();
					GridPane conGP = new GridPane();
						conGP.setHgap(13);
						conGP.setVgap(10);
						
						Label lbphNum = new Label("Phone Number:");
						lbphNum.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbphNum, HPos.RIGHT);
						TextField tfphNum = new TextField();
							
						Label lbExt = new Label("Ext:");
						lbExt.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbExt, HPos.RIGHT);
						TextField tfExt = new TextField();
							
						Label lbWebsite = new Label("Website:");
						lbWebsite.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbWebsite, HPos.RIGHT);
						TextField tfWebsite = new TextField();
							
						Label lbContactL = new Label("Contact List:");
						lbContactL.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbContactL, HPos.RIGHT);
						TextArea tfContactL= new TextArea();
						tfContactL.setMaxSize(250, 75);
							
						conGP.add(lbphNum, 0,0);  	 conGP.add(tfphNum, 1, 0);
						conGP.add(lbExt, 0, 1);  	 conGP.add(tfExt, 1, 1);
						conGP.add(lbWebsite, 0, 2);  conGP.add(tfWebsite, 1, 2);
						conGP.add(lbContactL, 2, 1); conGP.add(tfContactL, 3, 1);
	
						//conGP.setGridLinesVisible(true);
						conGP.setPadding(new Insets(10,10,10,10));
							
			conTP.setText("Contact");
			conTP.setContent(conGP);
			conTP.setCollapsible(false);
			conTP.setMaxWidth(800);
				
				
			rightPane.getChildren().addAll(busLabel,comNotesTP, mandTP,conTP);

			scrollPane = new ScrollPane(rightPane);
			scrollPane.setStyle("-fx-font-size: 18px;"); 
			scrollPane.setFitToWidth(true);
		    // DEBUG rightPane.setStyle("-fx-background-color: blue");
		   
		    //////////////////////////////////////////////////
						
			
		splitPane.getItems().addAll(leftPane, scrollPane);           //splitPane.setStyle("-fx-background-color: blue");
		root.getChildren().add(splitPane);	
		//////////////////////////////////////////////////////////
			
		
		//BottomPane////////////////////////////////
		HBox bottomPane = new HBox();
		bottomPane.setMaxHeight(45);
		bottomPane.setMinHeight(45);
		                                               //bottomPane.setStyle("-fx-background-color: red");
		root.getChildren().add(bottomPane);
		////////////////////////////////////////////
		
		
		//Stage
		stage.setTitle("D.F. Pray Formatter");
		stage.setMinWidth(1300);
		stage.setMinHeight(900);
		stage.setScene(scene);
		stage.show();
		
	}
	

}





