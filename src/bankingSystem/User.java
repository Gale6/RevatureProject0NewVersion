package bankingSystem;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name = "";
	private String password = "";
	
	public String accountType = "";
	
	public double amount = 0;
	public ArrayList<ArrayList<String>> history = new ArrayList<>();
	// withdraw history arrayList [withdraw,amount]
	//deposit history arraryList [deposit,amount]
	// transfer history arrayList [transfer, amount, target]
	//Receive history arrayList[receive, amount, sender]
	
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
		
		ArrayList<String> withdrawHistory = new ArrayList<>(Arrays.asList("withdraw",withdrawAmountString));
		history.add(withdrawHistory);
		System.out.println("withdraw done");
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
		System.out.println("deposited done");
		input.close();
	}

	
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
		System.out.println("transfer done");
		input.close();
	}
	public void receiveTransfer(double receivedAmount, String sender) {		
		
		this.amount += receivedAmount;
		String receivedAmountString = String.valueOf(receivedAmount);
		ArrayList<String> receiveHistory = new ArrayList<>(Arrays.asList("receive",receivedAmountString,sender));
		history.add(receiveHistory);
	}

	
	public static void logIntoSystem(User myUser) {
		try {
			String path = myUser.name+"User.ser";
			WriteObjectToFile.write(myUser, path);
			ArrayList<String> myArrayList = ReadListFromFile.read("userList.txt");
			myArrayList.add(myUser.name);
			WriteListToFile.write(myArrayList, "userList.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void deleteFromSystem(User myUser) {
		try {
		//delete file
			String path = myUser.name+"User.ser";
			File targetFile = new File(path);
			targetFile.delete();
			
			//delete from list
			ArrayList<String> targetArrayList = ReadListFromFile.read("userList.txt");
			targetArrayList.remove(myUser.name);
			WriteListToFile.write(targetArrayList, "userList.txt");
			
			System.out.println(myUser.name + " deleted from system");
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public String toString() {
		return ("userName: " + this.name +System.getProperty("line.separator")+"accountType: " + this.accountType + System.getProperty("line.separator") + "balance: " + this.amount + System.getProperty("line.separator") + "history " + history);
	}

}
