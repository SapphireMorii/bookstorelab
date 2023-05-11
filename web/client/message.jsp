<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>电子书城</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<script type="text/javascript">
	//删除订单
	function o_del() {   
	    var msg = "您确定要删除该订单吗？";   
	    if (confirm(msg)==true){   
	    return true;   
	    }else{   
	    return false;   
	    }   
	} 
	</script>
</head>
<body class="main">
	<!-- 使用了自定义标签 -->
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0" style="margin-top:30px">
						<tr>
							<td class="listtitle">我的帐户</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon5.png" width="15" height="10" />
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath }/client/modifyuserinfo.jsp">用户信息修改</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon8.png" width="15" height="10" />
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/findOrderByUser">订单查询</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon6.png" width="15" height="10" />
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/FindScoreByIdServlet">积分查询</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon4.png" width="15" height="10" />
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/findCommentByUser">我的评论</a>
							</td>
						</tr>
						<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon7.png" width="15" height="10" />
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/findMessageBynameServlet">我的留言</a>
						</td>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/icon9.png" width="15" height="10" />
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/logout">用户退出</a>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/client/myAccount.jsp">&nbsp;我的帐户</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
						留言查询
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td style="padding:20px"><p><b>我的留言</b></p>
								
								<table width="100%" border="0" cellspacing="0" class="tableopen">
									<tr>
			
										<td bgcolor="#A3D7E6" class="tableopentd01">留言标题</td>
										<td bgcolor="#A3CCE6" class="tableopentd01">留言内容</td>
										<td bgcolor="#A3B6E6" class="tableopentd01">回复</td>
									
									</tr>
									<c:forEach items="${mess}" var="m">
										<tr>
											<td class="tableopentd02">${m.title}</td>
											<td class="tableopentd02">${m.content}</td>
											<td class="tableopentd02">${m.replycontent}</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="foot.jsp" %>
</body>
</html>