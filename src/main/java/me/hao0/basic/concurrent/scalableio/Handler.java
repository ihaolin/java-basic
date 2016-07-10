package me.hao0.basic.concurrent.scalableio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Author: haolin
 * On: 10/31/14
 */
public class Handler implements Runnable{

    final SocketChannel socket;
    final SelectionKey sk;

    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    static final int READING = 0, SENDING = 1;
    int state = READING;

    public Handler(Selector selector, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
        sk = socket.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (state == READING)
                read();
            if (state == SENDING)
                send();
        } catch (IOException e){
            // handle ex
        }
    }

    private void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()){
            process();
            state = SENDING;
            // Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    private void process() {

    }

    private boolean inputIsComplete() {
        return false;
    }

    private void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()){
            sk.cancel();
        }
    }

    private boolean outputIsComplete() {
        return false;
    }
}
