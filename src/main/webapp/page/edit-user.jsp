<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
table th{text-align: left;}
</style>
<script type="text/javascript">
	$(function(){
		$("#list").attr("class","");
		$("#info").attr("class","active");
	});
</script>
<title>编辑信息</title>
</head>
<body>
<div class="container-fluid" >
<div class="row span7 offset2">
<s:form action="/save-user.action" theme="simple" id="userForm" method="post" cssClass="form-vertical">
<table>
	<tr>
		<th>用户名</th>
		<th>密码</th>
	</tr>
	<tr>
		<td><s:textfield name="user.username"  /></td>
		<td><s:textfield name="user.password"  /></td>
	</tr>
		<tr>
		<th>姓</th>
		<th>名</th>
	</tr>
	<tr>
		<td><s:textfield name="user.firstName"/></td>
		<td><s:textfield name="user.lastName"  /></td>
	</tr>
	<tr>
		<th>Email</th>
		<th>电话</th>
	</tr>
	<tr>
		<td><s:textfield name="user.email"  /></td>
		<td><s:textfield name="user.phoneNumber"  /></td>
	</tr>
	<tr>
		<th colspan="2">网址</th>
	</tr>
	<tr>
		<%-- <td colspan="2"><s:textfield name="user.website"  /></td> --%>
	</tr>
	<tr>
		<td colspan="2">
			<input type="text" name="user.address.province" placeholder="省" class="span2" value="<s:property value='%{#user.address.province}'/>">
			<input type="text" name="user.address.city" placeholder="城市" class="span2" value="<s:property value='%{#user.address.city}'/>">
			<input type="text" name="user.address.postalCode" placeholder="邮编" class="span2" value="<s:property value='%{#user.address.postalCode}'/>">
			<input type="text" name="user.address.country" placeholder="国家" class="span2" value="<s:property value='%{%{#user.address.country}'/>">
			<input type="text" name="user.address.address" placeholder="具体住址" class="span2" value="<s:property value='%{#user.address.address}'/>">
		</td>
	</tr>
</table>
<s:hidden name="user.id" />
<s:hidden name="user.version" />
    <div class="form-actions">
            <button class="btn btn-primary" type="submit">保存</button>
    </div>
</s:form>
</div>
</div>
</body>
</html>