<html >
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
</head>
<script type="text/javascript">
	function getJson(){
		var val = $("#aa").text;
		alert(val);
	}
</script>
<body>
<h2>Hello World !</h2>
<h2 id="aa">sdsd</h2>
<button onclick="getJson();">获取</button>
</body>
</html>
