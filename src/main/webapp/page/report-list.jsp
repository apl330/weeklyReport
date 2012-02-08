<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
	工作周报
	</title>
</head>
<body>
  <div class="clearfix">
<display:table name="reports" cellspacing="0" cellpadding="0" requestURI="/report-list.action"  id="report" pagesize="15" class="table" export="true">
	<display:setProperty name="paging.banner.item_name" value="周报"/>
	<display:setProperty name="paging.banner.items_name" value="周报"/>
	<display:column property="submitDate" title="呈报日期" format="{0,date,yyyy-MM-dd}" sortable="true"/>
	<%-- <display:column property="author.lastName" title="呈报人" sortable="true"/> --%>
	<display:column property="projectName" title="项目" sortable="true"/>
	<display:column property="workPlace" title="工作地点"  sortable="true"/>
	<display:column property="statusString" title="状态"  sortable="true"/>
	<display:column href="view_report.action" paramId="reportId" paramProperty="id" title="查看">查看</display:column>
</display:table>
	</div>
</body>
</html>