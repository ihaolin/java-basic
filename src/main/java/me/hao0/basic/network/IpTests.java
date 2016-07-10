package me.hao0.basic.network;

import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Author: haolin
 * Date  : 8/27/15.
 * Email : haolin.h0@gmail.com
 */
public class IpTests {

    @Test
    public void testMyIp() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());
    }

    @Test
    public void testAllInterfaces() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            System.out.println("---Name---:" + ni.getName());
            Enumeration nii = ni.getInetAddresses();
            while (nii.hasMoreElements()) {
                ip = (InetAddress) nii.nextElement();
                if (ip.getHostAddress().indexOf(":") == -1) {
                    System.out.println("本机的ip=" + ip.getHostAddress());
                }
            }
        }
    }
}
