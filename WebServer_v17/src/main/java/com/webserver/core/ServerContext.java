package com.webserver.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * 服务端相关信息
 */
public class ServerContext {
	/*
	 * 请求与Servlet之间的映射
	 * key 请求路径
	 * value 对应的Servlet名字
	 */
	private static Map<String,String> servletMapping
		= new HashMap<String,String>();
	static {
		initServletMapping();
	}
	/*
	 * 初始化Servlet映射
	 */
	private static void initServletMapping() {
		/*
		 * 加载配置文件conf/servlets.xml
		 * 将所有<servlet>标签中的url属性值作为key
		 * 将class属性值所谓value存入到servletMapping
		 */
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File("conf/servlets.xml"));
			Element root = doc.getRootElement();
			List<Element> urlList = root.elements("servlet");
			for(Element e : urlList) {
				String url = e.attributeValue("url");
				String value = e.attributeValue("class");
				servletMapping.put(url, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean hasUrl(String url) {
		return servletMapping.containsKey(url);
	}
	/*
	 * 根据给定的url获取对应的Servlet名字
	 */
	public static String getServletByUrl(String url) {
		return servletMapping.get(url);
	}
	public static void main(String[] args) {
		System.out.println(servletMapping);
	}
}
