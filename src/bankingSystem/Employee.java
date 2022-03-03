package bankingSystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends GeneralClass {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password = "";

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<String> viewExistingUser() {
		
		ArrayList<String> userList = ReadListFromFile.read("userList.txt");		
		return userList;	
	}
	
	public void viewUserInfo(String userName) {
		User targetUser = (User) ReadObjectFromFile.read(userName+"User.ser");
		
		System.out.println(targetUser.toString());
		
	}

	public static void logIntoSystem(Employee myEmployee) {
		try {
			String path = myEmployee.name+"Employee.ser";
			WriteObjectToFile.write(myEmployee, path);
			ArrayList<String> myArrayList = ReadListFromFile.read("employeeList.txt");
			myArrayList.add(myEmployee.name);
			WriteListToFile.write(myArrayList, "employeeList.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteFromSystem(Employee myEmployee) {
		try {
		//delete file
			String path = myEmployee.name+"Employee.ser";
			File targetFile = new File(path);
			targetFile.delete();
			
			//delete from list
			ArrayList<String> targetArrayList = ReadListFromFile.read("employeeList.txt");
			targetArrayList.remove(myEmployee.name);
			WriteListToFile.write(targetArrayList, "employeeList.txt");
			
			System.out.println(myEmployee.name + " deleted from system");
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void UI(Scanner input) {
		String userInputString = "";
		do {
			do {
				System.out.println(System.getProperty("line.separator"));
				System.out.println("select what you want to do");
				System.out.println("1 for view all users" +System.getProperty("line.separator")+ "2 for view an user's account information"+System.getProperty("line.separator")+ "3 for act on RegistrationForm" +System.getProperty("line.separator")+"4 for log off");
				if (input.hasNextLine()){
					userInputString = input.nextLine();	
				}
				if(!(userInputString.equals("1") || userInputString.equals("2") || userInputString.equals("3")|| userInputString.equals("4"))) {
					System.out.println("invalid input");
				}
				}while(!(userInputString.equals("1") || userInputString.equals("2") || userInputString.equals("3")|| userInputString.equals("4")));
			switch (userInputString) {
			
			case "1":
				
				System.out.println("userList: " + this.viewExistingUser());
				break;

			case "2":
				String targetName="";
				ArrayList<String> userList = this.viewExistingUser();
				System.out.println("userList: " + userList);
				do {					
					System.out.println("please enter userName of the user you want to see");
					targetName = input.nextLine();
					if (!userList.contains(targetName)) {
						System.out.println("invalid name");
					}					
				}while(!userList.contains(targetName));
				
				this.viewUserInfo(targetName);				
				break;
			case"3":
				ArrayList<String> myArrayList = ReadListFromFile.read("registrationList.txt");
				System.out.println(myArrayList);
				String targetString = "";
				do {
					System.out.println("please enter the name of registration you want to act on");
					targetString = input.nextLine();
					if(!myArrayList.contains(targetString)) {
						System.out.println("invalid input");
					}	
				} while (!myArrayList.contains(targetString));
				String path = targetString +"RegistrationForm.ser";
				RegistrationForm myForm = (RegistrationForm) ReadObjectFromFile.read(path);
				do {
					System.out.println("please enter you decision" +System.getProperty("line.separator")+ "1 for approve"+System.getProperty("line.separator")+"2 for deny");
					userInputString = input.nextLine();
					if (!(userInputString.equals("1") || userInputString.equals("2"))) {
						System.out.println("invalid input");
					}
				} while (!(userInputString.equals("1") || userInputString.equals("2")));
				
				if(userInputString.equals("1")){
					ApproveRegistration.approve(myForm);
					System.out.println("approved");
				}else {
					ApproveRegistration.deny(myForm);
					System.out.println("denied");
				}
			
			}
		} while (! userInputString.equals("4"));
			
		
	}
	
	@Override
	public String toString() {
		return ("userName: " + this.name +System.getProperty("line.separator")+"accountType: " + this.accountType);
	}


}
