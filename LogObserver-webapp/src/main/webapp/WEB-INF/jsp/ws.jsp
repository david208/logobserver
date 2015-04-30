<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<%-- <script src="${ctx}/static/js/stomp.js"></script> --%>


<script type="text/javascript">
	var wsServer = 'ws://localhost:8080/myHandler';
	var websocket = new WebSocket(wsServer);
	websocket.onopen = function(evt) {
		onOpen(evt)
	};
	websocket.onclose = function(evt) {
		onClose(evt)
	};
	websocket.onmessage = function(evt) {
		onMessage(evt)
	};
	websocket.onerror = function(evt) {
		onError(evt)
	};
	function onOpen(evt) {
		console.log("Connected to WebSocket server.");
	}
	function onClose(evt) {
		console.log("Disconnected");
	}
	function onMessage(evt) {
		console.log('Retrieved data from server: ' + evt.data);
	}
	function onError(evt) {
		console.log('Error occured: ' + evt.data);
	}

	/* var socket = new SockJS("/spring-websocket-portfolio/portfolio");
	var stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
	}); */
</script>
</head>
<body>
</body>
</html>
