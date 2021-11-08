package javatuning.ch3.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class TestNioBuffer extends TestMapBuffer {

	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];
		targets[3] = (byte) (res & 0xff);
		targets[2] = (byte) ((res >> 8) & 0xff);
		targets[1] = (byte) ((res >> 16) & 0xff);
		targets[0] = (byte) (res >>> 24);
		return targets; 
	}
	
	public static int byte2int(byte b1,byte b2,byte b3,byte b4) {
		return ((b1& 0xff)<<24)|((b2& 0xff)<<16)|((b3& 0xff)<<8)|(b4& 0xff);
	}
	
	/**
	 * @throws IOException
	 */
	@Test
	public void testBufferWrite() throws IOException {
		long starttime = System.currentTimeMillis();
		// DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp")),16*1024*1024));  
		FileOutputStream fout = new FileOutputStream(new File("temp_buffer.tmp"));
		FileChannel fc=fout.getChannel();
		ByteBuffer byteBuffer=ByteBuffer.allocate(numOfInts*4);
        for(int i = 0; i < numOfInts; i++){  
        	byteBuffer.put(int2byte(i));
        }  
        byteBuffer.flip();
        fc.write(byteBuffer);
		long endtime = System.currentTimeMillis();
		// testBufferWrite:107ms
		System.out.println("testBufferWrite:"+(endtime-starttime)+"ms");
	}
	

	/**
	 * 通过buffer读取文件
	 * @throws IOException
	 */
	@Test
	public void testBufferRead() throws IOException {
		long starttime = System.currentTimeMillis();
		// DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp")),16*1024*1024));  
		FileInputStream fin = new FileInputStream(new File("temp_buffer.tmp"));
		FileChannel fc=fin.getChannel();
		ByteBuffer byteBuffer=ByteBuffer.allocate(numOfInts*4);
		fc.read(byteBuffer);
		fc.close();
		byteBuffer.flip();
        while(byteBuffer.hasRemaining()){  
        	byte2int(byteBuffer.get(),  byteBuffer.get(),byteBuffer.get(),byteBuffer.get());
        }  
		long endtime = System.currentTimeMillis();
        // testBufferRead:49ms
		System.out.println("testBufferRead:"+(endtime-starttime)+"ms");
	}

	@Test
	public void testInt2byte() throws IOException {
		int res = 254;
		byte[] targets = new byte[4];
		targets[3] = (byte) (res & 0xff);// 最低位
		targets[2] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[1] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[0] = (byte) (res >>> 24);// 最高位,无符号右移。

		for (int i = 0; i < targets.length; i++) {
			System.out.println(i + " =  " + targets[i]);
		}

		System.out.println("##############");
		int re = ((targets[0]& 0xff)<<24)|((targets[1]& 0xff)<<16)|((targets[2]& 0xff)<<8)|(targets[3]& 0xff);
		System.out.println("re = " + re);
	}
}
