package com.dfpray.formatter;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {		
		
		CardModel model = new CardModel();
		
		CardView scene = new CardView(stage);
		
		CardController controller= new CardController(model,scene);
		
		stage.setTitle("D.F. Pray Formatter");
		stage.setMaximized(true);
		stage.setMinWidth(500);
		stage.setMinHeight(550);
		stage.setScene(scene);
		stage.show();
	}
	
}





