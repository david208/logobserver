package com.snowstore.log.console.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snowstore.log.console.service.BroadcastService;
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
	public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.sendRedirect("/login");
	}

	@Autowired
	UserService userService;
	@Autowired
	BroadcastService broadcastService;

	@RequestMapping
	public String console(HttpServletRequest httpServletRequest, Model model) {
		if (null == httpServletRequest.getSession().getAttribute("uname")) {
			httpServletRequest.getSession().setAttribute("uname", userService.getUsername());
		}
		model.addAttribute("systemNames", broadcastService.getSystemNames());
		return "console";
	}

}
