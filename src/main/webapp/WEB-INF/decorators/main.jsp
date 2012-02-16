<%@page import="org.springframework.context.annotation.Import"%>
<%@ page isELIgnored ="true" %> 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<%
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="dd"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>"/>
	<link href="styles/bootstrap.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"  charset="utf-8"></script>
	<script type="text/javascript" src="js/easyui.js"  charset="utf-8"></script>
	<decorator:head/>
</head>
<body>
<div class="navbar">
    <div class="navbar-inner">
      <div style="width: auto;" class="container">
        <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a href="#" class="brand">工作周报  </a>
        <div class="nav-collapse">
          <ul class="nav">
            <li id="list" class="active"><a href="report-list.action" >周报列表</a></li>
             <security:authorize ifAnyGranted="ROLE_MANAGER">
             		<li id="addlink" ><a href="add-report.action" >添加周报</a></li>
              </security:authorize>
            
            <li><a href="j_spring_cas_security_logout"> 退出</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div>
    </div><!-- /navbar-inner -->
  </div>

<div class="container">
<div class="well">
<decorator:body/>
</div>
  </div>
<div class="container">
    <p class="pull-left">&copy; Copyright <a href="http://www.suilink.com">广东日海通信工程有限公司</a>.</p>
    <p class="pull-right">power by <a href="http://www.dayatang.com">广州大雅堂信息科技有限公司</a></p>
</div>
  
</body>

</html>
