package com.citydo.microcloud.config;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.sun.net.httpserver.HttpHandler;

@Configuration
public class RestConfig {
	
   @Bean
   public HttpHeaders getHeaders() {//要进行一个http头信息
	   HttpHeaders httpHeaders = new HttpHeaders();//定义一个HTTP的头信息
	   String auth="citydojava:root"; //认证的原始信息
	   byte[] encode = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));//加密处理
	   //在进行授权的头信息内容的配置时候加密信息要有空格
	   String authHeader="Basic "+new String(encode);
	   httpHeaders.set("Authorization", authHeader);
	   return httpHeaders;
   }
	
	
	
	@Bean
	@LoadBalanced  //客户端负载均衡
	public RestTemplate getRestTemplate() {
		return new RestTemplate() ;
	}

}
