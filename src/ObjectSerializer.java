import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ObjectSerializer {

	static void save(Object object, String fileName) throws FileNotFoundException, IOException {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(object);
		objectOut.close();
		fileOut.close();
		System.out.printf("Serialized data saved as " + fileName + "\n");
	}
	
	static Object open(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		Object object = objectIn.readObject();
		objectIn.close();
		fileIn.close();
		
		return object;
	}
}
