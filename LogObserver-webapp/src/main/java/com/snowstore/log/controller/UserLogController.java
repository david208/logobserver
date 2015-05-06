package com.snowstore.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snowstore.log.entity.UserLog;
import com.snowstore.log.service.UserLogService;
import com.snowstore.log.vo.UserLogVo;
import com.snowstore.log.ws.BroadcastService;

@Controller
@RequestMapping
public class UserLogController {

	@Autowired
	private UserLogService userLogService;
	@Autowired
	private BroadcastService broadcastService;


	@RequestMapping
	public String userLog(UserLogVo formVo, Model model) {
		Page<UserLog> page = userLogService.findPage(formVo);
		model.addAttribute("resultVo", page);

		model.addAttribute("type", formVo.getType());
		model.addAttribute("username", formVo.getUsername());
		model.addAttribute("refreshTime", formVo.getRefreshTime());
		/*broadcastService.broadcast("1");*/
		return "/userLog";
	}

	@RequestMapping(value = "/ws")
	public String ws() {

		return "ws";
	}

}
