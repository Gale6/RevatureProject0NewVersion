package bankingSystem;


import java.util.Scanner;

public class Admin{
	public String  name = "";
	public String  Password = "";
	public String accountType = "admin";
	
	public Object accessObject(String name, String accountExtension) {
		
		String path = name + accountExtension;		
		Object resultObject = ReadObjectFromFile.read(path);
			
		return resultObject;		
	}
	
	
	public void UI() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Admin login success!");
			String in;

			do {
			System.out.println("Please select what type of account you want to act on " + System.getProperty("line.separator") + "1 for RegistrationForm" + System.getProperty("line.separator") + "2 for User" + System.getProperty("line.separator") + "3 for Employee");
			in = input.nextLine();
			
			}while(!(in.equals("1") || in.equals("2") || in.equals("3")));
			
			
			switch (in) {
			case "1":
				System.out.println(ReadListFromFile.read("registrationList.txt"));
				System.out.println("Please enter which user you want to ");
				
				break;
				
			case "2":
				
				break;
				
			case "3":
				break;

			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	
	}
	

}
