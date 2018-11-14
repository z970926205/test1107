package com.example.demo.jinx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.jinx.interceptor.RegisterInterceptor;
import com.example.demo.jinx.interceptor.UrlInterceptor;
@SpringBootConfiguration
public class TSpringMVCConfig extends WebMvcConfigurerAdapter{
	@Autowired
	UrlInterceptor urlInterceptor;
	@Autowired
	RegisterInterceptor registerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlInterceptor).addPathPatterns("/**");
		registry.addInterceptor(registerInterceptor).addPathPatterns("/personal/**");
		super.addInterceptors(registry);
	}
	
}
