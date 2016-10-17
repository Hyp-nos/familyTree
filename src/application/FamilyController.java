package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class FamilyController {
	FamilyModel model;
	FamilyView view;
	

	public FamilyController (FamilyModel model, FamilyView view){
		this.model=model;
		this.view=view;
		
		
		view.btnAdd.setOnAction(e-> {
			
			model.createPerson(view.txtName.getText(),view.txtFather.getText(),view.txtMother.getText(), 
			 view.tg.getSelectedToggle().getUserData().toString(), view.txtHusband.getText(), view.txtWife.getText(), (Integer.parseInt(view.txtAge.getText())) );
			view.txtArea.appendText(model.updateViewAfterAdd());
		});
		// for Radio Buttons
		view.tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				RadioButton selected = (RadioButton)newValue.getToggleGroup().getSelectedToggle();
				 if (selected.getText().equalsIgnoreCase("MALE")){
				
					 view.txtHusband.setDisable(true);
					 view.txtWife.setDisable(false);
			 }else{
				 view.txtWife.setDisable(true);
				 view.txtHusband.setDisable(false);
			
			 }
				 
			}
		});
		
		view.btnLoad.setOnAction(E-> {
			model.loadPerson();
		});
		
		view.btnSave.setOnAction(E-> {
			model.db.showDb();
		});
		view.btnDelete.setOnAction(E-> {
			model.db.deletePerson(view.txtName.getText());
		});
		
		
	}

	
	

	
	
	
	
	
}
