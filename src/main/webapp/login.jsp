<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录</title>
</head>
<body>
${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
   <table>
      <tr>
         <td>用户名1：</td>
         <td><input type="text" name="j_username"/></td>
      </tr>
      <tr>
         <td>密码2：</td>
         <td><input type="password" name="j_password"/></td>
      </tr>
      <tr>
         <td colspan="2" align="center">
             <input type="submit" value="登录"/>
             <input type="reset" value="重置"/>
         </td>
      </tr>
   </table>
</form>
</body>
</html>