package com.webserver.core;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*1\
 * WebServer主类
 */
public class WebServer {
	private ServerSocket server;
	//线程池
	private ExecutorService threadPool;
	//构造方法，用来初始化Server
	public WebServer() {
		try {
			/*
			 * 8080是Tomcat使用的默认端口号。
			 */
			System.out.println("初始化");
			server = new ServerSocket(8080);
			//线程池容量
			threadPool = Executors.newFixedThreadPool(50);
			System.out.println("完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 服务端开始工作的方法
	 */
	public void start() {
		try {
			//暂时只处理一次客户端连接
			while(true) {
				//等待客户端连接
				System.out.println("等待连接。。。");
				Socket socket = server.accept();
				System.out.println("一个客户端连接");
				//创建线程任务
				ClientHandler handler = new ClientHandler(socket);
				//任务放到线程池
				threadPool.execute(handler);
				//启动一个线程处理该客户端
//				Thread t = new Thread(handler);
//				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();
	}
}
