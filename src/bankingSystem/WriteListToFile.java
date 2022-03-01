package bankingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteListToFile {
	
	// gets in a arrayList and create a file at path if it doesn't exist, else just write to it
	public static void write(ArrayList<String> listToWrite, String path) throws IOException {
		
		File myFile = new File(path);
		myFile.createNewFile();
		
		FileWriter writer = new FileWriter(path); 
		for(String str: listToWrite) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
	}

}



