package me.hao0.basic.nio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		System.out.println("ServerSocket Begin........");
		int num = 0;
		try {
				serverSocket = new ServerSocket(Constant.PORT);
				//使用循环方式一直等待客户端的连接
				while(true){
					num ++;
					Socket accept = serverSocket.accept();
					//启动一个新的线程，接管与当前客户端的交互会话
					new Thread(new ServerThread(accept),"Client "+num).start();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				serverSocket.close();
				System.out.println("---->  serverSocket closed.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * @author JCC
 * 服务器处理客户端会话的线程
 */
class ServerThread implements Runnable {
	Socket socket = null;
	public ServerThread(Socket socket){
		System.out.println("Create a new ServerThread...");
		this.socket = socket;
	}

	@Override
	public void run() {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = socket.getInputStream();
			out  = socket.getOutputStream();
			//使用循环的方式，不停的与客户端交互会话
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//处理客户端发来的数据
				doRead(in);
				System.out.println("send Message to client.");
				//发送数据回客户端
				doWrite(out);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 读取数据
	 * @param in
	 * @return
	 */
	public boolean doRead(InputStream in){
		//引用关系，不要在此处关闭流
		try {
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			System.out.println("line:"+new String(bytes).trim());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * 写入数据
	 * @param out
	 * @return
	 */
	public boolean doWrite(OutputStream out){
		//引用关系，不要在此处关闭流
		try {
			//out.write("welcome you client.".getBytes());
			Runtime runtime = Runtime.getRuntime();
			String cmd = "ping www.baidu.com";
			Process prop = runtime.exec(cmd);
			BufferedReader reader = new BufferedReader(
						new InputStreamReader(prop.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null){
				out.write(line.getBytes());
			}
			out.flush();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}