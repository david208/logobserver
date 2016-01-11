<!DOCTYPE html>
<html>
<head>
	<title>日志跟踪器</title>
	<style type="text/css">
		#connect-container {
			float: left;
			width: 400px
		}

		#connect-container div {
			padding: 5px;
		}

		#console-container {
			float: left;
			margin-left: 15px;
			width: 1000px;
		}

		#console {
			border: 1px solid #CCCCCC;
			border-right-color: #999999;
			border-bottom-color: #999999;
			height: 700px;
			overflow-y: scroll;
			padding: 5px;
			width: 100%;
		}

		#console p {
			padding: 0;
			margin: 0;
		}
	</style>
	<script src="js/sockjs.min.js"></script>
	<script type="text/javascript">
		var ws = null;

		function setConnected(connected) {
			document.getElementById('connect').disabled = connected;
			document.getElementById('disconnect').disabled = !connected;
			document.getElementById('echo').disabled = !connected;
		}

		function connect() {
			var target = document.getElementById('target').value;
			var dist = document.getElementById('dist').value;
			ws = new SockJS(target);
			ws.onopen = function () {
				setConnected(true);
			    ws.send(dist);
				log('Info: WebSocket connection opened.');
			};
			ws.onmessage = function (event) {
				log(event.data);
			};
			ws.onclose = function () {
				setConnected(false);
				log('Info: WebSocket connection closed.');
			};
		}

		function disconnect() {
			if (ws != null) {
				ws.close();
				ws = null;
			}
			setConnected(false);
		}

		function echo() {
			if (ws != null) {
				var message = document.getElementById('message').value;
					var dist = document.getElementById('dist').value;
				log('Sent: ' + dist +':'+message);
				ws.send(dist +':'+message);
			} else {
				alert('WebSocket connection not established, please connect.');
			}
		}

		function log(message) {
			var console = document.getElementById('console');
			var p = document.createElement('p');
			p.style.wordWrap = 'break-word';
			p.appendChild(document.createTextNode(message));
			console.appendChild(p);
			while (console.childNodes.length > 25) {
				console.removeChild(console.firstChild);
			}
			console.scrollTop = console.scrollHeight;
		}
	</script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable
	Javascript and reload this page!</h2></noscript>
<div>
	<div id="connect-container">
		<div>
			<input id="target" type="text" size="40" style="width: 350px" value="/echo"/>
		</div>
		<div>
			<button id="connect" onclick="connect();">开始</button>
			<button id="parse" disabled="disabled" onclick="parse();">暂停</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">停止</button>
		</div>
		<div>
		   <#-- <input id="dist" type="text" size="40" style="width: 350px" value=""/> -->
		    <select id="dist" class="form-control">
  <option value="apollo">apollo</option>
  <option>2</option>
  <option>3</option>
  <option>4</option>
  <option>5</option>
</select>
			<textarea id="message" style="width: 350px"></textarea>
		</div>
		
	</div>
	<div id="console-container">
		<div id="console"></div>
	</div>
</div>
</body>
</html>
