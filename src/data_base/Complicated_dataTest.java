package data_base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;
/**
 * Very simple example of java object strea - write & load
 * @author ben-moshe
 *
 */
class Complicated_dataTest {

	@Test
	void test() {
		Complicated_data cd = new Complicated_data();
		System.out.println(cd);
	//	fail("Not yet implemented");
	}
	public static void main(String[] s) {
		Complicated_data cd = new Complicated_data();
	//	System.out.println(cd);
		
		  try {
		      //   create a new file with an ObjectOutputStream
	     FileOutputStream out = new FileOutputStream("test.txt");
		     ObjectOutputStream oout = new ObjectOutputStream(out);

		    //      write something in the file
		         oout.writeObject(cd);
		         oout.flush();

		         // create an ObjectInputStream for the file we created before
		         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));
		         Complicated_data cd1 = (Complicated_data)ois.readObject();
		         // read and print an object and cast it as string
		         System.out.println(cd1);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		
	}
}
