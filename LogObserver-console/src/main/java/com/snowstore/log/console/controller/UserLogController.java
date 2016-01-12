package com.snowstore.log.console.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snowstore.log.console.service.UserService;

//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class UserLogController {

	@RequestMapping("/403")
	public String m403() {
		return "/403";
	}

	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

	@Autowired
	UserService userService;

	@RequestMapping
	public String console(HttpServletRequest httpServletRequest) {
		if (null == httpServletRequest.getSession().getAttribute("uname")) {
			httpServletRequest.getSession().setAttribute("uname", userService.getUsername());
		}
		return "console";
	}

}
