package bankingSystem;

import java.util.Scanner;

public class Test {
	
	public String id = "";
	public String j = "";
	
	public Test() {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("id");
		this.id = inputScanner.nextLine();
		System.out.println("j");
		this.j = inputScanner.nextLine();
		inputScanner.close();
	}

}
