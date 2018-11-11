package com.citydo.microcloud.security.config;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//此方法并不可取 折中方法 不添加安全认证
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/hystrix.stream","/turbine.stream") ;
	}
	
	@Resource
	public void configGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.inMemoryAuthentication().withUser("citydojava").password("root")
		    //设置角色与管理员密码
			.roles("USER").and().withUser("admin").password("root")
			.roles("USER", "ADMIN");
	}
	
	//复写安全配置
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 表示所有的访问都必须进行认证处理后才可以正常进行
		http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
		// 所有的Rest服务一定要设置为无状态，以提升操作性能
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
