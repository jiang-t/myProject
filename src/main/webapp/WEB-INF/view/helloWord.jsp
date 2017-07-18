<html >
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/colajs/min/cola.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/colajs/min/semantic.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/colajs/min/3rd.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/colajs/min/cola.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/colajs/min/semantic.min.js"></script>

</head>
<script type="text/javascript">
cola(function (model) {
    model.set("name", "World");
    model.action({
    	message:function(name){
    		var aa = model.get("name");
    		alert(aa +" ===== "+ name);
    	},
    	read:function(name){
    		return "bind   "+name;
    	}
    	});
    
    model.set("addresses", [
        {
            city: "shanghai",
            postCode: 201101
        },
        {
            city: "beijing",
            postCode: 100020
        },
        {
            city: "shenzhen",
            postCode: 300021
        }  
    ]);
});
</script>
<body>
  Hello 
  <span c-bind="name"></span>
    <br>
    <input c-bind="name">
    <br>
    <button c-onclick="message(name)">shouMe</button>
    <br>
    <span c-bind="read(name)">shouMetwo</span>
    <br>
    <span>=======================================分割=======================================</span>
    <ul>
    	<li c-repeat="item in addresses"><span c-bind="item.city"></span>:<span c-bind="item.postCode"></span></li>
    </ul>
    
    <%=request.getContextPath() %>
    <br>
    <%=request.getServletPath() %>
    <br>
    <%=request.getContextPath() %>
    <br>
    <%=request.getPathInfo() %>
</body>
</html>