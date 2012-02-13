<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/themes/default/easyui.css" type="text/css"
	rel="stylesheet"/>
<link href="styles/themes/icon.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>

<title>添加周报</title>
<script type="text/javascript">
	$(document).ready(function(){
		$('#fromDate, #toDate').datebox({
			required : true
		});
		 
		$("#draft").click(function(){
			$.getJSON("save-report.action", $("#reportForm").serialize(), function(data){
			 	  $("#version").attr("value",data.report.version);
          	      $("#reportId").attr("value",data.report.id);
			});
		});
		
		$("#commit").mouseover(function(){
			$("#status").attr("value","<s:property value='%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_SUBMITTED}'/>");
		});
		$("#draft").mouseover(function(){
			$("#status").attr("value","<s:property value='%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_DRAFT}'/>");
		});
	});
</script>
</head>
<body>

	<div class="clearfix span8">
		<s:form enctype="multipart/form-data"  cssClass="form-horizontal" action="save-report.action" theme="simple" id="reportForm" method="post">
			<fieldset>
				<legend>添加周报</legend>
				<div class="control-group">
					<label class="control-label">项目名</label>
					<div class="controls">
						<s:textfield id="projectName" name="report.projectName" cssClass="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">地点</label>
					<div class="controls">
						<s:textfield rows="5" id="workPlace" name="report.workPlace" cssClass="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">日期</label>
					<div class="controls">
						<s:textfield id="fromDate" cssStyle="width:125px;"
							name="report.fromDate" />
						&nbsp;至&nbsp;
						<s:textfield id="toDate" name="report.toDate"
							cssStyle="width:125px;" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">工作内容</label>
					<div class="controls">
						<s:textarea rows="5" id="doneWorks" name="report.doneWorks"
							cssClass="span6" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">下周计划</label>
					<div class="controls">
						<s:textarea rows="5" id="toDoWorks" name="report.toDoWorks"
							cssClass="span6" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">其他事宜</label>
					<div class="controls">
						<s:textarea rows="5" id="memo" name="report.memo" cssClass="span6" />
					</div>
				</div>
				<div class="control-group">
				<div id="upMessage" style="display: none;">保存成功！</div>  
				</div>
				<%-- <input type="hidden" name="reportId" id="report1" value="${param.reportId}" /> --%>
				<s:hidden name="status" 	value="%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_SUBMITTED}" 	id="status" />
				<s:hidden id="reportId" name="report.id" />
				<s:hidden id="version" name="report.version"/>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit" id="commit">呈报</button>
					<button class="btn" type="reset">清空</button>
					<button class="btn" type="button" id="draft">存为草稿</button>
				</div>
			</fieldset>
		</s:form>
	</div>
</body>

</html>