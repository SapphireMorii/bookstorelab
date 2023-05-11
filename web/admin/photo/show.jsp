<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script type="text/javascript">
	    //添加商品
		function addPicture() {
			window.location.href = "photo.jsp";
		}
		//删除商品
		function p_del() {   
			var msg = "您确定要删除该图片吗？";   
			if (confirm(msg)==true){   
			return true;   
			}else{   
			return false;   
			}   
		}  
		//确定设置成轮播的图片？
		function p_edit() {   
			var msg = "您确定设置成换轮播图片吗？";   
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
			<TBODY>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe" class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff">
									<font face="宋体" color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<br /><br />
								</td>
								<!-- <td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search" value="&#26597;&#35810;" class="button_view">
										&#26597;&#35810;
									</button> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="reset" name="reset" value="&#37325;&#32622;" class="button_view" />
								</td> -->
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong> 图 片 列 表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="&#28155;&#21152;" class="button_add" onclick="addPicture()">
							&#28155;&#21152;
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="24%">图片编号</td>
								<td align="center" width="24%">图片内容</td>
								<td align="center" width="18%">设置轮播</td>
								<td align="center" width="18%">图片删除</td>
							</tr>
                            <!--  循环输出所有商品 -->
							<c:forEach items="${pt}" var="p">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="200">${p.id }</td>
									
									
									<td align="center" style="HEIGHT: 22px" width="7%">
									    
											<img src="${pageContext.request.contextPath}/${p.img}" width="100" height="50"> 
										
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<a href="${pageContext.request.contextPath}/manager/EditPhotoServlet?id=${p.id}" onclick="javascript:return p_edit()">
												<img src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
												width="16" height="16" border="0" style="CURSOR: hand">
										</a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<a href="${pageContext.request.contextPath}/manager/DeletePhotoServlet?id=${p.id}" onclick="javascript:return p_del()">
												<img src="${pageContext.request.contextPath}/admin/images/i_del.gif"
												width="16" height="16" border="0" style="CURSOR: hand">
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