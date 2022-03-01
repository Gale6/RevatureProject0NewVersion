package bankingSystem;



public class Main {

	public static void main(String[] args) {
		
		Object myObject = ReadObjectFromFile.read("jjRegistrationForm.ser");
		System.out.println(myObject.toString());
		
	}

}
