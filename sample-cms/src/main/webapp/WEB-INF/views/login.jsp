<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="STATIC_DOMAIN" scope="application"><spring:message code="res.domain"></spring:message></c:set>
<c:set var="SITE_CONTEXT_PATH" scope="application">${pageContext.request.contextPath }</c:set>
 
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>CMS | 用户登陆 </title>
    <link rel="stylesheet" href="${STATIC_DOMAIN}/layui-pkg/dist/css/layui.css" media="all">

</head>

<body>

<form class="layui-form layui-form-pane" style="width: 360px; float:right; margin-right: 100px; margin-top: 200px;" action="${SITE_CONTEXT_PATH}/login.html" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-block">
      <input type="text" name="username" placeholder="请输入用户名称" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密&#12288;码</label>
    <div class="layui-input-block">
      <input type="password" name="password" placeholder="请输入用户密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="*">登陆</button>
      <!--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
    </div>
  </div>
</form>

<script src="${STATIC_DOMAIN}/layui-pkg/dist/layui.js"></script>
<script>
layui.use(['layer','form'], function(){
    var layer = layui.layer;
    var form = layui.form;


    <c:if test="${not empty error_code}">
    layer.msg('${error_code}', {icon: 5}); 
    </c:if>

});
</script>

</body>

</html>
