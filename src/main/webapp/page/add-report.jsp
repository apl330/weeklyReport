<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="styles/themes/icon.css"> 
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<title>添加周报</title>
<script type="text/javascript">
	$() .ready(function() {
					 
						/* $("#draft").click(function() {
									$.getJSON("save-report.action", $( "#reportForm").serialize(),
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
						}); */
						
						$("#fromDate, #toDate").datebox({
							formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
						});
				
						
					});
				function del(self){
					$(self).parent().remove();
				}
				
				function delRow(self){
					$(self).parent().parent().remove();
				}
				
</script>
</head>
<body>


	<div id="tt" class="easyui-tabs" tools="#tab-tools" >
		<div title="周报内容" tools="#p-tools" style="padding:20px;">
			<p>
	<s:form enctype="multipart/form-data" cssClass="form-horizontal" action="save-report.action" theme="simple" id="reportForm" 	method="post">
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
						<s:textfield id="fromDate" cssStyle="width:125px;" name="report.fromDate" />
						&nbsp;至&nbsp;
						<s:textfield id="toDate" name="report.toDate" cssStyle="width:125px;" />
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
					<s:hidden name="status" value="%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_SUBMITTED}" id="status" />
					<s:hidden id="reportId" name="report.id" />
					<s:hidden id="version" name="report.version" />
			</s:form>
	</p>
		</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<div title="车辆使用" closable="true" style="padding:20px;" cache="false"  >
			<div  id="vehicleUsages-added">
							<table>
							<thead>
								<tr>
									<th>车牌号</th>
									<th>司机</th>
									<th>使用日期</th>
									<th>开始量程</th>
									<th>结束量程</th>
									<th>起始地点</th>
									<th>到达地点</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="report.vehicleUsages" id="vr" >
									<s:set name="ve" value="#vr" />
									<tr>
										<td><s:property /></td>
										<td ><s:textfield theme="simple" value="%{#ve.licensePlateNumber}" name="vehicleUsages.licensePlateNumber" cssClass="span2"  disabled="true"/></td>
										<td ><s:textfield theme="simple" value="%{#ve.driver}" cssClass="span1" disabled="true"/></td>
										<td><s:property value="%{#ve.fromDate}"/><s:hidden name="vehicleUsages.fromDate"/></td>
										<td ><s:textfield theme="simple" value="%{#ve.startMileage}" name="vehicleUsages.startMileage" cssClass="span2" disabled="true"/></td>
										<td><s:textfield theme="simple" value="%{#ve.endMileage}" name="vehicleUsages.endMileage" cssClass="span2" disabled="true"/></td>
										<td ><s:textfield theme="simple" value="%{#ve.fromPlace}" name="vehicleUsages.fromPlace" cssClass="span2" disabled="true"/></td>
										<td><s:textfield theme="simple" value="%{#ve.toPlace}" name="vehicleUsages.toPlace" cssClass="span2" disabled="true"/></td>
										<td><button type="button" onclick="delRow(this)" class="btn"/>移除</td>
									</tr>
								</s:iterator>
							</tbody>
							</table>						
		 </div>
		</div>
		<div title="附件"  closable="true" style="padding:20px;">
		</div>
	</div>
	
	<div id="tab-tools">
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="javascript:alert('add')"></a>
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="javascript:alert('save')"></a>
	</div>
	
		
</body>
</html>