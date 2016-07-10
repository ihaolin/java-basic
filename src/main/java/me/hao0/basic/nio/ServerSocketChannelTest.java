package me.hao0.basic.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Author: haolin
 * Date  : 8/5/15.
 * Email : haolin.h0@gmail.com
 */
public class ServerSocketChannelTest {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null){
                socketChannel.read(buf);
                buf.flip();

                System.out.println("read from client: " + buf.asCharBuffer().toString());

                buf.clear();
            }
        }
    }
}
