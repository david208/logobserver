<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>日志中心</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
    
    <script src="${rc.contextPath}/js/jquery.cookie.js"></script>
  <script>
    $.cookie('Hm_lvt_4174b42be17806794feef4d1a53e30d5', '', { expires: -1,domain:'.jlfex.com'}); 
    </script>
</head>

<body height="100%">

    <iframe id="iFrame1" name="iFrame1" width="100%" height="100%" onload="this.height=document.body.scrollHeight-50" frameborder="0" src="/kibana">
    </iframe>
    <div align="center">
    <span class="glyphicon glyphicon-user"></span>${uname!""},您好
        <a href="/monitor" class="btn">
            <span class="glyphicon glyphicon-signal "></span>异常监控</a>
         <a href="/console" class="btn">
            <span class="glyphicon glyphicon-console"></span>跟踪</a>
        <a href="/logout" class="btn">
            <span class="glyphicon glyphicon-off "></span>退出</a>
    </div>

</body>

</html>
