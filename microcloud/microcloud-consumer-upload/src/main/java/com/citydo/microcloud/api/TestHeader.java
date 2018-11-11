package com.citydo.microcloud.api;

import java.nio.charset.Charset;
import java.util.Base64;

public class TestHeader {
	public static void mains(String[] args) {
		String auth = "citydojava:root"; // 认证的原始信息
		byte[] encodedAuth = Base64.getEncoder()
				.encode(auth.getBytes(Charset.forName("US-ASCII")));	// 进行一个加密的处理
		String authHeader = "Basic" + new String(encodedAuth) ;
		System.out.println(authHeader);
	}
}
