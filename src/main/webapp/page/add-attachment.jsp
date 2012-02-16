<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/bootstrap.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#attaForm").submit(function(){
				var up = $("#upload").val();
				if(up==null || up == ""){
					alert("附件不能为空!");
					return false;
				}else{
					return true;
				}
			});
		});
	</script>
<title>Insert title here</title>
</head>
<body>
<table style="width:300px;">
	<thead>
		<tr>
			<th style="width:150px;">附件名</td>
			<th>&nbsp;</td>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="attas" id="at">
		<tr>
			<td><s:property value="#at.fileName"/></td>
			<td><a href="remove-attachment.action?id=<s:property value='#at.id'/>&reportId=<s:property value='#at.report.id'/>">删除</a></td>
		</tr>
	</s:iterator>
	</tbody>
</table>

	<div id="fileForm">
			<s:form enctype="multipart/form-data" cssClass="form-horizontal" action="save-attachment.action" theme="simple" id="attaForm">
			<div id="fields">
			<input id="upload" name="upload" type="file"/>			
			<input type="hidden"  id="atReportId" name="reportId"  class="reportId" value="${param.reportId }"/>
			</div>
			<p><button class="btn" type="submit">提交</button></p>
			</s:form>
	</div>
</body>
</html>