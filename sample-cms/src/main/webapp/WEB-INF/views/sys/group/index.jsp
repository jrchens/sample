<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<table class="layui-table" lay-data="{height:315, url:'${SITE_CONTEXT_PATH}/sys_group/query.json', page:true, id:'sys_user_list'}" lay-filter="sys_user_list">
  <thead>
    <tr>
      <th lay-data="{field:'id', width:60}">ID</th>
      <th lay-data="{field:'groupname', width:120}">群组名称</th>
      <th lay-data="{field:'viewname', width:120, sort: true}">显示名称</th>
    </tr>
  </thead>
</table>

<script type="text/javascript" src="${SITE_CONTEXT_PATH}/resources/sys/group.js"></script>