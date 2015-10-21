package com.snowstore.log.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;


import com.snowstore.hera.connector.monitor.impl.MonitorInfo;
import com.snowstore.hera.connector.monitor.impl.ZooL;
import com.snowstore.log.entity.FileInfo;
import com.snowstore.log.entity.UserLog;
import com.snowstore.log.entity.UserLogEs;
import com.snowstore.log.service.UserLogService;
import com.snowstore.log.vo.TreeData;
import com.snowstore.log.vo.UserLogVo;
import com.snowstore.log.vo.TreeData.NodeData;

@Controller
@RequestMapping
public class UserLogController {
	@SuppressWarnings("unused")
	private  static final Logger LOGGER = LoggerFactory.getLogger(UserLogController.class);

	@Autowired
	private UserLogService userLogService;

	@RequestMapping
	public String index(HttpServletRequest httpServletRequest) {
		if (null == httpServletRequest.getSession().getAttribute("uname"))
			httpServletRequest.getSession().setAttribute("uname", userLogService.getUsername());
		return "/index";
	}

	@RequestMapping("/monitor")
	public String monitorNew(HttpServletRequest httpServletRequest) {
		if (null == httpServletRequest.getSession().getAttribute("uname"))
			httpServletRequest.getSession().setAttribute("uname", userLogService.getUsername());
		return "/monitor_new";
	}
	
	@RequestMapping("/403")
	public String m403(HttpServletRequest httpServletRequest) {
		return "/403";
	}

	@Deprecated
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
		return "/userLog";
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

	@Autowired(required = false)
	private ZooL zooL;

	@Deprecated
	// @RequestMapping("/monitor")
	public String monitor() {
		return "/monitor";
	}

	@Deprecated
	// @RequestMapping("/getData")
	// @ResponseBody
	public TreeData getData() {
		Map<String, Map<String, MonitorInfo>> nodeMap = zooL.getZooListener().getNodeMap();
		TreeData treeData = new TreeData();
		Iterator<String> iterator = nodeMap.keySet().iterator();
		while (iterator.hasNext()) {
			String text = iterator.next();
			NodeData nodeData = new NodeData();
			nodeData.setText(text);
			Map<String, MonitorInfo> map = nodeMap.get(text);
			for (String nodeName : map.keySet()) {
				NodeData nodeData1 = new NodeData();
				MonitorInfo info = map.get(nodeName);
				nodeData1.setText(nodeName);
				String[] a = { info.getIp(), info.getAddress() };
				nodeData1.setTags(a);
				if (nodeData.getNodes() == null) {
					ArrayList<NodeData> nodes = new ArrayList<NodeData>();
					nodeData.setNodes(nodes);
				}
				nodeData.getNodes().add(nodeData1);
			}
			treeData.getNodeDatas().add(nodeData);
		}
		return treeData;
	}

	@RequestMapping("/fileLog")
	public String fileLog(UserLogVo formVo, Model model, HttpServletRequest httpServletRequest) {

		Page<UserLogEs> page = userLogService.findPageEs(formVo);
		model.addAttribute("resultVo", page);

		model.addAttribute("keyword", formVo.getKeyword());
		model.addAttribute("systemCode", formVo.getSystemCode());
		if (null == httpServletRequest.getSession().getAttribute("uname"))
			httpServletRequest.getSession().setAttribute("uname", userLogService.getUsername());
		if (null == httpServletRequest.getSession().getAttribute("systemCodeList"))
			httpServletRequest.getSession().setAttribute("systemCodeList", userLogService.findBySystemCodeGroup());
		return "/fileInfo";
	}

}
