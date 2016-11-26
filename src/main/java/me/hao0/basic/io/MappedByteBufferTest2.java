package me.hao0.basic.io;

import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author: haolin
 * Email:  haolin.h0@gmail.com
 */
public class MappedByteBufferTest2 {

    @Test
    public void testFileRealSize() throws IOException {
        File f = new File("mapped_bytebuffer_file_1.txt");
        RandomAccessFile file = new RandomAccessFile(f, "rw");
        MappedByteBuffer buffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 100);
        buffer.flip();
        buffer.clear();
        System.out.println(f.length());
    }

    @Test
    public void testWrite() throws IOException {
        File f = new File("mapped_bytebuffer_file.txt");
        RandomAccessFile file = new RandomAccessFile(f, "rw");
        MappedByteBuffer buffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 100);

        StringBuilder txt = new StringBuilder();
        for (int i=0; i<1024 * 5; i++){
            txt.append("x");
        }
        txt.append("\n");
        String line = txt.toString();

        long start = System.currentTimeMillis();
        for (int i=0; i<20000; i++){
            buffer.put(line.getBytes());
        }

        System.out.println("cost time: " + (System.currentTimeMillis() - start) + " ms");

        buffer.flip();
        buffer.clear();
        file.close();
    }

    @Test
    public void testAppend() throws IOException {

        int ONE_FILE_SIZE = 1024 * 10; // 10k every file

        String line = "I'm testing mmaped file.\n";

        File f = new File("mapped_bytebuffer_file_append.txt");

        if (!f.exists()){
            if(f.createNewFile()){
                System.err.println("create new file");
            } else {
                System.err.println("create new file failed");
            }
        }

        MappedByteBuffer buffer;
        RandomAccessFile file = new RandomAccessFile(f, "rw");

        if (file.length() > 0){
            System.err.println("seek file and append file");
            file.seek(file.length());
        }

        if (file.length() == ONE_FILE_SIZE){
            System.err.println("The file is full, please roll file");
            return;
        }

        long offset = file.getFilePointer();

        buffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, offset, ONE_FILE_SIZE - offset);

        for (int i=0; i<100; i++){
            if (line.getBytes().length > buffer.remaining()){
                System.err.println("the file is full, need roll file." + i);
            } else {
                buffer.put(line.getBytes());
            }
            System.err.println("buffer: current position:" + buffer.position());
        }

        buffer.clear();
        file.close();
    }

    @Test
    public void testAppend2() throws IOException {
        int ONE_FILE_SIZE = 1024 * 10; // 10k every file
        String line = "I'm testing mmaped file101xx.\n";
        int offset = 2500;
        File f = new File("mapped_bytebuffer_file_append.txt");
        RandomAccessFile file = new RandomAccessFile(f, "rw");
        MappedByteBuffer buffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, offset, ONE_FILE_SIZE - offset);
        buffer.put(line.getBytes());

        buffer.flip();
        buffer.clear();

        buffer.force();
        file.close();
    }
}
