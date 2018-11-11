package com.citydo.microcloud.comsumer;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.citydo.vo.Dept;



@RestController
public class ConsumerDeptController {
	/*public static final String DEPT_GET_URL = "http://dept-8001:8001/dept/get/";
	public static final String DEPT_LIST_URL = "http://dept-8001:8001/dept/list/";
	public static final String DEPT_ADD_URL = "http://dept-8001:8001/dept/add";*/
	public static final String DEPT_GET_URL = "http://MICROCLOUD-PROVIDER-DEPT/dept/get/";
	public static final String DEPT_LIST_URL = "http://MICROCLOUD-PROVIDER-DEPT/dept/list/";
	public static final String DEPT_ADD_URL = "http://MICROCLOUD-PROVIDER-DEPT/dept/add";
	
	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	private HttpHeaders headers; 
	
	@Resource
	private LoadBalancerClient loadBalancerClient;
	
	@RequestMapping(value = "/consumer/dept/get")
	public Object getDept(@RequestParam("id")long id) {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("MICROCLOUD-PROVIDER-DEPT") ;
		System.out.println(
				"【*** ServiceInstance ***】host = " + serviceInstance.getHost()
						+ "、port = " + serviceInstance.getPort()
						+ "、serviceId = " + serviceInstance.getServiceId());
		Dept dept = this.restTemplate
				.exchange(DEPT_GET_URL + id, HttpMethod.GET,
						new HttpEntity<Object>(this.headers), Dept.class)
				.getBody();
		/*Dept dept = this.restTemplate
				.exchange(DEPT_GET_URL + id, HttpMethod.GET,
						new HttpEntity<Object>(this.headers), Dept.class)
				.getBody();*/
		//Dept dept = this.restTemplate.getForObject(DEPT_GET_URL + id,Dept.class);
		return dept;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/consumer/dept/list")
	public Object listDept() {
		List<Dept> allDepts = this.restTemplate
				.exchange(DEPT_LIST_URL, HttpMethod.GET,
						new HttpEntity<Object>(this.headers), List.class)
				.getBody();
		//List<Dept> allDepts = this.restTemplate.getForObject(DEPT_LIST_URL,List.class); 
		return allDepts;
	}
	@RequestMapping(value = "/consumer/dept/add")
	public Object addDept(Dept dept) {
		Boolean flag = this.restTemplate.exchange(DEPT_ADD_URL, HttpMethod.POST,
				new HttpEntity<Object>(dept, this.headers), Boolean.class)
				.getBody();
		//Boolean flag = this.restTemplate.postForObject(DEPT_ADD_URL, dept,Boolean.class);
		return flag;
	}
}
