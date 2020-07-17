<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pager.jsp' starting page</title>
    
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
  
    <table border=1 bgColor="pink">
    		<tr>
    			<td>
		    		<input type="button" value="去首页" onclick="queryList(1)">
		    	</td>
		    	<td>
		    		<c:if test="${p.pageNow > 1}">
			    		<input type="button" value="上一页" onclick="queryList(${p.pageNow-1})">
		    		</c:if>
		    		<c:if test="${p.pageNow <= 1}">
			    		<input type="button" value="上一页" onclick="alert('没有上一页了！')">
		    		</c:if>
		    	</td>
		    	<td>
		    		当前页数：${p.pageNow}
		    	</td>
		    	<td>
		    		总页数：${p.pageCount}
		    	</td>
		    	<td>
		    		总条数：${p.totalCount}
		    	</td>
		    	<td>
			    	跳转到：<input type="number" style="width:40" id="toPageNumber">页
			    	<input value="跳转" type="button" onclick="toPage(${p.pageCount})">
		    	</td>
		    	<td>
		    		<c:if test="${p.pageNow < p.pageCount}">
			    		<input type="button" value="下一页" onclick="queryList(${p.pageNow+1})">
		    		</c:if>
		    		<c:if test="${p.pageNow >= p.pageCount}">
			    		<input type="button" value="下一页" onclick="alert('没有下一页了！')">
		    		</c:if>
		    	</td>
		    	<td>
		    		<input type="button" value="去尾页" onclick="queryList(${p.pageCount})">
		    	</td>
	    	</tr>
    	</table>
  </body>
  <script type="text/javascript">		
	function toPage(pageCount){
			var pageNumber = document.getElementById("toPageNumber").value;
			if(pageNumber<=0 || pageNumber>pageCount){
			alert("页面不存在");
			}else{
				queryList(pageNumber);
			}
	}
  </script>
</html>
