package bankingSystem;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Welcome to myBank");		
		System.out.println("Please select what you want to do");
		
		Scanner input = new Scanner(System.in);		
		String userIn;
		do {
			System.out.println("1 for Registration" + System.getProperty("line.separator") + "2 for login");
			userIn = input.nextLine();
		} while (!(userIn.equals("1")||userIn.equals("2")));
		
		if (userIn.equals("1")) {
			RegistrationForm myForm = new RegistrationForm();
			RegistrationForm.logIntoSystem(myForm);
		}else {
			System.out.println("login");
			System.out.println("please select userType");
			
			do {
				System.out.println("1 as user" + System.getProperty("line.separator") + "2 as employee" + System.getProperty("line.separator") + "3 as admin");
				userIn = input.nextLine();
			} while (!(userIn.equals("1")||userIn.equals("2")||userIn.equals("3")));
			
			String listPath = "";
			String fileExtension;
			switch (userIn) {
			case "1":
				listPath = "userList.txt";
				fileExtension = "User.ser";
				break;

			case "2":
				listPath = "employeeList.txt";
				fileExtension = "Employee.ser";				
				break;
				
			case "3":
				listPath = "adminList.txt";
				fileExtension = "Admin.ser";				
				break;
			}
			
			String inputName;
			ArrayList<String> listOfNames = ReadListFromFile.read(listPath);
			do {
				System.out.println("enter valid username");
				inputName = input.nextLine();
			} while (! listOfNames.contains(inputName));
			
			//TODO bring up object and check it against password entered

			
			
			
			
			
			
			
		}
		
		
		
		
	}

}
