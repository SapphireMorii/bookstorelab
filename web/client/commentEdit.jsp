<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%--"http://www.w3.org/TR/html4/loose.dtd"--%>
<html>
<head>
<title>编写评论内容</title>
<%--导入css和js --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css"/>
</head>
<body>
	<p:user/>
	<!-- 1.网上书城顶部 start -->
       <%@include file="head.jsp"%>
	<!-- 网上书城顶部  end -->
	<!--2. 网上书城菜单列表  start -->
       <%@include file="menu_search.jsp" %>
	<!-- 网上书城菜单列表  end -->
	<!-- 3.网上书城用户评论  start -->
	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/changeCommentState" method="get"  enctype="multipart/form-data">	
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding: 30px"><h1>编辑评论</h1>
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td>
								订单号：<INPUT TYPE="text" NAME="orderid" value="${param.oid}">
								商品号：<INPUT TYPE="text" NAME="productid" value="${param.pid}">
								</td>
							</tr>
						</table>
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align: right">评论内容：</td>
								<td colspan="2">
								
								<textarea class="textarea" name="scontent"></textarea>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>


						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top: 20px; text-align: center">
									<input type="image" src="images/signup.gif" name="submit" border="0" width="140" height="35"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 网上书城用户注册  end -->
	<!--4. 网上书城下方显示 start -->
     <%@ include file="foot.jsp" %>
	<!-- 网上书城下方显示 end -->
</body>
</html>