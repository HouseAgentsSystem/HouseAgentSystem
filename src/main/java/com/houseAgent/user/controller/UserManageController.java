package com.houseAgent.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houseAgent.common.web.ExtAjaxResponse;
import com.houseAgent.common.web.ExtjsPageRequest;
import com.houseAgent.user.domain.User;
import com.houseAgent.user.service.UserService;

@RestController
@RequestMapping("/usersManage")
public class UserManageController {
	@Autowired
	private UserService userService;
	@GetMapping
	public Page<User> findAll(User user,ExtjsPageRequest pageRequest){
		return userService.findAll(User.getWhereClause(user), pageRequest.getPageable());
	}
	
	@PutMapping(value="{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse UpdateOrder(@PathVariable("id") Long id , @RequestBody User user) {
		try {
			userService.update(id,user);
			System.out.println(user);
			return new ExtAjaxResponse(true,"修改成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"修改失败！！！");
		}
	}
}
