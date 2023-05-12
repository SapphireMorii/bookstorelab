<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<%--"http://www.w3.org/TR/html4/loose.dtd"--%>
<html>
<head>
<title>我的收藏</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
<script>
	//删除收藏夹中的商品
	function collect_del() {   
	    var msg = "您确定要取消收藏该商品吗？";   
	    if (confirm(msg)==true){   
	    return true;   
	    }else{   
	    return false;   
    }   
}  
</script>
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
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;我的收藏
					</div>
					<hr>亲，您共收藏了<a>${pbc.totalCount}</a>本书<hr>
					<table cellspacing="0" class="booklist">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/client/ad/page_ad.jpg" width="900" height="89" />
								<!--  -->
								<!-- 循环输出商品信息 -->							
									<table cellspacing="0" class="booklist">
										<tr>
										<c:forEach items="${pbc.cs}" var="entry" varStatus="vs">
											<td width="30%">
												<div class="divbookpic">
													<p>
														<img src="${pageContext.request.contextPath}${entry.product.imgurl}" width="160" height="200" border="0" />
													</p>
												</div>
												<div class="divlisttitle">
													书名： ${entry.product.name }<br />售价：￥${entry.product.price } 
												</div>
												<div>
													<!-- 购买按钮 -->
													<a href="${pageContext.request.contextPath}/addCart?id=${entry.product.id}">
															<img src="${pageContext.request.contextPath }/client/images/buybutton.gif" border="0" width="80" height="25" />
													</a>
													<!-- 取消收藏按钮 -->
													<a href="${pageContext.request.contextPath}/changeCollect?id=${entry.product.id}"
													style="color:#FF0000; font-weight:bold" onclick="javascript:return collect_del()">
														<img src="${pageContext.request.contextPath }/client/images/cancelcollectbutton.gif" border="0" width="80" height="25" />
													</a>
												</div>
											</td>
											</c:forEach>
										</tr>									
									</table>								
								<!--  -->
								<div class="pagination">
									<ul>
										<c:if test="${pbc.currentPage!=1}">
											<li class="disablepage_p">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showCollection?currentPage=${pbc.currentPage-1}"></a>
											</li>
										</c:if>
										<c:if test="${pbc.currentPage==1}">
											<li class="disablepage_p2"></li>
										</c:if>
										
										<c:forEach begin="1" end="${pbc.totalPage}" var="pageNum">
											<c:if test="${pageNum==pbc.currentPage}">
												<li class="currentpage">${pageNum }</li>
											</c:if>
											<c:if test="${pageNum!=pbc.currentPage}">
												<li><a href="${pageContext.request.contextPath}/showCollection?currentPage=${pageNum}">${pageNum}</a>
												</li>
											</c:if>
										</c:forEach>

										<c:if test="${pbc.currentPage==pbc.totalPage||pbc.totalPage==0}">
											<li class="disablepage_n2"></li>
										</c:if>
										<c:if test="${pbc.currentPage!=pbc.totalPage&&pb.totalPage!=0}">
											<li class="disablepage_n">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showCollection?currentPage=${pbc.currentPage+1}"></a>
											</li>
										</c:if>
									</ul>
								</div>
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