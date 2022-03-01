package bankingSystem;

import java.io.Serializable;
import java.util.ArrayList;

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
		return ("userName: " + this.userName +" accountType: " + this.accountType + " userType: " + this.userType);
	}
	
	
}
