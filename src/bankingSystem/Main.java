package bankingSystem;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		RegistrationForm myForm = new RegistrationForm();
		
		try {
			WriteObjectToFile.write(myForm, "test.txt");
			ArrayList<String> myArrayList = ReadListFromFile.read("registrationList.txt");
			myArrayList.add(myForm.getuserName());
			WriteListToFile.write(myArrayList, "registrationList.txt");
			Object myObject = ReadObjectFromFile.read("test.txt");
			System.out.println(myObject.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
