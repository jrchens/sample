<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../views/common/taglib.jsp" %>

  <div class="layui-header">
    <div class="layui-logo">ＣＭＳ</div>
    <ul class="layui-nav layui-layout-left" lay-filter="first_level_menu" id="first_level_menu"><!-- 
      <li class="layui-nav-item"><a href="${SITE_CONTEXT_PATH}/menu/index.html?sub=sys" id="menu_sys">系统管理</a></li>
      <li class="layui-nav-item"><a href="${SITE_CONTEXT_PATH}/menu/index.html?sub=cms" id="menu_cms">内容管理</a></li> -->
      <li class="layui-nav-item" id="menu_sys"><a href="${SITE_CONTEXT_PATH}/menu/index.html?sub=sys">系统管理</a></li>
      <li class="layui-nav-item" id="menu_cms"><a href="${SITE_CONTEXT_PATH}/menu/index.html?sub=cms">内容管理</a></li>
      <!-- <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li> -->
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <!-- <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> -->
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">修改密码</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="${SITE_CONTEXT_PATH}/logout.html">退了</a></li>
    </ul>
  </div>