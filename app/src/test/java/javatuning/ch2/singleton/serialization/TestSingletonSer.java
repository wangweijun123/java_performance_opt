package javatuning.ch2.singleton.serialization;

import junit.framework.Assert;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSingletonSer {

	@Test
	public void test() throws Exception {
		SerSingleton s1 = null;
		SerSingleton s = SerSingleton.getInstance();

		FileOutputStream fos = new FileOutputStream("SerSingleton.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s);
		oos.flush();
		oos.close();

		FileInputStream fis = new FileInputStream("SerSingleton.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		s1 = (SerSingleton) ois.readObject();
		
		Assert.assertEquals(s, s1);

	}

}
