package me.hao0.basic.concurrent.scalableio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: haolin
 * On: 10/31/14
 */
public class Refactor implements Runnable{

    final Selector selector;
    final ServerSocketChannel serverSocket;

    public Refactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        // Dispatch Loop
        try {
            while (!Thread.interrupted()){
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()){
                    dispatch(it.next());
                }
            }
        } catch (IOException e){
            // handle ex
        }
    }

    private void dispatch(SelectionKey k) {
        Runnable r = (Runnable)k.attachment();
        if (r != null){
            r.run();
        }
    }

    private class Acceptor implements Runnable{

        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null){
                    new Handler(selector, c);
                }
            } catch (IOException e) {
                // handle ex
            }
        }
    }
}
