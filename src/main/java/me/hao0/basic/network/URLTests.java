package me.hao0.basic.network;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Author: haolin
 * Date  : 8/6/15.
 * Email : haolin.h0@gmail.com
 */
public class URLTests {

    @Test
    public void testUrlConn() throws IOException {
        URL url = new URL("http://www.baidu.com");
        URLConnection urlConnection = url.openConnection();
        InputStream input = urlConnection.getInputStream();
        int data = input.read();
        while(data != -1){
            System.out.print((char) data);
            data = input.read();
        }
        input.close();
    }

}
