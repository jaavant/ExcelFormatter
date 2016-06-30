package com.dfpray.formatter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.dfpray.data.*;
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
import javafx.scene.control.ButtonType;
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
	CardModel cardModel;
	ObservableList<BusinessCard> observableList;
	ObservableList<BusinessCard> notSearhedOList = FXCollections.observableArrayList();
	ListView<BusinessCard> listView;
	
	Label busLabel;
	TextArea comNotesTA;
	File openedfile;
	
	TitledPane otherTP;
	TitledPane cfTP;
	TitledPane conTP;
	TitledPane repTP;
	TitledPane mandTP;
	TitledPane comTP;
	
	TitledPane comNotesTP;
	TextField tfStreet;
	TextField tfSuitePO;
	TextField tfCity;
	TextField tfZip;
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

	boolean editing;
	Button editBtn;
	
	Button addAccBtn;
	Button delAccBtn;
	TextField searchTF;
	
	
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
		cardModel = new CardModel();
		editing = false;
		
		// File Chooser
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");

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
			leftPane.setMaxWidth(300); //20
			leftPane.setMinWidth(190);
			leftPane.setPrefWidth(220);
			
				
		    	TextField searchTF = new TextField();
		    	searchTF.setPromptText("Search");
		    	searchTF.setStyle("-fx-font: 14 Verdana;");
		    	searchTF.setMaxWidth(250);
		   
		    	listView = new ListView<BusinessCard>();
		    	listView.setStyle("-fx-font: 14 Verdana;");
		    	observableList = FXCollections.observableArrayList();
		    	listView.setItems(observableList);
				listView.setMinHeight(700); 
				listView.setMaxWidth(250);
		    	
		    	addAccBtn = new Button(" New Contact  ");
		    	addAccBtn.setStyle("-fx-font: 19 verdana; -fx-base: #e6f3ff;");   // #b6e7c9;");
		    	
		    	delAccBtn = new Button("Delete Contact");
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
				editBtn = new Button("Edit");
				editBtn.setStyle("-fx-base: #e6f3ff");
		
				buttonPane.setMaxWidth(760);
				buttonPane.setAlignment(Pos.BASELINE_RIGHT);
																//buttonPane.setStyle("-fx-background-color: blue");
				buttonPane.getChildren().addAll(editBtn);
							
				////////////////////////		
				String labelStyle = "-fx-font: 12 Verdana;";
			
		
				busLabel = new Label("Company Name");
				busLabel.setStyle("-fx-font: 30 Verdana;");
				busLabel.setPadding(new Insets(10,0,0,0));
				busLabel.setDisable(true);
				
					comNotesTA = new TextArea();
					comNotesTA.setWrapText(true);
					comNotesTA.setStyle("-fx-font:15 Verdana");
					comNotesTA.setPadding(new Insets(5,10,5,10));
					
								
				comNotesTP = new TitledPane("Company Notes",comNotesTA);
				comNotesTP.setDisable(true);
				comNotesTP.setMaxWidth(750);
				comNotesTP.setMaxHeight(200);
				comNotesTP.setCollapsible(false);
				comNotesTP.setPadding(new Insets(50,0,0,0));
				
				
				comTP = new TitledPane();
					GridPane comGP = new GridPane();
						comGP.setHgap(13);
						comGP.setVgap(10);
					
						Label lbStreet = new Label("Street Address:");
						lbStreet.setStyle(labelStyle);
						GridPane.setHalignment(lbStreet, HPos.RIGHT);
						tfStreet = new TextField();
							
						Label lbSuitePO = new Label("Suite/P.O. Box:");
						lbSuitePO.setStyle(labelStyle);
						GridPane.setHalignment(lbSuitePO, HPos.RIGHT);
						tfSuitePO = new TextField();
							
						Label lbCity = new Label("City:");
						lbCity.setStyle(labelStyle);
						GridPane.setHalignment(lbCity, HPos.RIGHT);
						tfCity = new TextField();
							
						Label lbState = new Label("State:");
						lbState.setStyle(labelStyle);
						GridPane.setHalignment(lbState, HPos.RIGHT);
						tfState = new TextField();
						
						Label lbZip = new Label("Zip Code:");
						lbZip.setStyle(labelStyle);
						GridPane.setHalignment(lbZip, HPos.RIGHT);
						tfZip = new TextField();
						
						Label lbCountry = new Label("Country:");
						lbCountry.setStyle(labelStyle);
						GridPane.setHalignment(lbCountry, HPos.RIGHT);
						tfCountry = new TextField();
				
						comGP.add(lbStreet, 0,0);  comGP.add(tfStreet, 1, 0);
						comGP.add(lbSuitePO, 0, 1);  comGP.add(tfSuitePO, 1, 1);
						comGP.add(lbCity, 0, 2);   comGP.add(tfCity, 1, 2);
						comGP.add(lbState, 2, 0); comGP.add(tfState, 3, 0);
						comGP.add(lbZip, 2, 1); comGP.add(tfZip, 3, 1);
						comGP.add(lbCountry, 2, 2); comGP.add(tfCountry, 3, 2);
						
						//mandGP.setGridLinesVisible(true);
						comGP.setPadding(new Insets(10,10,10,10));
						
			comTP.setText("Company");
			comTP.setContent(comGP);
			comTP.setCollapsible(false);
			comTP.setDisable(true);
			comTP.setMaxWidth(750);
				
				
				
				mandTP = new TitledPane();
					GridPane mandGP = new GridPane();
						mandGP.setHgap(13);
						mandGP.setVgap(10);
						
						Label lbComName = new Label("Company Name:");
						lbComName.setStyle(labelStyle);
						GridPane.setHalignment(lbComName, HPos.RIGHT);
						tfComName = new TextField();
							
						Label lbFaxNum = new Label("Fax Number:");
						lbFaxNum.setStyle(labelStyle);
						GridPane.setHalignment(lbFaxNum, HPos.RIGHT);
						tfFaxNum = new TextField();
							
						Label lbEmail = new Label("Email Address:");
						lbEmail.setStyle(labelStyle);
						GridPane.setHalignment(lbEmail, HPos.RIGHT);
						tfEmail = new TextField();
							
						Label lbcsiCode = new Label("CSI Code:");
						lbcsiCode.setStyle(labelStyle);
						GridPane.setHalignment(lbcsiCode, HPos.RIGHT);
						tfcsiCode = new TextField();
							
						Label lbComFunc = new Label("Company Function:");
						lbComFunc.setStyle(labelStyle);
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
					
				
				conTP = new TitledPane();
					GridPane conGP = new GridPane();
						conGP.setHgap(13);
						conGP.setVgap(10);
						
						Label lbphNum = new Label("Phone Number:");
						lbphNum.setStyle(labelStyle);
						GridPane.setHalignment(lbphNum, HPos.RIGHT);
						tfphNum = new TextField();
							
						Label lbExt = new Label("Ext:");
						lbExt.setStyle(labelStyle);
						GridPane.setHalignment(lbExt, HPos.RIGHT);
						tfExt = new TextField();
							
						Label lbWebsite = new Label("Website:");
						lbWebsite.setStyle(labelStyle);
						GridPane.setHalignment(lbWebsite, HPos.RIGHT);
						tfWebsite = new TextField();
							
						Label lbContactL = new Label("Contact List:");
						lbContactL.setStyle(labelStyle);
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
						
					
				repTP = new TitledPane();
					GridPane repGP = new GridPane();
					repGP.setHgap(13);
					repGP.setVgap(10);
					repGP.setPadding(new Insets(10, 10, 10, 32));
						
						Label lbFName = new Label("First Name:");
						lbFName.setStyle(labelStyle);
						GridPane.setHalignment(lbFName, HPos.RIGHT);
						tfFName = new TextField();
							
						Label lbLName = new Label("Last Name:");
						lbLName.setStyle(labelStyle);
						GridPane.setHalignment(lbLName, HPos.RIGHT);
						tfLName = new TextField();
							
						Label lbTitle = new Label("Title:");
						lbTitle.setStyle(labelStyle);
						GridPane.setHalignment(lbTitle, HPos.RIGHT);
						tfTitle = new TextField();
							
						Label lbMobile = new Label("Mobile:");
						lbMobile.setStyle(labelStyle);
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
		
			
				cfTP = new TitledPane();
					GridPane cfGP = new GridPane();
						cfGP.setHgap(13);
						cfGP.setVgap(10);
				
						Label lbAEmail = new Label("Alt Email:");
						lbAEmail.setStyle(labelStyle);
						GridPane.setHalignment(lbAEmail, HPos.RIGHT);
						tfAEmail = new TextField();
					
						Label lbSupplier= new Label("Supplier/Manufacturer:");
						lbSupplier.setStyle(labelStyle);
						GridPane.setHalignment(lbSupplier, HPos.RIGHT);
						tfSupplier = new TextField();
					
						Label lbTrade = new Label("Trade:");
						lbTrade.setStyle(labelStyle);
						GridPane.setHalignment(lbTrade, HPos.RIGHT);
						tfTrade = new TextField();
					
						Label lbUnion = new Label("Union Value:");
						lbUnion.setStyle(labelStyle);
						GridPane.setHalignment(lbUnion, HPos.RIGHT);
						tfUnion = new TextField();
					
						Label lbUnlic = new Label("Unlicensed States:");
						lbUnlic.setStyle(labelStyle);
						GridPane.setHalignment(lbUnlic, HPos.RIGHT);
						tfUnlic = new TextField();
						
						Label lbWNB = new Label("Will Not Bid:");
						lbWNB.setStyle(labelStyle);
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
			
			
			    otherTP = new TitledPane();
				GridPane otherGP = new GridPane();
					otherGP.setHgap(13);
					otherGP.setVgap(10);
				
					Label lbMbe = new Label("MBE Affiliations:");
					lbMbe.setStyle(labelStyle);
					GridPane.setHalignment(lbMbe, HPos.RIGHT);
					tfMbe = new TextField();
					
					Label lbLabor = new Label("Labor:");
					lbLabor.setStyle(labelStyle);
					GridPane.setHalignment(lbLabor, HPos.RIGHT);
					tfLabor = new TextField();

					Label lbServiceA = new Label("Service Area:");
					lbMbe.setStyle(labelStyle);
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
		stage.setMinWidth(500);
		stage.setMinHeight(500);
		stage.setScene(scene);
		stage.show();
		
		
		//Event Handlers///////
		
		//exit
		exitMI.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				System.exit(0);
		    }
		});
			
		//Open file and fill viewlist 
		openMI.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				File file = fileChooser.showOpenDialog(stage);
							
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				
				
				
                if (file != null) {
                	try {
						cardModel.addCards(file.getAbsolutePath());
					} catch (IncompleteException e1) {
						alert.setHeaderText("IOException");
						alert.setContentText("File Does Not Exist");
					} catch (IOException e1) {
						alert.setHeaderText("MalFormattedFileException");
						alert.setContentText("There was a problem opening up this file, it may be corruped or malformatted");

						alert.showAndWait();
					}
                	
          
    		    	observableList.addAll(cardModel.getCards());
    		    	FXCollections.sort(observableList);
    		    	listView.setItems(observableList);
    		    	
                }
			}      			
		});
		
		//Export to excel 
		exportMI.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				
				 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
	             fileChooser.getExtensionFilters().add(extFilter);
				
				
				File file = fileChooser.showSaveDialog(stage);				
				String path = file.getAbsolutePath();
				
				if(path == null) return;
				
				ArrayList<BusinessCard> ACards = new ArrayList<BusinessCard>();
				
				//add cards for observablelsit to an arrayList
				for(BusinessCard card : observableList){
					ACards.add(card);
				}
				//Set cardModels AList to new list
				cardModel.setCards(ACards);
				
				if(file != null){
					try {
							if(path.endsWith(".xlsx")){
								cardModel.exportToExcel(path);
							}
							else{
								cardModel.exportToExcel(path + ".xlsx");
							}
					} catch (IOException e1) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("ExportToExcelException");
						alert.setContentText("Oops, there was an error trying to process your command");
					}
				}
				fileChooser.getExtensionFilters().clear();
				
			}
		});
		
		//Edit button which disables and enables fields
    		editBtn.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent arg0) {
					if(observableList.isEmpty()) return; //so user can't edit a non existent page
					
					//not editing contact
					if(!editing){
						listView.setDisable(true);
						searchTF.setDisable(true);
						setFieldsEditable(true);
						editBtn.setText("Done");
						editBtn.setStyle("-fx-base: #ccffdd");
					}
					//Editing a current contact
					else{
						
							BusinessCard card = listView.getSelectionModel().getSelectedItem(); 
							if(card == null){
								return;
							}
							updateCard(card.getUI());
							busLabel.setText(tfComName.getText().trim());
							
							searchTF.setDisable(false);
							listView.setDisable(false);
							setFieldsEditable(false);
							
							listView.setItems(observableList);
							listView.refresh();
							
							editBtn.setText(" Edit ");
							editBtn.setStyle("-fx-base: #e6f3ff");
						
					}
					
					editing = !editing;
				}
    		});
    		
    	//Adds an account to the ViewList
    		addAccBtn.setOnAction(new EventHandler<ActionEvent>(){
    			public void handle(ActionEvent arg0){
    				BusinessCard card = new BusinessCard();

					observableList.add(card);

					FXCollections.sort(observableList);
					listView.setItems(observableList);
					listView.refresh();

					listView.getSelectionModel().select(card);
					listView.scrollTo(card);	
    				
    			}
    		});
    	
    	// Removes an account from viewlist and sorts it
    		try {
				delAccBtn.setOnAction(new EventHandler<ActionEvent>(){
					public void handle(ActionEvent arg0){
						if(observableList.isEmpty()) return; //cant delete nothing
						
						BusinessCard card  = listView.getSelectionModel().getSelectedItem();
						
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Delete Contact");
						alert.setHeaderText("You are about to delete a, " + card.getCompany().getCompanyName());
						alert.setContentText("By pressing okay, this contact will permanently be deleted!");
										
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK){
							observableList.remove((Object)card); //668
							listView.setItems(observableList);					
						}
					}
				});
			} catch (Exception e) {
				//possible null if listview is empty
			}
		
		// ListView Listener, changes text fields for the selected B.C in ViewList
		try {
			listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BusinessCard>() {
				@Override
				public void changed(ObservableValue<? extends BusinessCard> arg0, BusinessCard oldval,BusinessCard newVal) {
					if(newVal == null) return; 					
					setDataFields(newVal.getUI());		 
				}
			});
		} catch (NullPointerException e1) {
			// Null pointer thrown when list is empty
		}
		
	
		//search field than searches listview
		searchTF.textProperty().addListener(new ChangeListener<Object>() {
		      public void changed(ObservableValue<?> observable, Object oldVal, Object newVal) {
		        search((String) oldVal, (String) newVal);
		      }
		}
		);
		
	
	
	}
	
	



	/**
	 * Changes the 
	 * @param card Card which data is to be shown
	 */
	protected void setDataFields(UUID id) {
	                        //= cardModel.getCard(id);
				BusinessCard card = null;
			
				for(BusinessCard TCard : observableList){
					if(TCard.getUI().equals(id)) card = TCard; 					
				}

				if(card == null){
					cardNotFound();
				}
				
				//System.out.println("Entered Method: " + card.getCompany().getCompanyName());
				
				busLabel.setText(card.getCompany().getCompanyName());
				
				comNotesTA.setText(card.getMisc().getCompanyNotes());
				tfStreet.setText(card.getCompany().getStreetAdd());
				tfSuitePO.setText(card.getCompany().getSuitPOBox());
				tfZip.setText(card.getCompany().getZipcode());
				tfCity.setText(card.getCompany().getCity());
				tfState.setText(card.getCompany().getState());
				tfCountry.setText(card.getCompany().getCountry());
				tfComName.setText(card.getCompany().getCompanyName());
				tfFaxNum.setText(card.getContacts().getFaxNumber());
				tfEmail.setText(card.getContacts().getEmailAddress());
				tfcsiCode.setText(card.getMisc().getCsiCodes());
				tfComFunc.setText(card.getCompany().getCompanyFunction());
				tfphNum.setText(card.getContacts().getPhoneNum());
				tfExt.setText(card.getContacts().getExt());
				tfWebsite.setText(card.getContacts().getEmailAddress());
				tfContactL.setText(card.getContacts().getContactLists());
				tfFName.setText(card.getRep().getContactFirstName());
				tfLName.setText(card.getRep().getContactLastName());
				tfTitle.setText(card.getRep().getTitle());
				tfMobile.setText(card.getRep().getMobilePhone());
				tfAEmail.setText(card.getCFInfo().getCf_AltEmail());
				tfSupplier.setText(card.getCFInfo().getCf_SupplierManf());
				tfTrade.setText(card.getCFInfo().getCf_Trade());
				tfUnion.setText(card.getCFInfo().getCf_UnionValue());
				tfUnlic.setText(card.getCFInfo().getCf_UnlicensedStates());
				tfWNB.setText(card.getCFInfo().getCf_WillNotBid());
				tfMbe.setText(card.getMisc().getMbeaffiliations());
				tfLabor.setText(card.getMisc().getLabor());
				tfServiceA.setText(card.getMisc().getServiceArea());
				
				//System.out.println("Exited Method");
			
	}
	

	/**
	 * Updates a cards data to cardModel
	 * @param card Card which data is to be updates
	 */
	protected void updateCard(UUID id) {
		BusinessCard card = null;
		
		for(BusinessCard TCard : observableList){
			if(TCard.getUI().equals(id)) card = TCard; 					
		}

		if(card == null){
			cardNotFound();
		}
				
		Contacts con = new Contacts(tfphNum.getText().trim(),tfExt.getText().trim(),tfFaxNum.getText().trim(),tfWebsite.getText().trim(), tfContactL.getText().trim(),tfEmail.getText().trim());
		Representative rep = new Representative(tfFName.getText().trim(),tfLName.getText().trim(),tfTitle.getText().trim(),tfMobile.getText().trim());
		Company comp = new Company(tfComName.getText().trim(),tfStreet.getText().trim(),tfSuitePO.getText().trim(),tfCity.getText().trim(), tfState.getText().trim(),tfZip.getText().trim(), tfCountry.getText().trim(), tfComFunc.getText().trim());
		CFInfo cf = new CFInfo(tfAEmail.getText().trim(),"",tfSupplier.getText().trim(), tfTrade.getText().trim(), tfUnion.getText().trim(), tfUnlic.getText().trim(), tfWNB.getText().trim());
		Misc misc = new Misc(tfcsiCode.getText().trim(), tfMbe.getText().trim(),tfLabor.getText().trim(), tfServiceA.getText().trim(), comNotesTA.getText().trim());
		
		try {
			card.updateCard(con, rep, comp, cf, misc);
			//updateViewList();
			listView.setItems(observableList);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("EmptyListException | CardNotFoundException");
			alert.setContentText("There was an error processing your request %2.");
			alert.showAndWait();
		}
	}
	
	protected void setFieldsEditable(boolean set){
		if(set){
			busLabel.setDisable(false);
			comNotesTP.setDisable(false);
			otherTP.setDisable(false);
			cfTP.setDisable(false);
			conTP.setDisable(false);
			repTP.setDisable(false);
			mandTP.setDisable(false);
			comTP.setDisable(false); 
		}
		else{
			busLabel.setDisable(true);
			comNotesTP.setDisable(true);
			otherTP.setDisable(true);
			cfTP.setDisable(true);
			conTP.setDisable(true);
			repTP.setDisable(true);
			mandTP.setDisable(true);
			comTP.setDisable(true); 
		}
	}

	/** Creates a list determined by the text in the search field
	 * Testing a search textfield  
	 * @param oldVal Old string in textfield
	 * @param newVal New one
	 */
	protected void search(String oldVal, String newVal) {
		
		//Check we don't have the same string
		if (oldVal != null && newVal.length() < oldVal.length()) {
	      listView.setItems(observableList);
	    }
	    
	    String value = newVal.toUpperCase();
	    ObservableList<BusinessCard> subentries = FXCollections.observableArrayList();
	    
	    for (BusinessCard card : listView.getItems()) {      
	      if (!card.toString().toUpperCase().contains(value)){
	        continue;
	      }
	      subentries.add(card);
	    }
	    listView.setItems(subentries);
	   
	    //effect to select first thing in list
	    if(subentries.size() != 0){
	    	listView.getSelectionModel().select(0);
	    	listView.scrollTo(0);
	    }
	  }
	
	/**
	 * Shows A dialog if card could not be found
	 */
	private void cardNotFound() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("EmptyListException | CardNotFoundException");
		alert.setContentText("There was an error processing your request %1.");
		alert.showAndWait();
	}	

}





