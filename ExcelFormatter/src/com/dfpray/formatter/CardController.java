package com.dfpray.formatter;

import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;

public class CardController {
	private CardModel model;
	private CardView view;
	
	public CardController(CardModel cm, CardView v){
		model = cm;
		view = v;
		
		//Open Listener
		view.addOpenListener(e -> {
			File file = view.openFile();
			
			if(file != null){	
				try{
					model.loadCards(file.getAbsolutePath());
				} catch (ClassNotFoundException e1) {
					view.showDialog("Error","ClasSNoteFoundException","Class could not be found in this file",AlertType.ERROR);
				} catch (IOException e1) {
					view.ioDialog();		
				}
			}
		});
		
		//Save As  //TODO did i check if there was a dir
		view.addSaveAsListener(e -> {
			File file = view.saveFile();			
			model.setCards(view.getCards());
		
			if(file != null){
				try{
					model.saveCards(file.getAbsolutePath());
					view.setFilePath(file.getAbsolutePath());
				}catch (IOException ex){
					view.ioDialog();
				}
			}
		});
		
		//Save 
		view.addSaveListener(e -> {
			String path = null;
			File file;
			
			//Check if we already have a default save dir
			if(CardModel.fileExists(view.getFilePath())){
				path = view.getFilePath();
			}else{	
				file =  view.saveFile();
				if( file != null){
					view.setFilePath(file.getAbsolutePath());			
				}
			}
			cm.setCards(view.getCards());		
			
			try {
				cm.saveCards(path);
				view.setFilePath(path);
			} catch (IOException ex) {
				view.ioDialog();
			}
		
		});
		
	
	
	
	
	}
	
	
	
}
