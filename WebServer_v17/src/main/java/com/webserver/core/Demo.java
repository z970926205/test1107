package com.webserver.core;

import java.io.FileOutputStream;

public class Demo {
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		builder.append("<head>");
		builder.append("<meta charset=\"UTF-8\">");
		builder.append("<title>动态页面</title>");
		builder.append("</head>");
		builder.append("<body>");
		builder.append("<center>");
		builder.append("<h1>动态表格</h1>");
		builder.append("<table border='1'>");
		String[] data = {"一","二","三","四","五","六","七","八","九","十"};
		for(int r=0;r<10;r++) {
			builder.append("<tr>");
			for(int c=0;c<10;c++) {
				builder.append("<td>");
				builder.append(data[r]+"行"+data[c]+"列");
				builder.append("</td>");
			}
			builder.append("</tr>");
		}
		builder.append("</table>");
		builder.append("</center>");
		builder.append("</body>");
		builder.append("</html>");
		try (
				FileOutputStream fos
					= new FileOutputStream("myfile.html");
				){
			fos.write(builder.toString().getBytes());
			System.out.println("完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
