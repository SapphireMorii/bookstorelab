<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />

	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
</head>
<body>
	<br />
	<form id="Form1" name="Form1" action="" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>查 询 条 件</strong>
					</td>
				</tr>
				
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请输入年份
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="year" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请选择月份
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<select name="month" id="month">
										<option value="0">--选择月份--</option>
										<option value="1">一月</option>
										<option value="2">二月</option>
										<option value="3">三月</option>
										<option value="4">四月</option>
										<option value="5">五月</option>
										<option value="6">六月</option>
										<option value="7">七月</option>
										<option value="8">八月</option>
										<option value="9">九月</option>
										<option value="10">十月</option>
										<option value="11">十一月</option>
										<option value="12">十二月</option>
									</select>
								</td>
							</tr>
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请输入商品名：
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="name" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									请输入商品类型：
								</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="category" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td align="center" bgColor="#ffffff" class="ta_01" colspan="3">
									
								</td>
							</tr>
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
								</td>
								<td class="ta_01" bgColor="#ffffff">
									
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
								
								</td>
								
								<td align="center" bgColor="#ffffff" class="ta_01" colspan="3">
									<input type="button" type="submit" id="download" name="download" value="下载" class="button_view" onclick="Form1.action='${pageContext.request.contextPath}/download';Form1.submit();"/> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="button" type="submit" id="search" name="search" value="查询" class="button_view" onclick="Form1.action='${pageContext.request.contextPath}/FindSellByManyConditions';Form1.submit();"/> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="reset" name="reset" value="重置" class="button_view" />
								</td>
							</tr>
							<tr>
								<td class="ta_01" align="center" bgColor="#afd1f3" colspan="7">
									<strong>销售列表</strong>
								</td>
							</tr>
							<tr>
								<td class="ta_01" align="right"></td>
							</tr>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="30%">商品编号</td>
								<td align="center" width="18%">商品名称</td>
								<td align="center" width="9%">商品价格</td>
								<td align="center" width="9%">库存量</td>
								<td align="center" width="9%">商品销量</td>
								<td width="8%" align="center">商品类别</td>
							</tr>
                            <!--  循环输出所有商品 -->
							<c:forEach items="${map}" var="m">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${m.key.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${m.key.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${m.key.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${m.key.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${m.value }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">${m.key.category}</td>
								</tr>
							</c:forEach>
						</table>
						
					</td>
					<tr>
						<c:if test="${display==1}">
							<div style="margin-top:20px; margin-bottom:5px">
								<img src="${pageContext.request.contextPath}/admin/images/a.png?randomNum=${randomNum}"  align="center"/>
							</div>
						</c:if>
					</tr>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>