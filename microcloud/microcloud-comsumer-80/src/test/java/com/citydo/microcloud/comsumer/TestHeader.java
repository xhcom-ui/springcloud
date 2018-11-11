package com.citydo.microcloud.comsumer;

import java.nio.charset.Charset;
import java.util.Base64;

public class TestHeader {
	
	public static void main(String[] args) {
		String auth="citydojava:root"; //认证的原始信息
		byte[] encode = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader="Basic"+new String(encode);
		System.out.println(authHeader);
		System.out.println(new String(encode));
	}

}
