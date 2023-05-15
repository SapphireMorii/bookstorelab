<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css"
		rel="stylesheet" type="text/css" />
	<script language="javascript"
		src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script>
	//删除购物车中的商品
	function message_del() {   
	    var msg = "您确定要删除该留言吗？";   
	    if (confirm(msg)==true){   
	    return true;   
	    }else{   
	    return false;   
	    }   
	}   
</script>
</HEAD>
<body>
	<br />
	<form id="Form1" name="Form1" action="" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
					    <strong>留言 列 表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="12%">留言编号</td>
								<td align="center" width="12%">留言标题</td>
								<td align="center" width="24%">留言内容</td>
								<td align="center" width="8%">用户名</td>
								<td align="center" width="8%">联系方式</td>
								<td width="8%" align="center">回复</td>
								<td width="8%" align="center">删除</td>
							</tr>
							<c:forEach items="${messages}" var="n">
								<tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23">${n.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${n.title }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${n.content }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${n.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${n.telephone}</td>	
										<!-- 回复 -->
									<td align="center" style="HEIGHT: 22px" width="7%">
										<a href="${pageContext.request.contextPath}/FindByIdMessageServlet?id=${n.id}">
											<img src="${pageContext.request.contextPath}/admin/images/i_edit.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
									<!-- 删除 -->
									<td align="center" style="HEIGHT: 22px" width="7%">
										<a href="${pageContext.request.contextPath}/DeleteMessageServlet?id=${n.id}"  onclick="javascript:return message_del()">
											<img src="${pageContext.request.contextPath}/admin/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
										</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>