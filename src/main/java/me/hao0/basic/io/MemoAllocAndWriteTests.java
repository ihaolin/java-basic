package me.hao0.basic.io;

import org.junit.Test;
import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * Author: haolin
 * Email:  haolin.h0@gmail.com
 */
public class MemoAllocAndWriteTests {

    private static int allocateCount = 10000;

    private static int allocateSize = 1024;

    private static final Unsafe unsafe = getUnsafe();

    private static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException("failed to get unsafe instance, cause");
        }
    }

    @Test
    public void testAllocateByUnsafe(){
        long start = System.currentTimeMillis();
        for (int i=0 ;i<allocateCount; i++){
            unsafe.allocateMemory(allocateSize);
        }
        System.out.println("cost time: " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    public void testAllocateByByteBuffer(){
        long start = System.currentTimeMillis();
        for (int i=0 ;i<allocateCount; i++){
            ByteBuffer.allocate(allocateSize);
        }
        System.out.println("cost time: " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    public void testHeapBufferWrite(){
        int writeCount = 100 * 100000;
        ByteBuffer buffer = ByteBuffer.allocate(writeCount);
        long start = System.currentTimeMillis();
        for (int i=0 ;i<writeCount/4; i++){
            buffer.putInt(i);
        }
        System.out.println("cost time: " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    public void testDirectBufferWrite(){
        int writeCount = 100 * 100000;
        ByteBuffer buffer = ByteBuffer.allocateDirect(writeCount);
        long start = System.currentTimeMillis();
        for (int i=0 ;i<writeCount/4; i++){
            buffer.putInt(i);
        }
        System.out.println("cost time: " + (System.currentTimeMillis() - start) + " ms");
    }

    @Test
    public void testAllocateByDirectByteBuffer(){
        long start = System.currentTimeMillis();
        for (int i=0 ;i<allocateCount; i++){
            ByteBuffer.allocateDirect(allocateSize);
        }
        System.out.println("cost time: " + (System.currentTimeMillis() - start) + " ms");
    }
}
