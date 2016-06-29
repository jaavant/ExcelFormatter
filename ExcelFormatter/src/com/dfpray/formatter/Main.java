package com.dfpray.formatter;

import java.io.File;
import java.io.IOException;
import com.dfpray.data.BusinessCard;
import com.dfpray.exception.IncompleteException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	FileChooser fileChooser;
	CardModel cardModel = new CardModel();
	ObservableList<BusinessCard> observableList;
	ListView<BusinessCard> listView;
	
	Label busLabel;
	TextArea comNotesTA;
	File openedfile;
	
	TextField tfStreet;
	TextField tfSuitePO;
	TextField tfCity;
	TextField tfState;
	TextField tfCountry;
	TextField tfComName;
	TextField tfFaxNum;
	TextField tfEmail;
	TextField tfcsiCode;
	TextField tfComFunc;
	TextField tfphNum;
	TextField tfExt;
	TextField tfWebsite;
	TextArea tfContactL;
	TextField tfFName;
	TextField tfLName;
	TextField tfTitle;
	TextField tfMobile;
	TextField tfAEmail;
	TextField tfSupplier;
	TextField tfTrade;
	TextField tfUnion;
	TextField tfUnlic;
	TextField tfWNB;
	TextField tfMbe;
	TextField tfLabor;	
	TextField tfServiceA;


	
	public static void main(String args[]){
		/* TODO 
		 * Add combine excel sheet
		 * Add a save functions ("All information format?")
		 * View: Prevent users from saving if B.C don't have required data
		 * ...
		*/
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {	
	
		// File Chooser
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		//fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TEXT files (*.txt)","*.txt"), 
		//		new ExtensionFilter("Microsoft Excel Worksheet (.xlsx)", "*.xlsx"));
	
		//Root Pane
		VBox root = new VBox();
		
		//Scene
		Scene scene = new Scene(root, 1300, 900);
		
		//Menu////////////////////////////////////////
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-font:15 Verdana");
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
        		
        		
        		//Open file and fill viewlist 
        		openMI.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent e) {
						File file = fileChooser.showOpenDialog(stage);					
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Error");
						
	                    if (file != null) {
	                    	try {
								cardModel.addCards(file.getAbsolutePath());
							} catch (IncompleteException e1) {
								alert.setHeaderText("File Does Not Exist");
								alert.setContentText("Oops there was an Error");
							} catch (IOException e1) {
								alert.setHeaderText("IOException");
								alert.setContentText("There was a problem opening up this file, it may be corruped or malformatted");

								alert.showAndWait();
							}
	                 	                    	                    	
	        		    	observableList = FXCollections.observableList(cardModel.getCards());
	        		    	listView.setItems(observableList);
	        		    	
	                    }
					}      			
        		});    		
        		
 //       		//List view listener, changes the data fields according to the selected company
 //       		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BusinessCard>(){
 //       			@Override
 //       			public void changed(ObservableValue<? extends BusinessCard> observable, BusinessCard oldValue, BusinessCard newValue){
 //       				try {
