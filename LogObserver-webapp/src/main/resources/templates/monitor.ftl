<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <!-- 新 Bootstrap 核心 CSS 文件 -->
        <link rel="stylesheet" href="/css/bootstrap.min.css" />

        <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="/js/jquery-1.11.1.min.js"></script>

        <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="/js/bootstrap.min.js"></script>
<link href="/list/css/bootstrap-treeview.css" rel="stylesheet">
        <title>日志中心</title>
         <script src="/list/js/bootstrap-treeview.js"></script>
  	<script type="text/javascript">
  		var wsServer = 'ws://localhost:8085/myHandler'; //服务器地址
var websocket = new WebSocket(wsServer); //创建WebSocket对象
//websocket.send("hello");//向服务器发送消息
//alert(websocket.readyState);//查看websocket当前状态
websocket.onopen = function (evt) {
	//已经建立连接
};
websocket.onclose = function (evt) {
	window.location.reload();
};
websocket.onmessage = function (evt) {
	getDate();
};
websocket.onerror = function (evt) {
	//产生异常
}; 

function getDate(){
   
    $.getJSON("/getData",function(result){
        var data1 = result.nodeDatas;
        console.log(data1);
         $('#treeview1').treeview({
	          data: data1,
	          showTags:true
	         // enableLinks:true,
	        //  onNodeSelected: function(event, node) {
	        //    $('#event_output').prepend('<p>You clicked ' + node.href + '</p>');
	       //   }
	        });
    });
    }

		
    $(function() {
    
    getDate();
    
        
	        });
	        </script>

</head>

<body>
  	<div class="container">
	      <div class="row">
	        <div class="col-sm-4">
	          <h2>cloud</h2>
	          <div id="treeview1" class=""></div>
	        </div>
	        </div>
	        
   
	        
</body>

</html>
