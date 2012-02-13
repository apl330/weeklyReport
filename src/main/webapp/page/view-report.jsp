<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		$().ready(function(){
			$("#comment").click(function(){
				$("#content").show();
				$("#comment").hide();
			});
			$("#cancel").click(function(){
				$("#content").hide();
				$("#comment").show();
			});
			
		});
	</script>
	<title> 查看 </title>
</head>
<body>
  <div class="clearfix">
  <s:set id="commented" value="commented"/>
    <s:if test="#commented == false">
  <div  class="span5">
	  <button id="comment"  class="btn" type="button" >评阅</button>
  </div>
    </s:if>
 	<table class="table-bordered span8">
 		<tbody>
 			<tr>
 			<th class="span2">项目名</th>
 			<td class="span4"><s:property value="report.projectName"/></td>
 			<th>呈报</th>
 			<td><s:property value="report.author.lastName"/> &nbsp;&nbsp; <s:date name="report.submitDate"  format="yyyy-MM-dd"/></td>
 			</tr>
 			<tr>
 				<th>工作日期</th>
 				<td colspan="3">
 				<s:date name="report.fromDate" format="yyyy-MM-dd"/> 至 <s:date name="report.toDate" format="yyyy-MM-dd"/>
 				</td>
 			</tr>
 			<tr>
 				<th>工作内容</th>
 				<td colspan="3">
 					<s:property value="report.doneWorks" escape="false" />
 				</td>
 			</tr>
 			<tr>
 				<th>下周计划</th>
 				<td colspan="3">
 					<s:property value="report.toDoWorks" escape="false" />
 				</td>
 			</tr>
 			<tr>
 				<th>备注</th>
 				<td colspan="3">
 					<s:property value="report.memo" escape="false" />
 				</td>
 			</tr>
 			<tr id="content" style="display: none;">
 				<th>&nbsp;</th>
 				<td colspan="3">
	 				<s:form theme="simple" action="save-comment.action" id="commentForm" method="post">
	 						<input type="hidden" name="reportId" value="<s:property value='report.id'/>"/>
	 					    <s:textarea theme="simple" id="txtCom" name="commen"  cols="4" rows="4" />
	 						<button class="btn btn-primary" id="btnCom" type="submit">确定</button>
	 						<button class="btn" id="cancel">取消</button>
	 				</s:form>
 				</td>
 			</tr>
 			
 			<s:if test="#commented == true">
 			<tr>
 				<th>评语</th>
 				<td colspan="3">
 					<p><s:property value="report.comment" escape="false" /></p>
 					<s:date name="report.commentDate" format="yyyy-MM-dd"/>
 				</td>
 			</tr>
 			</s:if>
 		</tbody>
	</table>
  </div>
</body>
</html>