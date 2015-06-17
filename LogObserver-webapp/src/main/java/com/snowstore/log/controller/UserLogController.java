package com.snowstore.log.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snowstore.log.entity.FileInfo;
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
	public String userLog(UserLogVo formVo, Model model, HttpServletRequest httpServletRequest) {
		Page<UserLog> page = userLogService.findPage(formVo);
		model.addAttribute("resultVo", page);

		model.addAttribute("type", formVo.getType());
		model.addAttribute("keyword", formVo.getKeyword());
		model.addAttribute("refreshTime", formVo.getRefreshTime());
		model.addAttribute("systemCode", formVo.getSystemCode());
		if (null == httpServletRequest.getSession().getAttribute("uname"))
			httpServletRequest.getSession().setAttribute("uname", userLogService.getUsername());
		if (null == httpServletRequest.getSession().getAttribute("systemCodeList"))
			httpServletRequest.getSession().setAttribute("systemCodeList", userLogService.findBySystemCodeGroup());

		/* broadcastService.broadcast("1"); */
		return "/userLog";
	}

	@RequestMapping(value = "/ws")
	public String ws() {

		return "ws";
	}

	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

	@RequestMapping(value = "/file/{id}")
	public void file(@PathVariable String id, HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setContentType("APPLICATION/OCTET-STREAM");
		OutputStream outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
		FileInfo fileInfo = userLogService.getFile(id);
		httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + new String(fileInfo.getFileName().getBytes("gb2312"), "iso-8859-1"));
		outputStream.write(fileInfo.getContent());
		outputStream.flush();
		outputStream.close();

	}

}
