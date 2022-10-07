package src.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Loader {
	public static Object load(String file) {
		
		Object data = null;
		
		try {
		    FileInputStream fis = new FileInputStream(file);
		    ObjectInputStream ois = new ObjectInputStream(fis);

		    data = (Object)ois.readObject();
		    
		    ois.close();
		} catch (IOException|ClassNotFoundException ex) {
		    ex.printStackTrace();
		}
		
		return data;
	}
}
