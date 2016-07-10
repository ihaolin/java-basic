package me.hao0.basic.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Author: haolin
 * Date  : 8/5/15.
 * Email : haolin.h0@gmail.com
 */
public class SocketChannelTest {

    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        Boolean conntected = channel.connect(new InetSocketAddress(9999));
        System.out.println("connect " + conntected);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        buf.put("You are my sunshine.".getBytes());

        buf.flip();

        channel.write(buf);

        buf.clear();

        channel.close();
    }
}
