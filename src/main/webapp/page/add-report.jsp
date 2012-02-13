<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/themes/default/easyui.css" type="text/css"
	rel="stylesheet" />
<link href="styles/themes/icon.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>

<title>添加周报</title>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#fromDate, #toDate').datebox({
							required : true
						});

						$("#draft").click(function() {
									$.getJSON("save-report.action", $(
											"#reportForm").serialize(),
											function(data) {
												$("#version").attr("value", data.report.version);
												$("#reportId").attr("value", data.report.id);
											});
					 });

						$("#commit").mouseover(function() {
											$("#status") .attr( "value", "<s:property value='%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_SUBMITTED}'/>");
						});
						$("#draft").mouseover(function() {
							$("#status").attr("value","<s:property value='%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_DRAFT}'/>");
						});

						$("#addVehicleUsage").click(function() {
									$("#vehicleUsages").append($("#vu").clone().removeAttr("id"));
						});
						
						$("#addAttach").click(function(){
							$("#attachments").append($("#am").clone().removeAttr("id"));
						});
						
					});
				function del(self){
					$(self).parent().remove();
				}
</script>
</head>
<body>

	<div class="clearfix span12">
		<s:form enctype="multipart/form-data" cssClass="form-horizontal"
			action="save-report.action" theme="simple" id="reportForm"
			method="post">
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

				<div class="control-group">
					<label class="control-label"><button type="button" id="addVehicleUsage" class="btn">添加车辆信息</button></label>
					<div class="controls" id="vehicleUsages">
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">
					<button type="button" id="addAttach" class="btn">添加附件</button></label>
					<div class="controls" id="attachments">
					<s:file  name="upload"/>
					</div>
				</div>

				<s:hidden name="status" value="%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_SUBMITTED}" id="status" />
				<s:hidden id="reportId" name="report.id" />
				<s:hidden id="version" name="report.version" />
				<div class="form-actions">
					<button class="btn btn-primary" type="submit" id="commit">呈报</button>
					<button class="btn" type="reset">清空</button>
					<button class="btn" type="button" id="draft">存为草稿</button>
				</div>
			</fieldset>
		</s:form>
	</div>
	<!-- 重要: 页面模板 -->
	<div style="display: none">
		<div id="vu" class="vehicleUsageTemp">
			<p>
					<input placeholder="车牌号" type="text" name="report.vehicleUsage.licensePlateNumber" class="span2" />
					<input placeholder="司机" type="text" name="report.vehicleUsage.driver" class="span1" />
					<input placeholder="使用日期" type="text" name="report.vehicleUsage.toPlace" class="span2" />
					<input placeholder="开始量程" type="text" name="report.vehicleUsage.startMileage" class="span2" /> 
					<input placeholder="结束量程" type="text" name="report.vehicleUsage.endMileage" class="span2" /><br/>
					<input placeholder="起始地点" type="text" name="report.vehicleUsage.fromPlace" class="span2" />
					<input placeholder="到达地点" type="text" name="report.vehicleUsage.toPlace" class="span2" />
			</p>
		</div>
		<div id="am">
			<p>
				<s:file  name="uploads"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn" onclick="del(this)" type="button">移除</button>
			</p>
		</div>
	</div>
	
		
		
</body>


</html>