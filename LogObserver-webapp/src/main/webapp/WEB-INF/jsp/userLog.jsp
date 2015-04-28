<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" />

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap-theme.min.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx}/static/style/bootstrap-switch.min.css" />
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="${ctx}/static/js/bootstrap-switch.min.js"
	type="text/javascript"></script>

<title></title>
<script type="text/javascript">
	var refreshTime;
	$(function() {
		if ("${refreshTime}" == "")
			refreshTime = 10000;
		else
			refreshTime = parseInt("${refreshTime}");
		$("input[value=" + refreshTime + "]").attr("checked", "checked");
		if ("${type}" == "" || "${type}" == "true")
			var type = true;
		else
			var type = false;
		/* 	$("[name='my-checkbox']").bootstrapSwitch(); */
		$('input[name="my-checkbox"]').bootstrapSwitch('state', type);
		$("#type").val(type);
		if (type == true)
			refreash();
		$('input[name="my-checkbox"]').on(
				'switchChange.bootstrapSwitch',
				function(event, state) {
					type = $('input[name="my-checkbox"]').bootstrapSwitch(
							'state');
					$("#type").val(type);
					if (type == true)
						refreash();
					else
						close();

				});

		$("#search").click(function() {
			$("#queryFrom").submit();
		});

		$("input[name=refreshTime]").click(function() {
			$("#queryFrom").submit();
		});

	})
	var timer;

	function myrefresh() {
		window.location.reload();
	}
	function refreash() {
		timer = setInterval('myrefresh()', refreshTime);
	}//指定1秒刷新一次
	function close() {
		clearInterval(timer);
	}
</script>
</head>
<body>
	<div class="container">

		<div class="rightbar fl table-responsive">

			<table class="table table-striped  table-hover ">

				<caption style="text-align: center">
					<h1>用户操作记录</h1>
					<form method="post" id="queryFrom" class="form-inline">
						<fieldset>

							<div class="row">
								<div class="col-lg-2">
									<input id="switch-state" type="checkbox" checked=""
										name="my-checkbox" /> <input name="type" id="type"
										type="hidden"></input>
								</div>
								<div class="col-lg-4">
									<label class="radio-inline"> <input type="radio"
										name="refreshTime" id="r10000" value="10000"/> 10s </label> <label
										class="radio-inline"> <input type="radio"
										name="refreshTime" id="r30000" value="30000"/> 30s </label> <label
										class="radio-inline"> <input type="radio"
										name="refreshTime" id="r60000" value="60000"/> 60s </label>
								</div>
								<div class="col-lg-6">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="关键字..."
											id="username" value="${username}" name="username"><span
											class="input-group-btn">
											<button id="search" class="btn btn-default" type="button">Go!</button>
										</span> </input>
									</div>
								</div>
								</div>
						</fieldset>
					</form>
				</caption>
				<thead>
					<tr>
						<th>系统号</th>
						<th>用户名</th>
						<th>操作</th>
						<th>记录时间</th>
						<th>输入</th>
						<th>输出</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultVo.content}" var="item" varStatus="i">
						<tr>
							<td><c:out value='${item.systemCode}' /></td>
							<td><c:out value='${item.username }' /></td>
							<td><c:out value='${item.remark}' /></td>
							<td class="redfont"><fmt:formatDate value="${item.logTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td style="word-wrap:break-word; word-break:break-all;"><c:out value='${item.arg}' /></td>
							<td><c:out value='${item.result }' /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clearfix">
				<tags:pageDemo page="${resultVo}" queryForm="queryFrom" />

			</div>
		</div>
	</div>
</body>
</html>
