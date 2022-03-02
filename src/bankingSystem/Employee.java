package bankingSystem;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name = "";
	private String password = "";
	public String accountType = "";
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void viewExistingUser() {
		
		ArrayList<String> userList = ReadListFromFile.read("userList.txt");		
		System.out.println("userList: " + userList);
		
	}
	
	public void viewUserInfo(String userName) {
		User targetUser = (User) ReadObjectFromFile.read(userName+"User.ser");
		
		System.out.println(targetUser.toString());
		
	}

	public static void logIntoSystem(Employee myeEmployee) {
		try {
			String path = myeEmployee.name+"Employee.ser";
			WriteObjectToFile.write(myeEmployee, path);
			ArrayList<String> myArrayList = ReadListFromFile.read("employeeList.txt");
			myArrayList.add(myeEmployee.name);
			WriteListToFile.write(myArrayList, "employeeList.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteFromSystem(Employee myeEmployee) {
		try {
		//delete file
			String path = myeEmployee.name+"Employee.ser";
			File targetFile = new File(path);
			targetFile.delete();
			
			//delete from list
			ArrayList<String> targetArrayList = ReadListFromFile.read("employeeList.txt");
			targetArrayList.remove(myeEmployee.name);
			WriteListToFile.write(targetArrayList, "employeeList.txt");
			
			System.out.println(myeEmployee.name + " deleted from system");
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public String toString() {
		return ("userName: " + this.name +System.getProperty("line.separator")+"accountType: " + this.accountType);
	}


}
