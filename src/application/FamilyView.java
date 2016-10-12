package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FamilyView {
	Stage stage;
	BorderPane root;
	Scene scene;
	
	public FamilyView(Stage stage) {
		this.stage = stage;
		
		
		root= new BorderPane();
		scene= new Scene(root,800,600);
		stage.setScene(scene);
		
		
		
		
		
		
	}
	
	
	
	public void start() {
		stage.show();
		
	}

}
