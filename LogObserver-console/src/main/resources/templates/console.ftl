<!DOCTYPE html>
<html>

<head>
    <title>日志跟踪器</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <script src="js/sockjs.min.js"></script>
    <script type="text/javascript">
    var ws = null;
    

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
    }

    function start() {
        var target = document.getElementById('target').value;
        var dist = document.getElementById('dist').value;
        ws = new SockJS(target);
        ws.onopen = function() {
            setConnected(true);
            ws.send(dist);
            log('开始跟踪');
        };
        ws.onmessage = function(event) {
            log(event.data);
        };
        ws.onclose = function() {
            setConnected(false);
            log('结束跟踪');
        };
    }

    function stop() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        setConnected(false);
    }
    
    function clearAll(){
                       document.getElementById('console').innerHTML="";
                       }


    function log(message) {
    
        var console = document.getElementById('console');
        //var content = document.getElementById('content');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        console.appendChild(p);
      console.scrollTop=console.scrollHeight;
        while (console.childNodes.length > 100) {
            console.removeChild(console.firstChild);       
        }
        
      
    }
    </script>
</head>

<body>
 <div class="container">
            <form class="form-inline">
                <div>
                    <select id="dist" class="form-control">
                        <option value="apollo1">apollo1</option>
                        <option value="diana-console1">diana-console1</option>
                        <option value="jupiter-job1">jupiter-job1</option>
                        <option value="atlantis2">atlantis2</option>
                        <option value="jupiter-web2">jupiter-web2</option>
                          <option value="diana-web1">diana-web1</option>
                    </select>
                </div>
                <div>
                    <input id="target" type="hidden" size="40" style="width: 350px" value="/tail" />
                </div>
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-success" id="connect" onclick="start();">开始</button>
                    <button type="button" class="btn btn-danger" id="disconnect" disabled="disabled" onclick="stop();">停止</button>
                    <button type="button" class="btn btn-info" id="clear"  onclick="clearAll();">清屏</button>
                    
 
                </div>
            </form>
            <div class="jumbotron" id="console">
 
</div>
</div>
</body>

</html>
