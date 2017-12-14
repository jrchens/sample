<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../views/common/taglib.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>ＣＭＳ</title>
<meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">
  
  <link rel="stylesheet" href="${STATIC_DOMAIN}/layui-pkg/dist/css/layui.css" media="all">
  
  <script type="text/javascript">
    var SITE_CONTEXT_PATH = '${SITE_CONTEXT_PATH}';
  </script>
</head>
<body>

<script src="${STATIC_DOMAIN}/jquery/dist/jquery.min.js" charset="utf-8"></script>
<script src="${STATIC_DOMAIN}/layui-pkg/dist/layui.js" charset="utf-8"></script>
<script src="${STATIC_DOMAIN}/jquery-serializejson/jquery.serializejson.min.js" charset="utf-8"></script>

<div class="layui-layout layui-layout-admin">

    <!-- 头部区域（可配合layui已有的水平导航） -->
    <tiles:insertAttribute name="top"></tiles:insertAttribute>

    <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
    <tiles:insertAttribute name="left"></tiles:insertAttribute>

    <script src="${SITE_CONTEXT_PATH}/resources/menu.js" charset="utf-8"></script>

    <div class="layui-body">
    <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <tiles:insertAttribute name="body"></tiles:insertAttribute>
        </div>
    </div>

    <!-- 底部固定区域 -->
    <!-- <div class="layui-footer"> © layui.com - 底部固定区域</div> -->

</div>

</body>
</html>