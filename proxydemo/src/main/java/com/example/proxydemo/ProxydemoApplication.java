package com.example.proxydemo;

import com.example.proxydemo.filters.ErrorFilter;
import com.example.proxydemo.filters.PostFilter;
import com.example.proxydemo.filters.PreFilter;
import com.example.proxydemo.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@EnableZuulProxy
@SpringBootApplication
public class ProxydemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProxydemoApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}
