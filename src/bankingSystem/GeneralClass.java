package bankingSystem;

import java.io.Serializable;
import java.util.Scanner;



public abstract class GeneralClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String  name = "";

	public String accountType = "";

	public abstract void UI(Scanner input);
	

}
