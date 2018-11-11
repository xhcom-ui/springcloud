package com.citydo.service;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.citydo.commons.config.FeignClientConfig;
import com.citydo.service.fallback.IDeptClientServiceFallbackFactory;
import com.citydo.vo.Dept;
//支持fallback调用
/*@FeignClient(value="MICROCLOUD-PROVIDER-DEPT",configuration=FeignClientConfig.class,
fallbackFactory=IDeptClientServiceFallbackFactory.class)
public interface IDeptClientService {
	@RequestMapping(method=RequestMethod.GET,value="/dept/get/{id}")
	public Dept get(@PathVariable("id") long id) ;
	@RequestMapping(method=RequestMethod.GET,value="/dept/list")
	public List<Dept> list() ;
	@RequestMapping(method=RequestMethod.POST,value="/dept/add")
	public boolean add(Dept dept) ;
}*/
@FeignClient(value="MICROCLOUD-ZUUL-GATEWAY",configuration=FeignClientConfig.class,
fallbackFactory=IDeptClientServiceFallbackFactory.class)
public interface IDeptClientService {
	@RequestMapping(method=RequestMethod.GET,value="/citydo-proxy/dept-proxy/dept/get/{id}")
	public Dept get(@PathVariable("id") long id) ;
	@RequestMapping(method=RequestMethod.GET,value="/citydo-proxy/dept-proxy/dept/list")
	public List<Dept> list() ;
	@RequestMapping(method=RequestMethod.POST,value="/citydo-proxy/dept-proxy/dept/add")
	public boolean add(Dept dept) ;
}
