package com.example.wangweijun.ch4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void testMappedWriteStr() throws Exception {
        String fileName = getCacheDir().getAbsolutePath()+ File.separator +  "temp_buffer.tmp";
        System.out.println("fileName :"+fileName);
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
    }

    public void renameFile() throws IOException {
        String fileName = getCacheDir().getAbsolutePath()+ File.separator +  "temp_buffer.tmp";
        System.out.println("fileName :"+fileName);

//		String fileName = "temp_mapped_char.txt";
        Path source = FileSystems.getDefault().getPath(fileName);
        // 注意目标文件不要加 目录哈
        Files.move(source, source.resolveSibling("temp_buffer_copy.tmp"), StandardCopyOption.REPLACE_EXISTING);
    }

    public void sendMsg(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testMappedWriteStr();
                    renameFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
