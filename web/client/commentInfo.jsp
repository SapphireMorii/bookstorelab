<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%--"http://www.w3.org/TR/html4/loose.dtd"--%>
<html>
<head>
<title>电子书城_评论信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/client/myAccount.jsp">我的账户</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/findCommentByUser">我的评论</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;评论详细信息
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<p>订单编号:${order.id}</p>
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td bgcolor="#A3E6DF" class="tableopentd01" width="10%">序号</td>
													<td bgcolor="#A3D7E6" class="tableopentd01" width="40%">商品名称</td>
													<td bgcolor="#A3B6E6" class="tableopentd01" width="10%">价格</td>
													<td bgcolor="#A3D7E6" class="tableopentd01" width="10%">数量</td>
													<td bgcolor="#A3B6E6" class="tableopentd01" width="10%">小计</td>
													<c:if test="${commentState != 1 }">	
													<td bgcolor="#A3E2E6" class="tableopentd01" width="10%">评论</td>
													</c:if>
												</tr>
											</table> 
											<c:forEach items="${order.orderItems}" var="item" varStatus="vs">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td class="tableopentd02" width="10%">${vs.count }</td>
														<td class="tableopentd02" width="40%">${item.p.name}</td>
														<td class="tableopentd02" width="10%">${item.p.price }</td>
														<td class="tableopentd02" width="10%">${item.buynum }</td>
														<td class="tableopentd02" width="10%">${item.buynum*item.p.price }</td>
														<c:if test="${commentState != 1 }">											
														<td width="10%" class="tableopentd03">
															<a href="${pageContext.request.contextPath}/client/commentEdit.jsp?oid=${order.id}&&pid=${item.p.id }">评论</a>&nbsp;&nbsp;
														</td>
														
														</c:if>
													</tr>
												</table>
											</c:forEach>
											
											<p>
												收货地址：${order.receiverAddress }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order.receiverName }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												联系方式：${order.receiverPhone }&nbsp;&nbsp;&nbsp;&nbsp;
											</p>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>