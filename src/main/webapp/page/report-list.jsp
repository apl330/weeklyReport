<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> 周报列表 </title>
</head>
<body>
<div class="clearfix span6">
  <a href="add-report.action" class="btn">添加</a>
</div>
  <div class="clearfix span10">
	<display:table name="reports" requestURI="/report-list.action"  id="report" pagesize="15" class="table" export="true">
		<s:set id="DRAFT" value="%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_DRAFT}" />
		<display:column style="width:80px;" property="submitDate" title="呈报日期" format="{0,date,yyyy-MM-dd}" sortable="true"/>
		<display:column style="width:80px;"   title="呈报人" sortable="true">
			<s:property value="%{#attr['report'].author.firstName}"/><s:property value="%{#attr['report'].author.lastName}"/>
		</display:column>
		<display:column property="projectName" title="项目" sortable="true"/>
		<display:column property="workPlace" title="工作地点"  sortable="true"/>
		<display:column style="width:50px;" property="statusString" title="状态"  sortable="true"/>
		<display:column>
			 <s:if test="#attr['report'].status == #DRAFT">
			 	<a href="add-report.action?reportId=${report.id}">编辑</a>
			 </s:if>
			 <s:else>
			 	<a href="view-report.action?reportId=${report.id}">查看</a>
			 </s:else>
		</display:column>
	</display:table>
	</div>
</body>
</html>