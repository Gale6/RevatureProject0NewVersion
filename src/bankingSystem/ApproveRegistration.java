package bankingSystem;

public class ApproveRegistration {
	
	public static void approve(RegistrationForm myForm) {
		

		switch (myForm.userType) {
		case "user":
			User myUser = new User();
			myUser.name = myForm.getuserName();
			myUser.setPassword(myForm.getPassward());
			myUser.accountType = "user";
			
			User.logIntoSystem(myUser);
			
			break;
		case "employee":
			Employee myEmployee = new Employee();
			myEmployee.name = myForm.getuserName();
			myEmployee.setPassword(myForm.getPassward());
			myEmployee.accountType = "employee";
			
			
			Employee.logIntoSystem(myEmployee);
			
			break;
		case "admin":
			Admin myAdmin = new Admin();
			myAdmin.name = myForm.getuserName();
			myAdmin.setPassword(myForm.getPassward());
			myAdmin.accountType = "admin";
			
			Admin.logIntoSystem(myAdmin);
			break;

		}
		
		
		RegistrationForm.deleteFromSystem(myForm);
	}
	
	public static void deny(RegistrationForm myForm) {
		
		RegistrationForm.deleteFromSystem(myForm);
		
	}
	
	
	
	
	

}
