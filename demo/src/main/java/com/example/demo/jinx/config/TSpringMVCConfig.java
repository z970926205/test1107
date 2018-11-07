package com.example.demo.jinx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.jinx.interceptor.UrlInterceptor;
@SpringBootConfiguration
public class TSpringMVCConfig extends WebMvcConfigurerAdapter{
	@Autowired
	UrlInterceptor urlInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
}
