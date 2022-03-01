package bankingSystem;

import java.io.IOException;
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
	//TODO bring up target object
	public void transfer() {
		Scanner input = new Scanner(System.in);
		double transferAmount;
		
		do {
			System.out.println("please enter transfer amount");
			transferAmount = Double.parseDouble(input.nextLine());
			if (transferAmount > amount) {
				System.out.println("Insufficient balance, you can only transfer what you have");
			}
		} while (transferAmount > amount);
		
		ArrayList<String> existingUserName = ReadListFromFile.read("userList.txt");
		String targetName;
		do {
			System.out.println("please enter the user to want to transfer to");
			targetName = input.nextLine();
			if (!existingUserName.contains(targetName)) {
				System.out.println(targetName + " is not our bank user");
			}
		} while (!existingUserName.contains(targetName));
		
		User targetObject = (User) ReadObjectFromFile.read(targetName+"User.ser");
		targetObject.receiveTransfer(transferAmount, name);
		try {
			WriteObjectToFile.write(targetObject, targetName+"User.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.amount -= transferAmount;
		String tranferAmountString = String.valueOf(transferAmount);
		
		ArrayList<String> transferHistory = new ArrayList<>(Arrays.asList("transfer",tranferAmountString,targetName));
		history.add(transferHistory);
		input.close();
	}
	public void receiveTransfer(double amount, String sender) {
		
	}

}
