package com.citydo.commons.config;

import org.springframework.context.annotation.Bean;
import com.netflix.loadbalancer.IRule;

public class MyLoadBalanceConfig {
	
	@Bean
	public IRule ribbonRule() {
		//随机访问策略
		//return new com.netflix.loadbalancer.RandomRule();
		//可用资源过滤
		return new com.netflix.loadbalancer.AvailabilityFilteringRule();
	}

}
