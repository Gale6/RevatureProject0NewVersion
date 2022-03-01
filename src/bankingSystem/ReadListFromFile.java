package bankingSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadListFromFile {
	
	//read in arrayList from a file, if file does not exist, it returns a empty arrayList
	
	public static ArrayList<String> read(String path) {
		
		ArrayList<String> myArrayList = new ArrayList<>();
		
		File myFile = new File(path);
		
		if (!myFile.exists()) {
			return myArrayList;
		}else {
			
			try {
				Scanner scan = new Scanner(myFile);
				while(scan.hasNext()) {
					myArrayList.add(scan.nextLine());
				}
				scan.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
				
			}
			
		}
		return myArrayList;

	}

}
