package application;

import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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
	 TextField txtAge;
	 TextField txtWife;
	 TextField txtHusband;
	 TextField txtFamily;
	 
	 RadioButton rbGenderMale;
	 RadioButton rbGenderFemale;
	 ToggleGroup tg;
	 
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
		 txtFather= new TextField();
		 txtMother = new TextField();
		 txtAge = new TextField();
		 txtWife = new TextField();
		 txtHusband = new TextField();
		 txtFamily = new TextField();
		 
		 
		 tg= new ToggleGroup();
		 
		 rbGenderMale= new RadioButton("Male");
		 rbGenderMale.setUserData("Male");
		 rbGenderMale.setToggleGroup(tg);
		 rbGenderFemale= new RadioButton("Female");
		 rbGenderFemale.setUserData("Female");
		 rbGenderFemale.setToggleGroup(tg);
		 
		 
		
		 
		 Label lblName = new Label("Name: ");
		 Label lblFather = new Label("Father: ");
		 Label lblMother = new Label("Mother: ");
		 Label lblAge = new Label("Age: ");
		 Label lblWife = new Label("Wife: ");
		 Label lblHusband = new Label("Husband: ");
		 Label lblFamily = new Label("Family: ");
		 Label lblGender = new Label("Gender: ");
		
		
		
		
		HBox hbName= new HBox();
		hbName.getChildren().addAll(lblName,txtName);
		HBox hbFather= new HBox();
		hbFather.getChildren().addAll(lblFather,txtFather);
		HBox hbMother= new HBox();
		hbMother.getChildren().addAll(lblMother,txtMother);
		HBox hbAge= new HBox();
		hbAge.getChildren().addAll(lblAge,txtAge);
		HBox hbWife= new HBox();
		hbWife.getChildren().addAll(lblWife,txtWife);
		HBox hbHusband= new HBox();
		hbHusband.getChildren().addAll(lblHusband,txtHusband);
		HBox hbFamily= new HBox();
		hbFamily.getChildren().addAll(lblFamily,txtFamily);
		HBox hbGender = new HBox();
		hbGender.getChildren().addAll(lblGender,rbGenderMale,rbGenderFemale);
		
		
		 txtArea = new TextArea();
		
		// container for txt 
		VBox vbinput = new VBox();
		vbinput.getChildren().addAll(txtArea, hbName/*,hbFamily*/,hbAge,hbFather,hbMother,hbGender,hbWife,hbHusband );
		
		
		// add stuff to root
		root.setLeft(vbButtons);
		root.setCenter(vbinput);
		

		
	}
	
	public void start() {
		stage.show();
		
	}

}
