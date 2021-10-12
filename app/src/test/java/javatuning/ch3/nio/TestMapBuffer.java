package javatuning.ch3.nio;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author Administrator
 * 
 */
public class TestMapBuffer {
	protected static int numOfInts = 4*1000*1000;


	
	@Test
	public void testStreamWrite() throws IOException {
		long starttime = System.currentTimeMillis();
//		 DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp")),16*1024*1024));
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp_stream.tmp"))));
         for(int i = 0; i < numOfInts; i++){  
             dos.writeInt(i);  
         }  
         if(dos != null){  
             dos.close();  
         }  
		long endtime = System.currentTimeMillis();
         // testStreamWrite:290ms
		System.out.println("testStreamWrite:"+(endtime-starttime)+"ms");
	}
	
	@Test
	public void testStreamRead() throws IOException {
		long starttime = System.currentTimeMillis();
		//DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("temp.tmp")),16*1024*1024));  
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File("temp_stream.tmp"))));
        for(int i = 0; i < numOfInts; i++){  
            dis.readInt();  
        }  
        if(dis != null){  
            dis.close();  
        }  
		long endtime = System.currentTimeMillis();
        // testStreamRead:403ms
		System.out.println("testStreamRead:"+(endtime-starttime)+"ms");
	}
	 
	
	@Test
	public void testMappedWrite() throws IOException {
		long starttime = System.currentTimeMillis();
		FileChannel fc = new RandomAccessFile("temp_mapped.tmp", "rw")
				.getChannel();
		IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, numOfInts*4)
				.asIntBuffer();
		for (int i = 0; i < numOfInts; i++) {
			ib.put(i);
		}
		if (fc != null) {
			fc.close();
		}
		long endtime = System.currentTimeMillis();
		// testMappedWrite:133ms
		System.out.println("testMappedWrite:"+(endtime-starttime)+"ms");
	}
	
	@Test
	public void testMappedRead() throws IOException {
		long starttime = System.currentTimeMillis();
		FileChannel fc = new FileInputStream("temp_mapped.tmp").getChannel();

        IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size())
				.asIntBuffer();
        while(ib.hasRemaining()){  
            ib.get();  
        }  
        if(fc != null){  
            fc.close();  
        }  
		long endtime = System.currentTimeMillis();
        // testMappedRead:33ms
		System.out.println("testMappedRead:"+(endtime-starttime)+"ms");
	}


	@Test
	public void testMappedWriteChar() throws IOException {
		int charCount = 10;
		long starttime = System.currentTimeMillis();
		RandomAccessFile randomAccessFile = new RandomAccessFile
				("temp_mapped_char.txt", "rw");
		FileChannel fc = randomAccessFile.getChannel();
		long size = fc.size();
		System.out.println("写文件前的长度 size="+size);
		CharBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, size, charCount)
				.asCharBuffer();
		for (int i = 0; i < charCount/2; i++) {
			System.out.println( i+" 次put b");
			ib.put('b');
		}
		System.out.println("写文件后的长度 length="+randomAccessFile.length());
		if (fc != null) {
			fc.close();
		}
		long endtime = System.currentTimeMillis();
		System.out.println("testMappedWriteChar:"+(endtime-starttime)+"ms");
	}

	@Test
	public void testMappedReadChar() throws IOException {
		long starttime = System.currentTimeMillis();
		FileChannel fc = new FileInputStream("temp_mapped_char.txt").getChannel();
		long size = fc.size();
		System.out.println("read size:"+size);
		CharBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, size).asCharBuffer();
		while(ib.hasRemaining()){
			System.out.println("读取" + ib.get());
		}
		if(fc != null){
			fc.close();
		}
		long endtime = System.currentTimeMillis();
		// testMappedRead:33ms
		System.out.println("testMappedReadChar:"+(endtime-starttime)+"ms");
	}


	/**
	 * 1 向文件一分钟的数据，当然肯定是断断续续的写哈
	 * 2 肯定是未知大小
	 * 3 fc map 的时候需要指定大小，有多少数据来，就映射多少数据,从
	 *
	 * @throws IOException
	 */
	@Test
	public void testMappedWriteStr() throws Exception {
//		String fileName = "temp_mapped_char.txt";
		String fileName = "d:\\temp_buffer.tmp";

		int charCount = 10;
		long starttime = System.currentTimeMillis();
		RandomAccessFile randomAccessFile = new RandomAccessFile
				(fileName, "rw");
		FileChannel fc = randomAccessFile.getChannel();
		int writeCount = 5;
		int currentWriteCount = 0;
		while (currentWriteCount < writeCount) {
			long size = fc.size();
			System.out.println(currentWriteCount + "次-> 写文件前的长度 size="+size);
			CharBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, size, charCount)
					.asCharBuffer();
			for (int i = 0; i < charCount/2; i++) {
				System.out.println( i+" 次put b");
				ib.put('b');
			}
			System.out.println("写文件后的长度 length="+randomAccessFile.length());
			currentWriteCount++;
		}

		if (fc != null) {
			fc.close();
		}
		if (randomAccessFile != null) {
			randomAccessFile.close();
		}
		long endtime = System.currentTimeMillis();
		System.out.println("testMappedWriteChar:"+(endtime-starttime)+"ms");

		Thread.sleep(1000);


		/*Path source = FileSystems.getDefault().getPath(fileName);
		*//*Path target = source.resolveSibling("duanxia.txt");*//*
		System.out.println(source.getFileName());

		// java.nio.file.FileSystemException: d:\temp_buffer.tmp -> d:\temp_buffer_copy.tmp:
		// 另一个程序正在使用此文件，进程无法访问。
		Files.move(source, source.resolveSibling("temp_buffer_copy.tmp"));*/
	}

	@Test
	public void renameFile() throws IOException {
//		String fileName = "temp_mapped_char.txt";
		String fileName = "d:\\temp_buffer.tmp";
		Path source = FileSystems.getDefault().getPath(fileName);
		// 注意目标文件不要加 目录哈
		Files.move(source, source.resolveSibling("temp_buffer_copy.tmp"));
	}

	@Test
	public void moveFile2NewDir() throws IOException {
//		String fileName = "temp_mapped_char.txt";
		String fileName = "d:\\temp_buffer.tmp";
		String newdirPath = "d:\\testdir";
		Path source = FileSystems.getDefault().getPath(fileName);
		// 注意目标文件不要加 目录哈
		Path newdir = FileSystems.getDefault().getPath(newdirPath);
		Files.move(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
	}
}
