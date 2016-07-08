<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>index</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>

  	<form action="saveuser.shtml"  method="post">
  	<table>
  		<tr>
  			 
  			<th>账号</th>
  			<td><input type="text"  name="userInfo.account" /> </td>
  		</tr>
  		<tr>
  			 
  			<th>密码</th>
  			<td><input type="text"  name="userInfo.password" /></td>
  		</tr>
  		<tr>
  			<td colspan="2"><input type="submit" value="添加"> </td>
  		</tr>
  	</table>
  	</form>
  	
  	<table>
  		<tr>
  			<th>id</th>
  			<th>账号</th>
  			<th>密码</th>
  			<th>功能</th>
  		</tr>
  	<s:iterator value="userList" id="user">
     	<tr>
  			<td><s:property value="#user.id"/></td>
  			<td><s:property value="#user.account"/></td>
  			<td><s:property value="#user.password"/></td>
  			<td><a href="edituser.shtml?userInfo.id=<s:property value="#user.id"/>">修改</a>&nbsp;&nbsp;&nbsp;<a href="deluser.shtml?userInfo.id=<s:property value="#user.id"/>">删除</a> </td>
  		</tr>
     </s:iterator>
  	</table>
    
  </body>
</html>
