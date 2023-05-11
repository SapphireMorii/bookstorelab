<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>评论展示</title>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/web/admin/css/Style.css"
		type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<script language="javascript"
		src="${pageContext.request.contextPath}/web/admin/js/public.js"></script>
	<script language="javascript"
		src="${pageContext.request.contextPath}/web/admin/js/check.js"></script>
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
		<div style="text-align:right; margin:5px 10px 5px 0px">
			<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/client/myAccount.jsp">我的账户</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/findCommentByUser">我的评论</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;评论内容展示
		</div>
		<table cellSpacing="1" cellPadding="5" width="80%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">		
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
					<strong>我的所有评论</strong>
				</td>
			</tr>
			
			<c:forEach items="${map}" var="map">
			<c:if test="${map.key.content!=null}">
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
					<input type="text" name="title" class="bg" maxlength="10"  value="${map.value.name }"/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">评论内容：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
					<textarea name="details" cols="30" rows="3" style="WIDTH: 96%" >${map.key.content }</textarea>
				</td>
			</tr>
			</c:if>
			</c:forEach>
		</table>
	<%@ include file="foot.jsp" %>
</body>
</html>