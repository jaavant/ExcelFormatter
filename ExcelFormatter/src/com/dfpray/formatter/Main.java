package com.dfpray.formatter;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	public static void main(String args[]){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		View scene = new View(stage);
		
		stage.setTitle("D.F. Pray Formatter");
		stage.setMaximized(true);
		stage.setMinWidth(500);
		stage.setMinHeight(550);
		stage.setScene(scene);
		stage.show();
	}
	
}





