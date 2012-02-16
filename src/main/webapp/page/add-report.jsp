<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="styles/themes/icon.css"> 
<script type="text/javascript" src="js/locale/easyui-lang-zh_TW.js"  charset="utf-8" ></script>
<title>添加周报</title>
<script type="text/javascript">
	$() .ready(function() {
						//保存草稿
						$("#draft").click(function() {
									$.getJSON("save-report.action", $( "#reportForm").serialize(),
											function(data) {
												$("#version").attr("value", data.report.version);
												$(".reportId").attr("value", data.report.id);
											});
					    });
						
						//改变周报的状态
						$("#commit").mouseover(function() {
											$("#status") .attr( "value", "<s:property value='%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_SUBMITTED}'/>");
						});
						$("#draft").mouseover(function() {
							$("#status").attr("value","<s:property value='%{@com.dayatang.weekly.domain.WeeklyReport@STATUS_DRAFT}'/>");
						});
						
						//添加车辆使用
						$("#addVehicleUsage").click(function() {
									var id = $("#veReportId").val();
									if(id == 0 || id == null || id == ""){
										$("#tt").tabs("select",0);
										msg("请先填写周报内容再添加车辆使用情况");
									}else{
										$("#vu").show();
										$(".datebox").datebox({
											formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
										});	
									}
						});
						
						//日期控件
						$("#fromDate, #toDate").datebox({
							formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
						});
						
						//处理草稿未保存而添加附件的情况
						$("#tt").tabs({"onSelect":function(title){
							if("附件" == title){
								var id = $("#globalId").val();
								if(id == 0 || id == null || id == ""){
									$("#tt").tabs("select",0);
									$("#projectName").focus();
									msg("请先填写周报内容再添加附件");
								}else{
									$("#iframe").attr("src", "add-attachment.action?reportId=" + id);
								}
							}	
						}});
					});//end of ready 
				
				function msg(str){
					$.messager.show({msg:str, showType: "show", title:"注意"});
				}
	
				function msgOfSave(){
					$("#tt").tabs("select",0);
					$("#projectName").focus();
					$.messager.show({msg:"项目名必填哦! ", showType: "show", title:"提示"});	
				}
				
	
				//呈报　
				function commit(){
					var projectName = $("#projectName").val().trim();
					if(projectName == null ||  projectName == ""){
						msgOfSave();		
					}else{
						$("#reportForm").submit();
					}
					return false;
				}
	
				//隐藏车辆表单
				function hiden(){
					//将表单中的值清空
					$("#vuForm input").each(function(i, v){
							if(v.id != "veReportId"){
								$(v).attr("value","");
							}
					});
					$("#vu").hide();
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
				function saveVehicle(){
					$.getJSON("save-vehicle.action", $('#vuForm').serialize(), function(result){
						hiden(); //隐藏输入表单
						var vehicle = result.vehicleUsage;
						$("#veBody").append($("<tr><td>"+vehicle.licensePlateNumber + "  " + vehicle.driver +"</td>"+
																	"<td>"+vehicle.fromDate+"</td>"+
																	"<td>"+ vehicle.startMileage +"</td>"+
																	"<td>"+ vehicle.endMileage +"</td>"+
																	"<td>"+vehicle.fromPlace+"</td>"+
																	"<td>"+vehicle.toPlace+"</td>"+
																	"<td><button class='btn' type='button' onclick='rmVe("+result.reportId+", this)'>移除</button></td></tr>"));
					}); 
					return false;
				}
				
</script>
</head>
<body>

	<button id="commit" class="btn" type="button" onclick="commit();">呈报</button>
	<p/>		
	<div id="tt" class="easyui-tabs">
		<div title="周报内容"  style="padding:20px;">
	<s:form enctype="multipart/form-data" cssClass="form-horizontal" action="save-report.action" theme="simple" id="reportForm" 	method="post">
				<div class="control-group">
					<label class="control-label">项目名</label>
					<div class="controls">
						<s:textfield id="projectName" name="report.projectName" cssClass="input-xlarge" />
						<span class="label label-important help-inline ">必填</span>
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
						<s:set id="fDate" value="report.fromDate"/>
						<s:set id="tDate" value="report.toDate"/>
						<input  style="width:100px;"  class="span2" type="text" id="fromDate" name="report.fromDate" value="<s:date name="#fDate" format="yyyy-MM-dd"/>" />
						<%-- <s:textfield id="fromDate" cssStyle="width:125px;" name="report.fromDate"  value="#fDate"/> --%>
						&nbsp;至&nbsp;
						<input style="width:100px;" type="text" id="toDate" name="report.toDate" value="<s:date name="#tDate" format="yyyy-MM-dd"/>" />
						<%-- <s:textfield id="toDate" name="report.toDate" cssStyle="width:125px;" /> --%>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">工作内容</label>
					<div class="controls">
						<s:textarea  rows="5" cols="50" id="doneWorks" name="report.doneWorks" cssClass="span6"  />
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
					<s:hidden cssClass="reportId" name="report.id" id="globalId"/>
					<s:hidden id="version" name="report.version" />
				<div class="form-actions">
					<button class="btn" type="reset">清空</button>
					<button class="btn" type="button" id="draft">存为草稿</button>
				</div>
			</s:form>
		</div>
		
		
		
		<div title="车辆使用"  style="padding:20px;" cache="false"  >
		<div>
			<button class="btn" id="addVehicleUsage">添加</button>
		</div>
		<div style="display: none;" id="vu" class="alert alert-info">
				<s:form theme="simple"  method="post" action="save-vehicle.action" id="vuForm">
				<input placeholder="车牌号" type="text" name="vehicleUsage.licensePlateNumber" class="span2" />
				<input placeholder="司机" type="text" name="vehicleUsage.driver" class="span1" />
				<input placeholder="使用日期" type="text" name="vehicleUsage.fromDate" class="datebox "/>
				<input placeholder="开始量程" type="text" name="vehicleUsage.startMileage" class="span2" /> 
				<input placeholder="结束量程" type="text" name="vehicleUsage.endMileage" class="span2" /><br/>
				<input placeholder="起始地点" type="text" name="vehicleUsage.fromPlace" class="span2" />
				<input placeholder="到达地点" type="text" name="vehicleUsage.toPlace" class="span2" />
				<input type="hidden"  id="veReportId" name="reportId"  class="reportId" value="${param.reportId }"/>
				<button class="btn btn-primary" type=button onclick="saveVehicle()">提交</button>&nbsp;&nbsp;&nbsp;
				<button class="btn"  onclick="hiden()" type="button">移除</button>
				</s:form>
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
							<tbody id="veBody">
								<s:iterator value="report.vehicleUsages" id="ve" >
									<tr>
										<td><s:property value="#ve.licensePlateNumber" />  <s:property value="#ve.driver"/></td>
										<td><s:date name="#ve.fromDate" format="yyyy-MM-dd"/></td>
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
			<iframe scrolling="auto" id="iframe"   class="span10" height=300 frameborder="0"></iframe>
		</div>
	</div>

		
</body>
</html>