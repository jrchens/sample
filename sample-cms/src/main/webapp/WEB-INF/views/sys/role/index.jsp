<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>

<blockquote class="layui-elem-quote">
<div class="layui-form layui-form-pane" id="querySysRoleForm" name="querySysRoleForm" action="${SITE_CONTEXT_PATH}/sys_role/query.json" method="get">
    <div class="layui-form-item" style="margin: 0px; padding: 0px;">
        <div class="layui-inline">
            <label class="layui-form-label">显示名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="viewname" name="viewname" placeholder="请输入角色显示名称" autocomplete="off" class="layui-input">
            </div>
            <a class="layui-btn" id="querySysRoleBtn" name="querySysRoleBtn">查询</a>
        </div>
        <div style="float: right;"><a class="layui-btn layui-btn-normal" id="addSysRoleBtn" name="addSysRoleBtn">新增角色</a></div>
    </div>
</div>
</blockquote>

<table id="sys_role_list"></table>
<script type="text/html" id="sys_role_list_toolbar">
    <%--<a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="edit">编辑</a>--%>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>

<blockquote class="layui-elem-quote">
    选择显示名称单元格可直接修改。
</blockquote>


<script type="text/html" id="sys_role_index_save">

    <div class="layui-form-item layui-form-pane">
     
      <div class="layui-inline">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-inline">
          <input type="text" id="rolename" name="rolename" placeholder="请输入角色名称" autocomplete="off" class="layui-input">
        </div>
      </div>
      
      <div class="layui-inline">
        <label class="layui-form-label">显示名称</label>
        <div class="layui-input-inline">
          <input type="text" id="viewname" name="viewname" placeholder="请输入显示名称" autocomplete="off" class="layui-input">
        </div>
      </div>
      
    </div>

</script>

<script type="text/javascript" src="${SITE_CONTEXT_PATH}/resources/sys/role.js"></script>