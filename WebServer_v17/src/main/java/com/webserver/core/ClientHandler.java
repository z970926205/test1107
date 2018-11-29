package com.webserver.core;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.spi.ResourceBundleControlProvider;

import com.webserver.http.EmptyRequestException;
import com.webserver.http.HttpContext;
import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;
import com.webserver.servlet.HttpServlet;
import com.webserver.servlet.LoginServlet;
import com.webserver.servlet.RegServlet;

/*2
 * 该线程任务是用来处理指定客户端的请求并予以响应
 */
public class ClientHandler implements Runnable{
	private Socket socket;
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("开始处理客户端请求");
		/*
		 * 处理客户端请求的大致流程
		 * 1：解析请求并用一个对象保存请求内容
		 * 2：创建响应对象，表示给客户端回复的内容
		 * 3：分析请求内容并处理该请求，将处理结果（即将给
		 * 客户端回复的内容）设置到响应对象中
		 * 4：将相应对象中的内容以HTTP相应格式发送回给客户端
		 */
		try {
			//接收请求对象
			HttpRequest request = new HttpRequest(socket);
			//回复响应对象
			HttpResponse response = new HttpResponse(socket);
			String url = request.getRequestURI();
			System.out.println("url:"+url);
			/*
			 * 首先判断该请求是否为请求业务
			 */
			if(ServerContext.hasUrl(url)) {
				Class cls = Class.forName(
						ServerContext.getServletByUrl(url));
				HttpServlet servlet = (HttpServlet)cls.newInstance();
				servlet.service(request, response);
			}else{
				/*
				 * 得到url用户要请求的资源路径后，从webapps目录
				 * 中找到对应该相对路径的资源。
				 */
				File file = new File("webapps"+url);
				if(file.exists()) {
					System.out.println("找到该文件");
					responseResource(file, response);
				}else {
					System.out.println("没有该文件");
					toNotFoundpage(response);
				}
			}
			//响应客户端
			response.flush();
			System.out.println("回复消息");
		} catch(EmptyRequestException e) {
			System.out.println("空请求。。。");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//1.0协议中，一次请求响应完毕后与客户端断开连接
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("处理请求完毕");
	}
	/*
	 * 调转404提示页面
	 */
	private void toNotFoundpage(HttpResponse response) {
		File notFoundFile = new File("webapps/ROOT/notfound.html");
		response.setStatusCode(404);
		response.putHeader("Content-Type", "text/html");
		response.putHeader("Content-Length", notFoundFile.length()+"");
		response.setEntity(notFoundFile);
	}
	private void responseResource(File file,HttpResponse response) {
		//设置状态代码
		response.setStatusCode(200);
		System.out.println(file.getName());
		//先根据该文件的后缀名获取到对应的Content-Type的值
		//String name = file.getName();
		//name = name.substring(name.lastIndexOf(".")+1);
		//设置响应头信息
		String name = (file.getName().split("\\."))[1];
		String contentType = HttpContext.getMimeType(name);
		System.out.println("name:"+contentType);
		response.putHeader("Content-Type", contentType);
		response.putHeader("Content-Length", file.length()+"");
		//将客户端请求的实际资源文件设置到response对象中
		response.setEntity(file);
	}
}
