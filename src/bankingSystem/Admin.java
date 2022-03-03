package bankingSystem;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends GeneralClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password = "";

	public ArrayList<String> viewExistingUser() {
		
		ArrayList<String> userList = ReadListFromFile.read("userList.txt");		
		return userList;
		
	}
	
	//
	public ArrayList<String>  viewExistingEmployee() {
		
		ArrayList<String> employeeList = ReadListFromFile.read("employeeList.txt");		
		return employeeList;
		
	}
	
	//
	public ArrayList<String>  viewExistingAdmin() {
		
		ArrayList<String> adminList = ReadListFromFile.read("adminList.txt");		
		return adminList;
		
	}
	
	
	public static void logIntoSystem(Admin myAdmin) {
		
		try {
			String path = myAdmin.name + "Admin.ser";
			WriteObjectToFile.write(myAdmin, path);
			ArrayList<String> myArrayList = ReadListFromFile.read("adminList.txt");
			myArrayList.add(myAdmin.name);
			WriteListToFile.write(myArrayList, "adminList.txt");
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void deleteFromSystem(Admin myAdmin) {
		try {
		//delete file
			String path = myAdmin.name + "Admin.ser";
			File targetFile = new File(path);
			targetFile.delete();
			
			//delete from list
			ArrayList<String> targetArrayList = ReadListFromFile.read("adminList.txt");
			targetArrayList.remove(myAdmin.name);
			WriteListToFile.write(targetArrayList, "adminList.txt");
			
			System.out.println(myAdmin.name + " deleted from system");
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public Object accessObject(String name, String accountExtension) {
		
		String path = name + accountExtension;		
		Object resultObject = ReadObjectFromFile.read(path);
			
		return resultObject;		
	}
	
	
	public void UI(Scanner input) {
		String userInputString = "";
		do {
			do {
				System.out.println(System.getProperty("line.separator"));
				System.out.println("select what you want to do");
				System.out.println("1 for act on RegistrationForm" +System.getProperty("line.separator")+ "2 for act on accounts"+System.getProperty("line.separator")+ "3 for log off");
				if (input.hasNextLine()){
					userInputString = input.nextLine();	
				}
				if(!(userInputString.equals("1") || userInputString.equals("2") || userInputString.equals("3"))) {
					System.out.println("invalid input");
				}
				}while(!(userInputString.equals("1") || userInputString.equals("2") || userInputString.equals("3")));

		switch (userInputString) {
		case "1":
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
			break;
			
		case "2":
			String actOnAccountIn = "";
			do {				
				System.out.println("select what type of account you want to act on"  +System.getProperty("line.separator")+ "1.user" +System.getProperty("line.separator")+"2.employee"+System.getProperty("line.separator")+"3.admin");
				actOnAccountIn = input.nextLine();
				if (!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3"))) {
					System.out.println("invalid input");
				}
			}while(!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3")));
			
			switch (actOnAccountIn) {
			case "1":
				ArrayList<String> userList = viewExistingUser();
				System.out.println("user list:" + userList);
				
				do {					
					System.out.println("please enter userName of the user you want to see");
					actOnAccountIn = input.nextLine();
					if (!userList.contains(actOnAccountIn)) {
						System.out.println("invalid name");
					}					
				}while(!userList.contains(actOnAccountIn));
				
				User myUser = (User) ReadObjectFromFile.read(actOnAccountIn+"User.ser");
				do {
					do {
						System.out.println("you are now in the account");
						System.out.println("select want you want to do" +System.getProperty("line.separator")+ "1 for view info" +System.getProperty("line.separator")+ "2 for use the account" +System.getProperty("line.separator")+"3 for delete the account" +System.getProperty("line.separator")+"4 for exit this account");
						actOnAccountIn = input.nextLine();
						if (!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3")|| actOnAccountIn.equals("4"))) {
							System.out.println("invalid input");
						}
					} while (!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3") || actOnAccountIn.equals("4")));
					switch (actOnAccountIn) {
					case "1":
						System.out.println(myUser.toString());
								
						
						break;
					case "2":
						myUser.UI(input);
						
						break;
					case "3":
						User.deleteFromSystem(myUser);
						
						break;
					}
				} while (!(actOnAccountIn.equals("4")));

				break;
			case "2":
				ArrayList<String> employeeList = viewExistingEmployee();
				System.out.println("employee list:" +employeeList);
				
				do {					
					System.out.println("please enter userName of the employee you want to see");
					actOnAccountIn = input.nextLine();
					if (!employeeList.contains(actOnAccountIn)) {
						System.out.println("invalid name");
					}					
				}while(!employeeList.contains(actOnAccountIn));
				
				Employee myEmployee = (Employee) ReadObjectFromFile.read(actOnAccountIn+"Employee.ser");
				
				do {
					do {
						System.out.println("you are now in the account");
						System.out.println("select want you want to do" +System.getProperty("line.separator")+ "1 for view info" +System.getProperty("line.separator")+ "2 for use the account" +System.getProperty("line.separator")+"3 for delete the account" +System.getProperty("line.separator")+"4 for exit this account");
						actOnAccountIn = input.nextLine();
						if (!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3")|| actOnAccountIn.equals("4"))) {
							System.out.println("invalid input");
						}
					} while (!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3") || actOnAccountIn.equals("4")));
					
					switch (actOnAccountIn) {
					case "1":
						System.out.println(myEmployee.toString());
							
						
						break;
					case "2":
						myEmployee.UI(input);
						
						break;
					case "3":
						Employee.deleteFromSystem(myEmployee);
						
						break;
					}
				} while (!(actOnAccountIn.equals("4")));
				
				
				break;
			case "3":
				ArrayList<String> adminList = viewExistingAdmin();
				System.out.println("admin list:" +adminList);
				
				do {					
					System.out.println("please enter userName of the admin you want to see");
					actOnAccountIn = input.nextLine();
					if (!adminList.contains(actOnAccountIn)) {
						System.out.println("invalid name");
					}					
				}while(!adminList.contains(actOnAccountIn));
				
				Admin myAdmin = (Admin) ReadObjectFromFile.read(actOnAccountIn+"Admin.ser");
				
				do {
					do {
						System.out.println("you are now in the account");
						System.out.println("select want you want to do" +System.getProperty("line.separator")+ "1 for view info" +System.getProperty("line.separator")+ "2 for use the account" +System.getProperty("line.separator")+"3 for delete the account" +System.getProperty("line.separator")+"4 for exit this account");
						actOnAccountIn = input.nextLine();
						if (!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3")|| actOnAccountIn.equals("4"))) {
							System.out.println("invalid input");
						}
					} while (!(actOnAccountIn.equals("1") || actOnAccountIn.equals("2") || actOnAccountIn.equals("3") || actOnAccountIn.equals("4")));
					
					switch (actOnAccountIn) {
					case "1":
						System.out.println(myAdmin.toString());

						break;
					case "2":
						myAdmin.UI(input);
						
						break;
					case "3":
						Admin.deleteFromSystem(myAdmin);
						
						break;
					}
				} while (!(actOnAccountIn.equals("4")));
				
				break;

			}
			
			
			break;

			}
		}while(!userInputString.equals("3"));
	
	}
	
	@Override
	public String toString() {
		return ("userName: " + this.name +System.getProperty("line.separator")+"accountType: " + this.accountType);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
