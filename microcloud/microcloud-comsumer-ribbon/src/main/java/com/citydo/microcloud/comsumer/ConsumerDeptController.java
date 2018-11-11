package com.citydo.microcloud.comsumer;
import java.net.URI;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.citydo.commons.config.MyLoadBalanceConfig;
import com.citydo.vo.Dept;


@RestController
@RibbonClient(name = "microcloud-provider-dept", configuration = MyLoadBalanceConfig.class)
public class ConsumerDeptController {
	public static final String DEPT_REST_TOPIC = "microcloud-provider-dept";
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private HttpHeaders headers;
	@Resource
	private LoadBalancerClient loadBalancerClient;
	@RequestMapping(value = "/consumer/dept/get")
	public Object getDept(long id) {
		ServiceInstance serviceInstance = this.loadBalancerClient
				.choose(DEPT_REST_TOPIC);
		System.out.println(
				"【*** ServiceInstance ***】host = " + serviceInstance.getHost()
						+ "、port = " + serviceInstance.getPort()
						+ "、serviceId = " + serviceInstance.getServiceId()
						+ "、uri = " + serviceInstance.getUri());
		URI deptUri = URI.create(String.format("http://%s:%s/dept/get/" + id,
				serviceInstance.getHost(), serviceInstance.getPort()));
		Dept dept = this.restTemplate
				.exchange(deptUri, HttpMethod.GET,
						new HttpEntity<Object>(this.headers), Dept.class)
				.getBody();
		return dept;
	}
}