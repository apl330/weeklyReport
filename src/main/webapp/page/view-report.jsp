<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style type="text/css">
		.table th {
		    font-weight: bold;
		    vertical-align: top;
		}
	</style>
	<title> 查看 </title>
</head>
<body>
  <div class="row">
  <s:set id="commented" value="commented"/>

    <div class="span11">
 	<table class="table" >
 		<caption><b>周报内容</b></caption>
 		<tbody>
 			<tr>
 			<th class="span2">项目名</th>
 			<td class="span4"><s:property value="report.projectName"/></td>
 			<th>呈报</th>
 			<td><s:property value="report.author.firstName"/><s:property value="report.author.lastName"/> &nbsp;&nbsp; <s:date name="report.submitDate"  format="yyyy-MM-dd"/></td>
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
 			<s:if test="#commented == true">
 			<tr>
 				<th>评语</th>
 				<td colspan="3">
 					<p><s:property value="report.comment" escape="false" /></p>
 					<s:date name="report.commentDate" format="yyyy-MM-dd"/>
 				</td>
 			</tr>
 			</s:if>
 			<s:else>
 			<tr>
 				<th>评语</th>
 				<td colspan="3">
 					<security:authorize ifAnyGranted="ROLE_HEAD"> 
					    <s:if test="#commented == false">
						 		<s:form theme="simple" action="save-comment.action" id="commentForm" method="post">
						 				<div >
						 				<input type="hidden" name="reportId" value="<s:property value='report.id'/>"/>
						 				<s:textarea theme="simple" id="txtCom" name="commen" cols="50" cssClass="span6" rows="2"  />
						 				<button class="btn" id="btnCom" type="submit">审阅</button>
						 				</div>
						 	 </s:form>
					    </s:if>
					</security:authorize>
 				</td>
 			</tr>
 			</s:else>
 			<tr>
 				<th >附件</th>
 				<td colspan="3">
	 			<s:iterator value="attachments" id="at">
	 				<p>
	 				<a href="${basePath }/dowload-attachment.action?id=<s:property value='#at.id'/>" > <s:property value="#at.fileName"/> </a>
	 				</p>
 				</s:iterator>
 				</td>
 			</tr>
 			
 		</tbody>
	</table>
	</div>
	
	<div class="span12">
	<table class="table">
		<caption><b>车辆使用情况(总共)</b></caption>
		<thead>
			<th class="span2">车牌号</th>
			<th class="span1">司机</th>
			<th class="span2">使用日期</th>
			<th class="span2">开始里程</th>
			<th class="span2">结束里程</th>
			<th class="span2">起始地点</th>
			<th class="span2">到达地点</th>
		</thead>
		<tbody>
			<s:iterator value="report.vehicleUsages" id="ve">
				<tr>
					<td><s:property value="#ve.licensePlateNumber"/></td>
					<td><s:property value="#ve.driver"/></td>
					<td><s:property value="#ve.fromDate"/></td>
					<td><s:property value="#ve.startMileage"/></td>
					<td><s:property value="#ve.endMileage"/></td>
					<td><s:property value="#ve.fromPlace"/></td>
					<td><s:property value="#ve.toPlace"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
  </div>

  
</body>
</html>