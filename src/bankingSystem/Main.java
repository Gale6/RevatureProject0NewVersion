package bankingSystem;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> myArrayList = ReadListFromFile.read("test.txt");
		
		System.out.println(myArrayList.toString());
	}

}
