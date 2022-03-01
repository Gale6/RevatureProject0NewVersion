package bankingSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User {
	public String name = "";
	private String password = "";
	
	public double amount = 0;
	public ArrayList<ArrayList<String>> history = new ArrayList<>();
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void withdraw() {
		
		Scanner input = new Scanner(System.in);
		double withdrawAmount;
		
		do {
			System.out.println("please enter withdraw amount");
			withdrawAmount = Double.parseDouble(input.nextLine());
			if (withdrawAmount > amount) {
				System.out.println("Insufficient balance, you can only withdraw what you have");
			}
			
		} while (withdrawAmount > amount);
		
		this.amount -= withdrawAmount;
		String withdrawAmountString = String.valueOf(withdrawAmount);
		
		ArrayList<String> withdrawHistory = new ArrayList<>(Arrays.asList("deposit",withdrawAmountString));
		history.add(withdrawHistory);
		input.close();
		
	}
	
	//add negative check
	public void deposit() {
		Scanner input = new Scanner(System.in);
		double depositAmount;
		
		do {
			System.out.println("please enter a valid deposit amount");
			depositAmount = Double.parseDouble(input.nextLine());
		}while(depositAmount<0);
				
		this.amount += depositAmount;
		String depositAmountString = String.valueOf(depositAmount);
		ArrayList<String> depositHistory = new ArrayList<>(Arrays.asList("deposit",depositAmountString));
		history.add(depositHistory);
		input.close();
	}
	
	public void transfer() {
		
	}
	public void receiveTransfer() {
		
	}

}
