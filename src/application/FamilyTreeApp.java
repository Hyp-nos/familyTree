package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class FamilyTreeApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 FamilyView view = new FamilyView(primaryStage);
			 FamilyModel model = new FamilyModel(view);
			 FamilyController controller = new FamilyController(model, view);
			view.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
