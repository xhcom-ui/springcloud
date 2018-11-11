package com.citydo.service.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import com.citydo.service.IDeptClientService;
import com.citydo.vo.Dept;

import feign.hystrix.FallbackFactory;

@Component
public class IDeptClientServiceFallbackFactory 
                        implements FallbackFactory<IDeptClientService> {

	@Override
	public IDeptClientService create(Throwable cause) {
		
		return new IDeptClientService() {

			@Override
			public Dept get(long id) {
				Dept vo = new Dept();
				vo.setDeptno(888888L);
				vo.setDname("{ERROR}Feign-Hystrix"); // 错误的提示
				vo.setLoc("Consumer客户端提供");
				return vo;
			}

			@Override
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
	}

}
