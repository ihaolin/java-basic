package me.hao0.basic.other;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class LocalHost {

	public static void main(String[] args) {
		if (args.length > 0){
			try {
				InetAddress[] addresses = InetAddress.getAllByName("localhost");
				for (InetAddress address: addresses){
					System.out.println(address);
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		else{
			InetAddress address;
			try {
				address = InetAddress.getLocalHost();
				System.out.println(address);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}
}
