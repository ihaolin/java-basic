package me.hao0.basic.concurrent.syncutils;

public class GrumyBoundedBufferTests {
	public static void main(String[] args) {
		GrumyBoundedBuffer<String> buffer = new GrumyBoundedBuffer<>(10);
		
		while (true){
			try {
				String item = buffer.take();
			} catch (BufferEmptyExeption e) {
				e.printStackTrace();
			}
		}
	}
}
