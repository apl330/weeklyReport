<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="styles/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="styles/themes/icon.css"> 
<script type="text/javascript" src="js/locale/easyui-lang-zh_TW.js"  charset="utf-8" ></script>
	<script type="text/javascript">
	
	$(function(){
		
		
		$(".datebox").datebox({
			formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
		});	
	});
	</script>
	<title> 周报列表 </title>
</head>
<body>


<div class="container-fluid">
<div class="row span11">
<!-- 搜索框 -->
<s:form theme="simple" action="report-list.action" cssClass="form-search">
			  <label>
			  		项目名&nbsp;<input type="text" class="search-query" name="projectName"/>
			  </label>
			  
			  &nbsp;
			  <label >
					呈报人&nbsp;<input type="text" class="input-mini  search-query" name="userRealName">
			  </label>
			  
			  &nbsp;
			  <label>
			  		时间范围&nbsp;<input type="text"  class="input-mini   datebox" name="fromDate" style="width:90px;"/>&nbsp;至&nbsp;
			  					<input type="text"   class="input-mini  datebox" name="toDate"  style="width:90px;"/>
			  </label>
			  
			  &nbsp;
              <label class="checkbox">
                <input type="checkbox" checked="" value="false" id="optionsRadios1"  name="read">
                未审阅
              </label>
              &nbsp;
              <input name="search" type="hidden" value="true"/>
   			  <button type="submit" class="btn">搜索</button>
</s:form>
</div>


<!-- 周报显示 -->
<div	class="row span10">
		<display:table name="reports" requestURI="/report-list.action"  id="report" pagesize="15" class="table" export="true">
		<s:set id="DRAFT" value="%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_DRAFT}" />
		<display:column style="width:80px;" property="submitDate" title="呈报日期" format="{0,date,yyyy-MM-dd}" sortable="true"/>
		<display:column style="width:80px;"   title="呈报人" sortable="true">
			<s:property value="%{#attr['report'].author.firstName}"/><s:property value="%{#attr['report'].author.lastName}"/>
		</display:column>
		<display:column  title="项目" sortable="true" style="width:150px;">
			 <s:if test="#attr['report'].status == #DRAFT">
			 	<a href="add-report.action?reportId=${report.id}">${report.projectName}</a>
			 </s:if>
			 <s:else>
			 	<a href="view-report.action?reportId=${report.id}">${report.projectName}</a>
			 </s:else>
		</display:column>
		<display:column property="workPlace" title="工作地点"  sortable="true"/>
		<display:column style="width:50px;" property="statusString" title="状态"  sortable="true"/>
	</display:table>
</div>
</div>

</body>
</html>