package com.snowstore.log.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class UserLogController {

	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

	@RequestMapping
	public String console() {
		return "console";
	}

}
