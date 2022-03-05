package bankingSystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class User extends GeneralClass{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String password = "";

	public double amount = 0;
	public ArrayList<ArrayList<String>> history = new ArrayList<>();
	// withdraw history arrayList [withdraw,amount,time]
	//deposit history arraryList [deposit,amount,time]
	// transfer history arrayList [transfer, amount, target,time]
	//Receive history arrayList[receive, amount, sender,time]
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void withdraw(Scanner input) {
		
		double withdrawAmount;
		
		do {
			System.out.println("***********************************************");
			System.out.println("please enter withdraw amount");
			withdrawAmount = Double.parseDouble(input.nextLine());
			if (withdrawAmount > amount) {
				System.out.println("Insufficient balance, you can only withdraw what you have");
				System.out.println("***********************************************");
			}
			
		} while (withdrawAmount > amount);
		
		this.amount -= withdrawAmount;
		String withdrawAmountString = String.valueOf(withdrawAmount);
		
		ArrayList<String> withdrawHistory = new ArrayList<>(Arrays.asList("withdraw",withdrawAmountString,Time.timeNow()));
		history.add(withdrawHistory);
		System.out.println("withdraw done");

		
	}
	
	public void deposit(Scanner input) {
		
		double depositAmount;
		
		do {
			System.out.println("***********************************************");
			System.out.println("please enter a valid deposit amount");
			depositAmount = Double.parseDouble(input.nextLine());
		}while(depositAmount<0);
				
		this.amount += depositAmount;
		String depositAmountString = String.valueOf(depositAmount);
		ArrayList<String> depositHistory = new ArrayList<>(Arrays.asList("deposit",depositAmountString,Time.timeNow()));
		history.add(depositHistory);
		System.out.println("deposited done");

	}

	
	public void transfer(Scanner input) {
		
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
		
		ArrayList<String> transferHistory = new ArrayList<>(Arrays.asList("transfer",tranferAmountString,targetName,Time.timeNow()));
		history.add(transferHistory);
		System.out.println("transfer done");

	}
	public void receiveTransfer(double receivedAmount, String sender) {		
		
		this.amount += receivedAmount;
		String receivedAmountString = String.valueOf(receivedAmount);
		ArrayList<String> receiveHistory = new ArrayList<>(Arrays.asList("receive",receivedAmountString,sender,Time.timeNow()));
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
	public void UI(Scanner input) {

		String userInputString = "";
		do {
			do {
				System.out.println("***********************************************");
				System.out.println("select what you want to do");
				System.out.println("***********************************************");
				System.out.println("1 for withdraw" +System.getProperty("line.separator")+ "2 for deposit" +System.getProperty("line.separator")+ "3 for transfer" +System.getProperty("line.separator")+ "4 for view your account"+System.getProperty("line.separator")+"5 for log off");
				if (input.hasNextLine()){
					userInputString = input.nextLine();	
				}
				if(!(userInputString.equals("1") || userInputString.equals("2") || userInputString.equals("3") || userInputString.equals("4")|| userInputString.equals("5"))) {
					System.out.println("invalid input");
				}
				}while(!(userInputString.equals("1") || userInputString.equals("2") || userInputString.equals("3") || userInputString.equals("4")|| userInputString.equals("5")));
			
			switch (userInputString) {
			
			case "1":
				
				this.withdraw(input);
				break;

			case "2":
				
				this.deposit(input);				
				break;
				
			case "3":
				
				this.transfer(input);					
				break;
				
			case "4":
				
				System.out.println(this.toString());								
				break;
			}

			
		} while (! userInputString.equals("5"));
		try {
			WriteObjectToFile.write(this, this.name+"User.ser");
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		
	}
	@Override
	public String toString() {
		return ("userName: " + this.name +System.getProperty("line.separator")+"accountType: " + this.accountType + System.getProperty("line.separator") + "balance: " + this.amount + System.getProperty("line.separator") + "history " + history);
	}

}
