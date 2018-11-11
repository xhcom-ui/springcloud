package com.citydo.microcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citydo.service.IDeptClientService;
import com.citydo.vo.Dept;


@RestController
public class ConsumerDeptController {
	@Resource
	private IDeptClientService deptService ;
	@RequestMapping(value = "/consumer/dept/get")
	public Object getDept(@RequestParam("id")long id) {
		return this.deptService.get(id);
	}
	@RequestMapping(value = "/consumer/dept/list")
	public Object listDept() {
		return this.deptService.list();
	}
	@RequestMapping(value = "/consumer/dept/add")
	public Object addDept(Dept dept) throws Exception {
		return this.deptService.add(dept);
	}
}
