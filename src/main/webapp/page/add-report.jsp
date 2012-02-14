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
					 
						$("#draft").click(function() {
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
									$("#vehicleUsages-added").append($("#vu").clone().removeAttr("id"));
						});
						
						$("#addAttach").click(function(){
							$("#attachments").append($("#am").clone().removeAttr("id"));
						}); 
						
						$("#fromDate, #toDate").datebox({
							formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
						});
					
						
					});
	
			
				function del(self){
					$(self).parent().remove();
				}
				//移除车辆使用
				function rmVe(id,self){
					var veId = id;
					$.getJSON("remove-vehicle.action",{veId:veId},function(result){
						if(result.success == true){
							$(self).parent().parent().remove();
						}
					});
				}
				//添加车辆使用
				function saveVehicle(self){
					$(self).parent().submit(function(){
						$.getJSON("save-vehicle.action", this.serialize(), function(result){
							console.log(result.vehicleUsage);
						});
						return false;
					});
				}
				
				
</script>
</head>
<body>


	<div id="tt" class="easyui-tabs" tools="#tab-tools" >
		<div title="周报内容" tools="#p-tools" style="padding:20px;">
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
				<div class="form-actions">
					<button class="btn btn-primary" type="submit" id="commit">呈报</button>
					<button class="btn" type="reset">清空</button>
					<button class="btn" type="button" id="draft">存为草稿</button>
				</div>
			</s:form>
		</div>
		
		
		
		
		
		
		
		
		
		
		<div title="车辆使用"  style="padding:20px;" cache="false"  >
		<div>
			<button class="btn" id="addVehicleUsage">添加</button>
		</div>
		<div  id="vehicleUsages-added">
			<s:if test="report.vehicleUsages == null">
				未使用车辆
			</s:if>
			<s:else>
			<table class="table">
							<thead>
								<tr>
									<th class="span2">车牌号-司机</th>
									<th>使用日期</th>
									<th>开始量程</th>
									<th>结束量程</th>
									<th>起始地点</th>
									<th>到达地点</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="report.vehicleUsages" id="ve" >
									<tr>
										<td><s:property value="#ve.licensePlateNumber" /> - <s:property value="#ve.driver"/></td>
										<td><s:property value="#ve.fromDate"/></td>
										<td><s:property value="#ve.startMileage"/></td>
										<td><s:property value="#ve.endMileage"/></td>
										<td><s:property value="#ve.fromPlace"/></td>
										<td><s:property value="#ve.toPlace"/></td>
										<td><button type="button" onclick="rmVe('<s:property value="#ve.id"/>', this)" class="btn"/>移除</td>
									</tr>
								</s:iterator>
							</tbody>
			</table>						
			</s:else>
		 </div>
		</div>
		
		
		
		<div title="附件"   style="padding:20px;"  cache="false" >
		</div>
	</div>
	<div style="display: none;">
		<div id="vu" class="vehicleUsageTemp">
			<p>
					<s:form theme="simple"  method="post" >
					<input placeholder="车牌号" type="text" name="vehicleUsage.licensePlateNumber" class="span2" />
					<input placeholder="司机" type="text" name="vehicleUsage.driver" class="span1" />
					<input placeholder="使用日期" type="text" name="vehicleUsage.fromDate" class="datebox "/>
					<input placeholder="开始量程" type="text" name="vehicleUsage.startMileage" class="span2" /> 
					<input placeholder="结束量程" type="text" name="vehicleUsage.endMileage" class="span2" /><br/>
					<input placeholder="起始地点" type="text" name="vehicleUsage.fromPlace" class="span2" />
					<input placeholder="到达地点" type="text" name="vehicleUsage.toPlace" class="span2" />
					<s:hidden id="reportId" name="report.id" />
					<button class="btn" type=button onclick="saveVehicle(this)">提交</button>&nbsp;&nbsp;&nbsp;
					<button class="btn"  onclick="del(this)" type="button">移除</button>
					</s:form>
			</p>
		</div>
	</div>
		
</body>
</html>