<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">

<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
  </head>
  
  <script type="text/javascript">
  function setMethod(method){
	  var ele=document.getElementById("method");
	  ele.value=method;
  }
  </script>
  
  <body>
  <div>
    <img src="<c:url value='/${book.image }'/>" border="0"/>
  </div>
  <form style="margin:20px;" id="form" action="<c:url value='/BookServlet'></c:url>" method="post">
  	<input type="hidden" name="method" id="method"/>
  	<input type="hidden" name="bid" value="${book.bid }" />
  	<input type="hidden" name="image" value="${book.image }" />
  	图书名称：<input type="text" name="bname" value="${book.bname }"/><br/>
  	图书出版社：<input type="text" name="press" value="${book.press }"/><br/>
  	图书分类：<select style="width: 150px; height: 20px;" name="cid">
  			<c:forEach items="${categoryList }" var="c">
     		<option value="${c.cid }" <c:if test="${c.cid eq book.category.cid }">selected="selected"</c:if>>${c.cname }</option>
    		</c:forEach>
    	</select><br/>
  	图示状态：<c:choose>
			<c:when test="${book.status eq 0 }">
				未借出<a href="<c:url value='/BookServlet?method=borrow&oid=${book.bid }'/>">点击这里借出</a>
			</c:when>
			<c:when test="${book.status eq 1 }">
				借出<a href="<c:url value='/BookServlet?method=borrow&oid=${book.bid }'/>">点击这里归还</a>
			</c:when>
		</c:choose><br/>
  	<input type="submit" value="删除" onclick="setMethod('delete')"/>
  	<input type="submit" value="修改" onclick="setMethod('modify')"/>
  </form>
  </body>
</html>
