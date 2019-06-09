package com.deb.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.deb.demo.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
@Order(1)
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/web-resources/**").addResourceLocations("/resources/").setCachePeriod(30);
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){

		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
		.excludePathPatterns("/")
		.excludePathPatterns("/login")
		.excludePathPatterns("/web-resources/**");
		registry.addInterceptor(webContentInterceptor());
	}





	@SuppressWarnings("deprecation")
	@Bean
	public WebContentInterceptor webContentInterceptor() {
		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.setCacheSeconds(0);
		interceptor.setUseExpiresHeader(true);
		interceptor.setUseCacheControlHeader(true);
		interceptor.setUseCacheControlNoStore(true);

		return interceptor;
	}
}
