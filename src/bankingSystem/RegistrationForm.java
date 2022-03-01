package bankingSystem;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName = "";	
	private String passward = "";
	public String accountType = "";//regular or joint
	public String userType = "";//user or employee
	

	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
	
	public String getuserName() {
		return userName;
	}
	
	// uses ReadExistingUser class to check if userName entered is in system or not
	public void setUserName (String userInputName) throws UserNameAlreadyExistException{
		
		ArrayList<String> existingUserName = ReadListFromFile.read("userList.txt");
		ArrayList<String> existingEmployeeName = ReadListFromFile.read("employeeList.txt");
		ArrayList<String> existingRegistrationName = ReadListFromFile.read("registrationList.txt");
		
		if (existingUserName.contains(userInputName) || existingEmployeeName.contains(userInputName) || existingRegistrationName.contains(userInputName)) { 
			throw new UserNameAlreadyExistException("userName already exist!");
		}else {		
		this.userName = userInputName;
		}	
	}
	@Override
	public String toString() {
		return ("userName: " + this.userName + System.getProperty("line.separator")+ "accountType: " + this.accountType + System.getProperty("line.separator") + "userType: " + this.userType);
	}
	
	
	
	public RegistrationForm() {
		
		try {
			System.out.println("Welcome to Registraton");
			System.out.println("Please Enter a userName");
			Scanner input = new Scanner(System.in);
			this.setUserName(input.nextLine());
			System.out.println("Please Enter a password");
			this.setPassward(input.nextLine());
			String userIn;
			do {
				System.out.println("Enter 1 for user account, 2 for Employee account");
				userIn = input.nextLine();
				
			}while (!userIn.equals("1") &&! userIn.equals("2"));
			if (userIn.equals("1")) {
				this.userType = "user";
				do {
					System.out.println("Enter 1 for regular account, 2 for joint account");
					userIn = input.nextLine();
				}while (!userIn.equals("1") &&! userIn.equals("2"));
				if (userIn.equals("1")) {
					this.accountType = "regular";
				}else {
					this.accountType = "joint";
				}
				
		
			}else {
				this.userType = "employee";
			}
			System.out.println("Congratulation! You have submitted you registration form. Please wait for aproval!");
			input.close();
			
			
			
		} catch (UserNameAlreadyExistException e) {
			e.getMessage();
		}
	}
	public static void logIntoSystem(RegistrationForm myForm) {
		try {
			String path = myForm.getuserName()+"RegistrationForm.ser";
			WriteObjectToFile.write(myForm, path);
			ArrayList<String> myArrayList = ReadListFromFile.read("registrationList.txt");
			myArrayList.add(myForm.getuserName());
			WriteListToFile.write(myArrayList, "registrationList.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
