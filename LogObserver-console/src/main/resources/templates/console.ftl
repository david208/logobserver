<!DOCTYPE html>
<html>

<head>
    <title>日志跟踪</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="${rc.contextPath}/css/bootstrap.min.css" />

</head>

<body>
    <div class="container">
        <div align="right">
            <span class="glyphicon glyphicon-user"></span>${uname!""},您好
             <a href="/" class="btn">
                <span class="glyphicon glyphicon-off "></span>主页</a>
         <!--   <a href="${rc.contextPath}/logout" class="btn">
                <span class="glyphicon glyphicon-off "></span>退出</a>-->
        </div>
        <form class="form-inline">
            <select id="destination" class="form-control" autofocus>
                <option value="">系统名</option>
                <#list systemNames as item>
                <option value="${item}">${item}</option>
                </#list>
         <#--       <option value="apollo1">apollo1</option>
                <option value="atlantis1">atlantis1</option>
                <option value="atlantis2">atlantis2</option>
                <option value="diana-console1">diana-console1</option>
                <option value="diana-web1">diana-web1</option>
                <option value="ebo-athene1">ebo-athene1</option>
                <option value="ebo-crm1">ebo-crm1</option>
                <option value="ebo-hera1">ebo-hera1</option>
                <option value="ebo-uc1">ebo-uc1</option>
                <option value="jupiter-job1">jupiter-job1</option>
                <option value="jupiter-web1">jupiter-web1</option>
                <option value="jupiter-web2">jupiter-web2</option>
                <option value="mars1">mars1</option>
                <option value="neptune1">neptune1</option>
                <option value="tpp1">tpp1</option>
                <option value="trident1">trident1</option> -->
            </select>

            <div class="btn-group" role="group" aria-label="...">
                <button type="button" class="btn btn-success" id="connect" onclick="start();">开始(1)</button>
                <button type="button" class="btn btn-danger" id="disconnect" disabled="disabled" onclick="stop();">停止(2)</button>
                <button type="button" class="btn btn-info" id="clear" onclick="clearAll();">清屏(3)</button>
            </div>

        </form>

      

        </div>
    </div>
      <div class="jumbotron" style=" background:#000; color:#fff; margin:20px;" >
      <div  style=" background:#000; color:#fff; margin:20px;" id="console">
     </div>
     </div>
    <script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${rc.contextPath}/js/sockjs.min.js"></script>
    <script type="text/javascript">
    var ws = null;


    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
    }

    function start() {
        var destination = document.getElementById('destination').value;
        if (destination == "") {
            alert("请选择系统");
            return false;
        }
        ws = new SockJS("${rc.contextPath}/tail");
        ws.onopen = function() {
            setConnected(true);
            ws.send(destination);
            log('开始跟踪'+destination);
        };
        ws.onmessage = function(event) {
            log(event.data);
        };
        ws.onclose = function() {
            setConnected(false);
            log('结束跟踪'+destination);
        };
    }

    function stop() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        setConnected(false);
    }

    function clearAll() {
        document.getElementById('console').innerHTML = "";
    }

    function updateSelect(message){
    var s = $("#destination");
    s.append("<option value=message>message</option>");
    }

    function log(message) {

        var console = document.getElementById('console');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        console.appendChild(p);
        console.scrollTop = console.scrollHeight;
        while (console.childNodes.length > 1000) {
            console.removeChild(console.firstChild);
        }


    }


    $(document).keydown(function(e) {
        if (e.which == 49) {
            if (!document.getElementById('connect').disabled) {
                start();
            }
        }
        if (e.which == 50) {
            if (document.getElementById('connect').disabled) {
                stop();
            }
        }
        if (e.which == 51) {
            clearAll();
        }
    });
    
    
   $(function() {
		$("#destination").change(function() {
		stop();
		});
		});
    </script>
</body>

</html>
