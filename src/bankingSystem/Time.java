package bankingSystem;

import java.text.SimpleDateFormat;  
import java.util.Date;  
public class Time {  
	
	public static String timeNow() {
		
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();  

	return formatter.format(date);  
}  
}  

