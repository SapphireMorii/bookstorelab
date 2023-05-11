<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
		type="text/css" rel="stylesheet">
	<script language="javascript"
		src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script language="javascript"
		src="${pageContext.request.contextPath}/admin/js/check.js"></script>
</HEAD>
<body>
	<form id="userAction_save_do" name="Form1" 
		action="${pageContext.request.contextPath}/manager/replyMessageServlet" method="post">
		<input type="hidden" name="id" value="${message.id}" /> &nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
					<strong>回复信息</strong>
				</td>
			</tr>		
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">信息ID：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
				<input type="text" name="id" class="bg" maxlength="10"  value="${message.id}"/>
				</td>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">信息内容：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
				<textarea name="content" cols="30" rows="3" style="WIDTH: 96%" >${message.content }</textarea>				
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">回复内容：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
					<textarea name="reply" cols="30" rows="3" style="WIDTH: 96%" ></textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
					<input type="submit" class="button_ok" value="确定"> 
					
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> 
					<input class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"> </span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>