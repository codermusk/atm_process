package src.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Generator {
	public static boolean save(Object payload,String file) {
		
		try {
		    FileOutputStream fos = new FileOutputStream(file);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);

		    oos.writeObject(payload);
		    oos.close();
		    
		    return true;

		} catch (IOException ex) {
		    ex.printStackTrace();
		    return false;
		}
	}
}
