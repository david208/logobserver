<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>日志中心</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="/js/jquery-1.11.1.min.js"></script>
 <script type="text/javascript">
 
     $(function() {
     var length = document.body.scrollHeight-50;
     var max = window.screen.availHeight-50 ;
      $("#exitS").hide();
  $('#fullS').click(function() {
  $(this).hide();
        $("#exitS").show();
           $("#iFrame1").height(max); 
        
      });
       $('#exitS').click(function() {
  $(this).hide();
        $("#fullS").show();
         $("#iFrame1").height(length); 
      });
      
     
  })
   // 判断各种浏览器，找到正确的方法
function launchFullscreen(element) {
  if(element.requestFullscreen) {
    element.requestFullscreen();
  } else if(element.mozRequestFullScreen) {
    element.mozRequestFullScreen();
  } else if(element.webkitRequestFullscreen) {
    element.webkitRequestFullscreen();
  } else if(element.msRequestFullscreen) {
    element.msRequestFullscreen();
  }
  }
  function exitFullscreen() {
  if(document.exitFullscreen) {
    document.exitFullscreen();
  } else if(document.mozCancelFullScreen) {
    document.mozCancelFullScreen();
  } else if(document.webkitExitFullscreen) {
    document.webkitExitFullscreen();
  }
}
  

</script>
</head>

<body height="100%">

    <iframe id="iFrame1" name="iFrame1" width="100%" height="100%" onload="this.height=document.body.scrollHeight-50" frameborder="0" src="/kibana/#/dashboard/monitor?_g=(refreshInterval:(display:'1%20minute',pause:!f,section:2,value:60000),time:(from:now%2Fd,mode:quick,to:now%2Fd))">
    </iframe>
    <div align="center" > 
    <span class="glyphicon glyphicon-user"></span>${uname!""},您好
    <a href="/" class="btn">
            <span class="glyphicon glyphicon-step-backward"></span>返回</a>
    <a href="#" class="btn" id="fullS" onclick="launchFullscreen(document.documentElement);"> 
    <span  class="glyphicon glyphicon-fullscreen" ></span>全屏</a>
    <a href="#" class="btn"  id="exitS" onclick="exitFullscreen();"> 
    <span  class="glyphicon glyphicon-screenshot" ></span>还原</a>
        
    <a href="/logout" class="btn">
            <span class="glyphicon glyphicon-off "></span>退出</a>
    </div>
</body>

</html>
