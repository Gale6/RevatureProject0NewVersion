package bankingSystem;

import java.io.File;
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
	public String userType = "";//user or employee or admin
	

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
	public void setUserName (String userInputName){
		this.userName = userInputName;
	}
	@Override
	public String toString() {
		return ("userName: " + this.userName + System.getProperty("line.separator")+ "accountType: " + this.accountType + System.getProperty("line.separator") + "userType: " + this.userType);
	}
	
	
	
	public RegistrationForm(Scanner input) {
		
		System.out.println("***********************************************");
		System.out.println("Welcome to Registraton");

		
		ArrayList<String> existingUserName = ReadListFromFile.read("userList.txt");
		ArrayList<String> existingEmployeeName = ReadListFromFile.read("employeeList.txt");
		ArrayList<String> existingRegistrationName = ReadListFromFile.read("registrationList.txt");
		ArrayList<String> existingAdminName = ReadListFromFile.read("adminList.txt");
		String inputName = "";
		
		do {
			System.out.println("Please Enter a userName");
			inputName = input.nextLine();				
			if(existingUserName.contains(inputName) || existingEmployeeName.contains(inputName) || existingRegistrationName.contains(inputName)||existingAdminName.contains(inputName)) {
				System.out.println("usename already exist, please enter a new one");
			}				
		} while (existingUserName.contains(inputName) || existingEmployeeName.contains(inputName) || existingRegistrationName.contains(inputName)||existingAdminName.contains(inputName));
		this.setUserName(inputName);
		
		System.out.println("Please Enter a password");
		this.setPassward(input.nextLine());
		String userIn="";
		do {
			System.out.println("1 for user account"+System.getProperty("line.separator")+"2 for Employee account"+System.getProperty("line.separator")+ "3 for Admin account");
			userIn = input.nextLine();
			
			if (!userIn.equals("1") && !userIn.equals("2") && !userIn.equals("3")) {
				System.out.println("invalid input");
			}
			
		}while (!userIn.equals("1") && !userIn.equals("2") && !userIn.equals("3"));
		
		switch (userIn) {
		case "1":
			this.userType = "user";
			do {
				System.out.println("1 for regular account"+System.getProperty("line.separator")+"2 for joint account");

				userIn = input.nextLine();
				if (!userIn.equals("1") &&! userIn.equals("2")) {
					System.out.println("invalid input");
				}
			}while (!userIn.equals("1") &&! userIn.equals("2"));
			if (userIn.equals("1")) {
				this.accountType = "regular";
			}else {
				this.accountType = "joint";
			}
			
			break;
		case "2":
			this.userType = "employee";
			
			break;
		case "3":
			this.userType = "admin";
			
			break;


		}
		System.out.println("Congratulation! You have submitted you registration form. Please wait for aproval!");
		

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
	public static void deleteFromSystem(RegistrationForm myForm) {
		try {
			//delete file
			String path = myForm.getuserName()+"RegistrationForm.ser";
			File targetFile = new File(path);
			targetFile.delete();
			
			//delete from list
			ArrayList<String> targetArrayList = ReadListFromFile.read("registrationList.txt");
			targetArrayList.remove(myForm.getuserName());
			WriteListToFile.write(targetArrayList, "registrationList.txt");
			
			System.out.println(myForm.getuserName() + " deleted from system");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