//							setDataFields(cardModel.getCard(newValue.getUI()));
//						} catch (IncompleteException e) {
//							e.printStackTrace();					
//						}
//       			}
//      		});
       		
        		
        		
        	fileFile.getItems().addAll( openMI, saveMI, saveAsMI, exportMI,exitMI);
        		
        	Menu helpFile = new Menu("Help"); 
        menuBar.getMenus().addAll(fileFile, helpFile);
        root.getChildren().add(menuBar);        
        
        
		//SplitPlane///////////////////////////////////
		SplitPane splitPane = new SplitPane();
		splitPane.setPrefSize(1300,900);	
		//splitPane.setPadding(new Insets(10,10,10,10));
	
			//Left Pane (List)////////////////////////
	
			VBox leftPane = new VBox(5);
			leftPane.setAlignment(Pos.CENTER);
			leftPane.setPadding(new Insets(7,7,7,7));
			leftPane.setMaxWidth(250); //20
			leftPane.setMinWidth(190);
			leftPane.setPrefWidth(220);
			
				
		    	TextField searchTF = new TextField();
		    	searchTF.setPromptText("Search");
		    	searchTF.setStyle("-fx-font: 14 Verdana;");
		    	searchTF.setMaxWidth(190);
		   
		    	listView = new ListView<BusinessCard>();
		    	observableList = FXCollections.observableList(cardModel.getCards());
		    	listView.setItems(observableList);
				listView.setMinHeight(700); 
				listView.setMaxWidth(190);
		    	
		    	Button addAccBtn = new Button(" New Contact  ");
		    	addAccBtn.setStyle("-fx-font: 19 verdana; -fx-base: #e6f3ff;");   // #b6e7c9;");
		    	
		    	Button delAccBtn = new Button("Delete Contact");
		    	delAccBtn.setStyle("-fx-font: 19 verdana; -fx-base: #e6f3ff;"); //#F74D60");

			leftPane.getChildren().addAll(searchTF,listView,addAccBtn,delAccBtn);
			/////////////////////////////////////////////////////////////////////

		
			
			//Right Pane/////////////////////////////////////
			
			ScrollPane scrollPane;
			VBox rightPane = new VBox();
		    rightPane.setPadding(new Insets(75,2,2,2));
														//rightPane.setStyle("-fx-background-color: white");
			rightPane.setAlignment(Pos.TOP_CENTER);
																//rightPane.setMinSize(1025, 1500);
			
				//Top Pane///////////////
				VBox buttonPane = new VBox(5);
				Button editBtn = new Button("Edit");
				editBtn.setStyle("-fx-base: #e6f3ff");
		
				buttonPane.setMaxWidth(760);
				buttonPane.setAlignment(Pos.BASELINE_RIGHT);
																//buttonPane.setStyle("-fx-background-color: blue");
				buttonPane.getChildren().addAll(editBtn);
							
				////////////////////////		
			
			
		
				busLabel = new Label("Company Name");
				busLabel.setStyle("-fx-font: 45 Verdana;");
				
				TitledPane comNotesTP;
					comNotesTA = new TextArea();
					comNotesTA.setWrapText(true);
					comNotesTA.setStyle("-fx-font:15 Verdana");
					comNotesTA.setPadding(new Insets(5,10,5,10));
					
								
				comNotesTP = new TitledPane("Company Notes",comNotesTA);
				comNotesTP.setDisable(true);
				comNotesTP.setMaxWidth(750);
				comNotesTP.setMaxHeight(200);
				comNotesTP.setCollapsible(false);
				comNotesTP.setPadding(new Insets(70,0,0,0));
				
				
				TitledPane comTP = new TitledPane();
				GridPane comGP = new GridPane();
					comGP.setHgap(13);
					comGP.setVgap(10);
					
					Label lbStreet = new Label("Street Address:");
					lbStreet.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbStreet, HPos.RIGHT);
					tfStreet = new TextField();
						
					Label lbSuitePO = new Label("Suite/P.O. Box:");
					lbSuitePO.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbSuitePO, HPos.RIGHT);
					tfSuitePO = new TextField();
						
					Label lbCity = new Label("City:");
					lbCity.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbCity, HPos.RIGHT);
					tfCity = new TextField();
						
					Label lbState = new Label("State:");
					lbState.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbState, HPos.RIGHT);
					tfState = new TextField();
						
					Label lbCountry = new Label("Country:");
					lbCountry.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbCountry, HPos.RIGHT);
					tfCountry = new TextField();
			
					comGP.add(lbStreet, 0,0);  comGP.add(tfStreet, 1, 0);
					comGP.add(lbSuitePO, 0, 1);  comGP.add(tfSuitePO, 1, 1);
					comGP.add(lbCity, 0, 2);   comGP.add(tfCity, 1, 2);
					comGP.add(lbState, 2, 0); comGP.add(tfState, 3, 0);
					comGP.add(lbCountry, 2, 1); comGP.add(tfCountry, 3, 1);
						
					//mandGP.setGridLinesVisible(true);
					comGP.setPadding(new Insets(10,10,10,10));
						
			comTP.setText("Company");
			comTP.setContent(comGP);
			comTP.setCollapsible(false);
			comTP.setDisable(true);
			comTP.setMaxWidth(750);
				
				
				
				TitledPane mandTP = new TitledPane();
					GridPane mandGP = new GridPane();
						mandGP.setHgap(13);
						mandGP.setVgap(10);
						
						Label lbComName = new Label("Company Name:");
						lbComName.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbComName, HPos.RIGHT);
						tfComName = new TextField();
							
						Label lbFaxNum = new Label("Fax Number:");
						lbFaxNum.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbFaxNum, HPos.RIGHT);
						tfFaxNum = new TextField();
							
						Label lbEmail = new Label("Email Address:");
						lbEmail.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbEmail, HPos.RIGHT);
						tfEmail = new TextField();
							
						Label lbcsiCode = new Label("CSI Code:");
						lbcsiCode.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbcsiCode, HPos.RIGHT);
						tfcsiCode = new TextField();
							
						Label lbComFunc = new Label("Company Function:");
						lbComFunc.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbComFunc, HPos.RIGHT);
						tfComFunc = new TextField();
				
						mandGP.add(lbComName, 0,0);  mandGP.add(tfComName, 1, 0);
						mandGP.add(lbFaxNum, 0, 1);  mandGP.add(tfFaxNum, 1, 1);
						mandGP.add(lbEmail, 0, 2);   mandGP.add(tfEmail, 1, 2);
						mandGP.add(lbcsiCode, 2, 0); mandGP.add(tfcsiCode, 3, 0);
						mandGP.add(lbComFunc, 2, 1); mandGP.add(tfComFunc, 3, 1);
							
						//mandGP.setGridLinesVisible(true);
						mandGP.setPadding(new Insets(10,10,10,10));
							
				mandTP.setText("Mandatory");
				mandTP.setContent(mandGP);
				mandTP.setDisable(true);
				mandTP.setCollapsible(false);
				mandTP.setMaxWidth(750);
					
				
				TitledPane conTP = new TitledPane();
					GridPane conGP = new GridPane();
						conGP.setHgap(13);
						conGP.setVgap(10);
						
						Label lbphNum = new Label("Phone Number:");
						lbphNum.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbphNum, HPos.RIGHT);
						tfphNum = new TextField();
							
						Label lbExt = new Label("Ext:");
						lbExt.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbExt, HPos.RIGHT);
						tfExt = new TextField();
							
						Label lbWebsite = new Label("Website:");
						lbWebsite.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbWebsite, HPos.RIGHT);
						tfWebsite = new TextField();
							
						Label lbContactL = new Label("Contact List:");
						lbContactL.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbContactL, HPos.RIGHT);
						tfContactL = new TextArea();
						tfContactL.setMaxSize(250, 75);
							
						conGP.add(lbphNum, 0,0);  	 conGP.add(tfphNum, 1, 0);
						conGP.add(lbExt, 0, 1);  	 conGP.add(tfExt, 1, 1);
						conGP.add(lbWebsite, 0, 2);  conGP.add(tfWebsite, 1, 2);
						conGP.add(lbContactL, 2, 1); conGP.add(tfContactL, 3, 1);
	
						//conGP.setGridLinesVisible(true);
						conGP.setPadding(new Insets(10,10,10,10));
							
					conTP.setText("Contact");
					conTP.setContent(conGP);
					conTP.setDisable(true);
					conTP.setCollapsible(false);
					conTP.setMaxWidth(750);
						
					
				TitledPane repTP = new TitledPane();
					GridPane repGP = new GridPane();
					repGP.setHgap(13);
					repGP.setVgap(10);
					repGP.setPadding(new Insets(10, 10, 10, 32));
						
						Label lbFName = new Label("First Name:");
						lbFName.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbFName, HPos.RIGHT);
						tfFName = new TextField();
							
						Label lbLName = new Label("Last Name:");
						lbLName.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbLName, HPos.RIGHT);
						tfLName = new TextField();
							
						Label lbTitle = new Label("Title:");
						lbTitle.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbTitle, HPos.RIGHT);
						tfTitle = new TextField();
							
						Label lbMobile = new Label("Mobile:");
						lbMobile.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbMobile, HPos.RIGHT);
						tfMobile = new TextField();
							
						repGP.add(lbFName, 0,0);  	 repGP.add(tfFName, 1, 0);
						repGP.add(lbLName, 0, 1);  	 repGP.add(tfLName, 1, 1);
						repGP.add(lbTitle, 3, 0);    repGP.add(tfTitle, 4, 0);
						repGP.add(lbMobile, 3, 1);   repGP.add(tfMobile, 4, 1);
		
							
				repTP.setText("Representative");
				repTP.setContent(repGP);
				repTP.setDisable(true);
				repTP.setCollapsible(false);
				repTP.setMaxWidth(750);
		
			
				TitledPane cfTP = new TitledPane();
					GridPane cfGP = new GridPane();
						cfGP.setHgap(13);
						cfGP.setVgap(10);
				
						Label lbAEmail = new Label("Alt Email:");
						lbAEmail.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbAEmail, HPos.RIGHT);
						tfAEmail = new TextField();
					
						Label lbSupplier= new Label("Supplier/Manufacturer:");
						lbSupplier.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbSupplier, HPos.RIGHT);
						tfSupplier = new TextField();
					
						Label lbTrade = new Label("Trade:");
						lbTrade.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbTrade, HPos.RIGHT);
						tfTrade = new TextField();
					
						Label lbUnion = new Label("Union Value:");
						lbUnion.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbUnion, HPos.RIGHT);
						tfUnion = new TextField();
					
						Label lbUnlic = new Label("Unlicensed States:");
						lbUnlic.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbUnlic, HPos.RIGHT);
						tfUnlic = new TextField();
						
						Label lbWNB = new Label("Will Not Bid:");
						lbWNB.setStyle("-fx-font: 12 Verdana;");
						GridPane.setHalignment(lbWNB, HPos.RIGHT);
						tfWNB = new TextField();
		
		
					cfGP.add(lbAEmail, 0,0);    cfGP.add(tfAEmail, 1, 0);
					cfGP.add(lbSupplier, 0, 1);  cfGP.add(tfSupplier, 1, 1);
					cfGP.add(lbTrade, 0, 2);   cfGP.add(tfTrade, 1, 2);
					cfGP.add(lbUnion, 2, 0); cfGP.add(tfUnion, 3, 0);
					cfGP.add(lbUnlic, 2, 1); cfGP.add(tfUnlic, 3, 1);
					cfGP.add(lbWNB, 2, 2); cfGP.add(tfWNB, 3, 2);
					
					//cfGP.setGridLinesVisible(true);
					cfGP.setPadding(new Insets(10,10,10,10));
					
				cfTP.setText("CF Info");
				cfTP.setContent(cfGP);
				cfTP.setDisable(true);
				cfTP.setCollapsible(false);
				cfTP.setMaxWidth(750);	
			
			
			TitledPane otherTP = new TitledPane();
				GridPane otherGP = new GridPane();
					otherGP.setHgap(13);
					otherGP.setVgap(10);
				
					Label lbMbe = new Label("MBE Affiliations:");
					lbMbe.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbMbe, HPos.RIGHT);
					tfMbe = new TextField();
					
					Label lbLabor = new Label("Labor:");
					lbLabor.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbLabor, HPos.RIGHT);
					tfLabor = new TextField();

					Label lbServiceA = new Label("Service Area:");
					lbMbe.setStyle("-fx-font: 12 Verdana;");
					GridPane.setHalignment(lbMbe, HPos.RIGHT);
					tfServiceA = new TextField();
					
					otherGP.add(lbMbe, 0, 0);		otherGP.add(tfMbe, 1, 0);
					otherGP.add(lbLabor,0,1); 		otherGP.add(tfLabor, 1, 1);
					otherGP.add(lbServiceA, 3, 0);	otherGP.add(tfServiceA, 4, 0);
					
					
					otherGP.setPadding(new Insets(10,10,10,10));
				
				otherTP.setText("Other");
				otherTP.setContent(otherGP);
				otherTP.setDisable(true);
				otherTP.setCollapsible(false);
				otherTP.setMaxWidth(750);	
					
					
					
					
			rightPane.getChildren().addAll(buttonPane,busLabel,comNotesTP,mandTP,comTP,conTP,repTP,cfTP,otherTP);

			scrollPane = new ScrollPane(rightPane);
			scrollPane.setStyle("-fx-font-size: 15px;"); 
			scrollPane.setFitToWidth(true);
		    // DEBUG rightPane.setStyle("-fx-background-color: blue");
		   
		    //////////////////////////////////////////////////
						
			
		splitPane.getItems().addAll(leftPane, scrollPane);           //splitPane.setStyle("-fx-background-color: blue");
		root.getChildren().add(splitPane);	
		//////////////////////////////////////////////////////////
			
		
		//BottomPane////////////////////////////////
		HBox bottomPane = new HBox();
		bottomPane.setMaxHeight(50);
		bottomPane.setMinHeight(50);
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

	protected void setDataFields(BusinessCard card) {
		// TODO Auto-generated method stub	
	}
	
	protected void updateCard(BusinessCard card) {
		// TODO Auto-generated method stub
	}
	
	public void setFieldsEditable(boolean set){
		// TODO 
	}
	

}





