package bankingSystem;

public class ApproveRegistration {
	
	public static void approve(RegistrationForm myForm) {
		if(myForm.userType.equals("user")) {
			User myUser = new User();
			myUser.name = myForm.getuserName();
			myUser.setPassword(myForm.getPassward());
			myUser.accountType = "user";
			
			User.logIntoSystem(myUser);
				
		}else {
			
			Employee myEmployee = new Employee();
			myEmployee.name = myForm.getuserName();
			myEmployee.setPassword(myForm.getPassward());
			myEmployee.accountType = "employee";
			
			
			Employee.logIntoSystem(myEmployee);
			
		}
		RegistrationForm.deleteFromSystem(myForm);
	}
	
	public static void deny(RegistrationForm myForm) {
		
		RegistrationForm.deleteFromSystem(myForm);
		
	}
	
	
	
	
	

}
