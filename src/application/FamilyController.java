package application;

public class FamilyController {
	FamilyModel model;
	FamilyView view;
	

	public FamilyController (FamilyModel model, FamilyView view){
		this.model=model;
		this.view=view;
		
		
		view.btnAdd.setOnAction(e-> {
			model.createPerson(view.txtName.getText(),view.txtFather.getText(),view.txtMother.getText() );
			view.txtArea.appendText(model.updateView());
		});
		
		
		
	}

	
	

	
	
	
	
	
}
