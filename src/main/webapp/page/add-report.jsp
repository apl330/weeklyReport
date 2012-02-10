<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/themes/default/easyui.css" type="text/css"
	rel="stylesheet">
<link href="styles/themes/icon.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<title>添加周报</title>
<script type="text/javascript">
	$().ready(function(e) {
		$('#fromDate, #toDate').datebox({
			required : true
		});
		
		$("#draft").click(function(){
			$.ajax({
				type : 'POST',
				url : "save-draft.action",
				async : true,
				cache : false,
				data : $("#reportForm").serialize(),
				error : function(html) {
					alert("提交数据失败，代码:" + html.status + "，请稍候再试");
				}
			});
		});
	});
</script>
</head>
<body>

	<div class="clearfix span8">
		<s:form cssClass="form-horizontal" action="commit-report.action" theme="simple" id="reportForm">
			<fieldset>
				<legend>添加周报</legend>
				<div class="control-group">
					<label class="control-label">项目名</label>
					<div class="controls">
						<s:textfield id="projectName" name="report.projectName"
							cssClass="input-xlarge" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">地点</label>
					<div class="controls">
						<s:textfield rows="5" id="workPlace" name="report.workPlace"
							cssClass="input-xlarge" />
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
				<input type="hidden" name="reportId" value="${#request.reportId }"
					id="reportId" />
				<div class="form-actions">
					<button class="btn btn-primary"  type="submit" id="commit">呈报</button>
					<button class="btn" type="reset">清空</button>
					<a class="btn"  id="draft"  >存为草稿</a>
				</div>
			</fieldset>
		</s:form>
	</div>
</body>

</html>