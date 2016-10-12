package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FamilyView {
	Stage stage;
	BorderPane root;
	Scene scene;
	 TextField txtName;
	 TextField txtFather;
	 TextField txtMother;
	 TextArea txtArea;
	 Button btnAdd;
	 Button btnLoad;
	 Button btnSave;
	
	public FamilyView(Stage stage) {
		this.stage = stage;

		root= new BorderPane();
		scene= new Scene(root,800,600);
		stage.setScene(scene);

		//Buttons
		 btnAdd = new Button("Add person");
		 btnLoad = new Button("Load person");
		 btnSave = new Button("Save Button");
		
		//containor for buttons
		VBox vbButtons = new VBox();
		vbButtons.getChildren().addAll(btnAdd,btnLoad,btnSave);
		
		//input fields
		 txtName = new TextField();
		Label lblName = new Label("Name: ");
		 txtFather= new TextField();
		Label lblFather = new Label("Father: ");
		 txtMother = new TextField();
		Label lblMother = new Label("Mother: ");
		HBox hbName= new HBox();
		hbName.getChildren().addAll(lblName,txtName);
		HBox hbFather= new HBox();
		hbFather.getChildren().addAll(lblFather,txtFather);
		HBox hbMother= new HBox();
		hbMother.getChildren().addAll(lblMother,txtMother);
		
		
		 txtArea = new TextArea();
		
		// container for txt 
		VBox vbinput = new VBox();
		vbinput.getChildren().addAll(txtArea, hbName,hbFather,hbMother);
		
		
		// add stuff to root
		root.setLeft(vbButtons);
		root.setCenter(vbinput);
		
		
		
		
		
		
		
	}
	
	public void start() {
		stage.show();
		
	}

}
