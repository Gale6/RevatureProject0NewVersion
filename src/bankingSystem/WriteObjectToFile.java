package bankingSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjectToFile {
	
	// takes in a object and create a file at path if it doesn't exist, then just write the list to it
	
	public static void write(Object myObject, String path) throws IOException {
		File myFile = new File(path);
		myFile.createNewFile();
		
		FileOutputStream fos = new FileOutputStream(myFile);
		ObjectOutputStream oops = new ObjectOutputStream(fos);
			
		oops.writeObject(myObject);
		
		oops.close();
		fos.close();

	}

}
