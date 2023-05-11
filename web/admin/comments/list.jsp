<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>评论管理</title>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css"
		rel="stylesheet" type="text/css" />
	<script language="javascript"
		src="${pageContext.request.contextPath}/admin/js/public.js"></script>
</head>
<body>
<br />
	<form id="Form1" name="Form1" action="" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
					    <strong>评 论 列 表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="12%">评论编号</td>
								<td align="center" width="12%">评论商品</td>
								<td align="center" width="12%">评论用户</td>
								<td align="center" width="24%">评论内容</td>
								<td width="8%" align="center">删除</td>
							</tr>
							<c:forEach items="${map}" var="map">
							<c:if test="${map.value[1]!=null}">
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23">${map.value[0] }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${map.value[3] }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${map.value[2] }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${map.value[1] }</td>
									
									<td align="center" style="HEIGHT: 22px" width="7%">
										<a href="${pageContext.request.contextPath}/DeleteCommentServlet?id=${map.value[0]}">
											<img src="${pageContext.request.contextPath}/admin/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
										</a>
									</td>
								</tr>
							</c:if>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</html>