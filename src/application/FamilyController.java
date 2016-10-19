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
			try{
			model.createPerson(view.txtName.getText(),view.txtFather.getText(),view.txtMother.getText(), 
			 view.tg.getSelectedToggle().getUserData().toString(), view.txtHusband.getText(), view.txtWife.getText(), (Integer.parseInt(view.txtAge.getText())) );
			view.txtArea.appendText(model.updateViewAfterAdd());
			view.txtName.clear();
			view.txtAge.clear();
			view.txtFather.clear();
			view.txtMother.clear();
			view.txtWife.clear();
			view.txtHusband.clear();
			
		}catch (Exception ee){
			view.txtArea.setText("Why are you trying to add a person without filling all the data ? "
					+ "\n are you trying to crash me ? ");
		}}
			);
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
		
		view.btnShowData.setOnAction(E-> {
			view.txtArea.clear();
			view.txtArea.appendText("Names in the Database: "+model.db.showDb());
			model.db.showDb();
		});
		view.btnDelete.setOnAction(E-> {
			model.db.deletePerson(view.txtName.getText());
		});
		view.btnShowGrandFather.setOnAction(E->{
			view.txtArea.clear();
			String user= view.txtName.getText();
			if (user!=null){
			view.txtArea.appendText("The Grandfathers of "+user+":");
			try{
			view.txtArea.appendText(Person.loopArray(model.ShowGrandpa(view.txtName.getText())));
			}catch (Exception e){
				view.txtArea.setText("loading old people failed");
			}
			}});
		view.btnShowGrandMother.setOnAction(E->{
			view.txtArea.clear();
			String user= view.txtName.getText();
			if (user!=null){
			view.txtArea.appendText("The Grandmothers of "+user+":");
			try{
			view.txtArea.appendText(Person.loopArray(model.showGrandma((view.txtName.getText()))));
			}catch (Exception e){
				view.txtArea.setText("You got no GrandMa Brah!");
			}
			}});
		view.btnShowSibilings.setOnAction(E->{
			view.txtArea.clear();
			String user= view.txtName.getText();
			if (user!=null){
				System.out.println(user);
			view.txtArea.appendText("The Siblings of "+user+":");
			try{
			view.txtArea.appendText(Person.loopArray(model.showSiblings((view.txtName.getText()))));
			}catch (Exception e){
				view.txtArea.setText("stop trying to crash me!! I WONT");
			}
			} else view.txtName.setText("please fill some data");
			});
		view.getStage().setOnCloseRequest(e->{
			model.db.saveDb();
		});
		
		view.btnShowCousins.setOnAction(E->{
			view.txtArea.clear();
			String user= view.txtName.getText();
			if (user!=null){
			view.txtArea.appendText("The Cousins of "+user+":");
			try{
			view.txtArea.appendText(Person.loopArray(model.showCousins((view.txtName.getText()))));
			
		}catch (Exception e){
			view.txtArea.setText("loading old people failed");
		}
		
	}

	
	
		});
	
	}
	
	
	
}
