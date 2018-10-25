package com.houseAgent.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Shiro 配置
 * 
Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。 
既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
*/
@Configuration 
public class ShiroConfiguration {
	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 * 
	 	Filter Chain定义说明 
		1、一个URL可以配置多个Filter，使用逗号分隔 
		2、当设置多个过滤器时，全部验证通过，才视为通过 
		3、部分过滤器可指定参数，如perms，roles
	 * 
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
		
		 // 必须设置 SecurityManager  
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		// 自定义过滤器
		Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
		filterMap.put("checkOutTime", new CheckOutTimeFilter());
		filterMap.put("myLogoutFilter", new MyLogoutFilter());
		filterMap.put("perms", new ShiroPermsFilter());	
		filterMap.put("roles", new ShiroRolesFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		
		//配置退出 过滤器,使用自定义的退出过滤器
//		filterChainDefinitionMap.put("/logout", "myLogoutFilter");
		
		
		//配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/index", "user");
//        filterChainDefinitionMap.put("/", "user");
        	//个人信息
        filterChainDefinitionMap.put("/staff/updateInfo", "checkOutTime,user");
        filterChainDefinitionMap.put("/staff /upload", "checkOutTime,user");
        	//交易管理模块
        filterChainDefinitionMap.put("/trade/*", "checkOutTime,user");
        
        
        //测试：
        
        //配置不拦截的静态资源
        filterChainDefinitionMap.put("/Admin/**", "anon");
        filterChainDefinitionMap.put("/Customer/**", "anon");
        filterChainDefinitionMap.put("/users/**", "anon");
        filterChainDefinitionMap.put("/showUser/**", "anon");
        filterChainDefinitionMap.put("/showStore/**", "anon");
        filterChainDefinitionMap.put("/showStaffStore/**", "anon");
        filterChainDefinitionMap.put("/houseRent/**", "anon");
        filterChainDefinitionMap.put("/showHouseRent/**", "anon");
        
        
//        filterChainDefinitionMap.put("/index.html", "anon");
//        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/users/login", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
//        filterChainDefinitionMap.put("/403", "anon");//403界面
//        filterChainDefinitionMap.put("/**", "shiroLoginFilter");
        
        //租房审核(后台)
        filterChainDefinitionMap.put("/rentapply/start","checkOutTime,roles[admin]");
        filterChainDefinitionMap.put("/rentapply/tasks","checkOutTime,roles[admin]");
        filterChainDefinitionMap.put("/rentapply/claim/*","checkOutTime,roles[admin]");
        filterChainDefinitionMap.put("/rentapply/complete/*","checkOutTime,roles[admin]");
        //门店管理模块
        filterChainDefinitionMap.put("/store/*","checkOutTime,roles[admin]");
        //房源管理模块
        filterChainDefinitionMap.put("/house/*","checkOutTime,roles[staff]");
        filterChainDefinitionMap.put("/addHouse","checkOutTime,roles[staff]");
        //管理员查看员工排名 & 员工排名
        filterChainDefinitionMap.put("/tradeRanking/staff","checkOutTime,roles[admin,manager]");
        //门店排名
        filterChainDefinitionMap.put("/admin/tradeRanking/store","checkOutTime,roles[admin]");
        //报表
        filterChainDefinitionMap.put("/report/findAll","checkOutTime,roles[admin,manager]");
        //日程管理
        filterChainDefinitionMap.put("/calendar/*","checkOutTime,roles[manager,staff]");
        //员工管理
        filterChainDefinitionMap.put("/staff/*","checkOutTime,roles[manager]");
        //客户模块
        filterChainDefinitionMap.put("/usersManage/*","checkOutTime,roles[admin]");
//        filterChainDefinitionMap.put("/userInfo/userDel","roles[admin]");
		//<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
	    //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        
        
        

        
      filterChainDefinitionMap.put("/**", "anon");
//		filterChainDefinitionMap.put("/**", "authc");
		
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/Customer/login&registration.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		
		return shiroFilterFactoryBean;
	}
	
	
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		//设置realm.
		securityManager.setRealm(myShiroRealm());
		
		//注入缓存管理器;
		securityManager.setCacheManager(ehCacheManager());//这个如果执行多次，也是同样的一个对象;
		
//		//注入记住我管理器;
//		securityManager.setRememberMeManager(rememberMeManager());
		
		return securityManager;
	}
	
	
	/**
	 * 身份认证realm;
	 * (这个需要自己写，账号密码校验；权限等)
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());;
		return myShiroRealm;
	}
	
	/**
	 * 凭证匹配器
	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 *  所以我们需要修改下doGetAuthenticationInfo中的代码;
	 * ）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		
		return hashedCredentialsMatcher;
	}
	
	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	
	/**
	 * shiro缓存管理器;
	 * 需要注入对应的其它的实体类中：
	 * 1、安全管理器：securityManager
	 * 可见securityManager是整个shiro的核心；
	 * @return
	 */
	@Bean
	public EhCacheManager ehCacheManager(){
		System.out.println("ShiroConfiguration.getEhCacheManager()");
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return cacheManager;
	}
	
	/**
	 * cookie对象;
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
		System.out.println("ShiroConfiguration.rememberMeCookie()");
		//这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//<!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}
	
	/**
	 * cookie管理对象;
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
		System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	/**
	 *  自定义logout拦截器
	 * @return
	 */
//	@Bean(name="myLogoutFilter")
//    public MyLogoutFilter myLogoutFilter(){
//		MyLogoutFilter myLogoutFilter = new MyLogoutFilter();
//        return myLogoutFilter;
//    }
}
