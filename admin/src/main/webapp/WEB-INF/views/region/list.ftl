<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <#include "../common/header.ftl"/>
    <link rel="stylesheet" href="/js/plugins/bootstrap-treeview/bootstrap-treeview.min.css" type="text/css" />
    <script src="/js/plugins/bootstrap-treeview/bootstrap-treeview.min.js"></script>

    <script type="text/javascript">
        $(function(){
            var req="/region/getRegionMapByParentId.do";
            $.get(req,function (data) {
                var defaultDate=[{
                    text:"全部地区",
                    nodes:data
                }];

                // console.log(data);
                $('#treeview1').treeview({
                    data:defaultDate,
                    showTags:true,
                    lazyLoad:function (node) {
                        $.get("/region/getRegionMapByParentId.do",{parentId:node.id},function (data) {
                            $('#treeview1').treeview("addNode",[data,node])
                        })
                    },
                    onNodeSelected:function (event,data) {
                        $.get("/region/getRegionByParentId.do",{parentId:data.id},function (data) {
                            $('.table tbody').empty();
                            $(data).each(function (index,region) {
                                // console.log(region);
                                var tr=$('#template tr').clone(true);
                                tr.find("td:eq(0)").text(index+1);
                                tr.find("td:eq(1)").text(region.name);
                                tr.find(".btn-add").attr("data-json",JSON.stringify(region));
                                var recom=tr.find('.btn-recommened');
                                if(region.state==1){
                                    recom.text("取消推荐");
                                }else{
                                    recom.text("设为推荐");
                                }
                                recom.attr("data-json",JSON.stringify(region));
                                $('.table tbody').append(tr);
                            })
                        })
                    }
                });
                
            });
            
            $('.btn-add').click(function () {
                var parentNode= $('#treeview1').treeview('getSelected');
                if(parentNode.length>0){
                    $("#editForm")[0].reset();
                    $("[name='parent.id']").val(parentNode[0].id);
                    $("[name='parent.name']").val(parentNode[0].text);
                }else{
                    $.messager.alert("请选择上级地区");
                    return;
                    $.messager.alert("未选择上级地区，本次添加为一级地区");
                }
                var region=$(this).data("json");
                if(region){
                    $("input[name=id]").val(region.id);
                    $("input[name=name]").val(region.name);
                }
                $("#regionModal").modal('show');
            });
            $("#editForm").ajaxForm(function (date) {
               $.messager.confirm("tips","保存成功",function () {
                   window.location.reload();
               }) ;
            });
            $("#saveBtn").click(function () {
                $("#editForm").submit();
            });
            
            $('.btn-recommened').click(function () {
                var region=$(this).data("json");
                var url="/region/updateState.do?id="+region.id+"&state="+(1^region.state);
                // var url2="/region/updateState.do?region="+region;
                window.location.href=url;
            });
        });
    </script>

</head>

<body>

<table id="template" hidden>
    <tr>
        <td></td>
        <td></td>
        <td>
            <a href="#" class="btn-add">修改</a>
        </td>
        <td>
            <a href="#" class="btn-recommened">设为推荐</a>
        </td>
    </tr>
</table>

	<div class="container">
		<#include "../common/top.ftl"/>
		<div class="row">
			<div class="col-sm-3"><#assign currentMenu = "user" />
				<#include "../common/menu.ftl" /></div>
			<div class="col-sm-9">
                <div class="page-header">
                    <h3>平台用户管理</h3>
                </div>
                <div class="col-sm-4">
                    <div id="treeview1"></div>
                </div>
				<div class="col-sm-8">
                    <div class="row">
                        <!-- 提交分页的表单 -->
                        <form id="searchForm" class="form-inline" method="post"
                              action="/user/list.do">
                            <div class="form-group">
                                <button id="query" type="button" class="btn btn-success btn-add">
                                    <i class="icon-search"></i>
                                    添加
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>名称</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
				</div>
			</div>
		</div>
	</div>

<div id="regionModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" method="post" action="/region/saveOrUpdate.do" >
                    <input id="regionId" type="hidden" name="id" value="" />
                    <div class="form-group">
                        <label class="col-sm-4 control-label">名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="name" placeholder="地区/景区名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">上级地区</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="parent.name" readonly >
                            <input type="hidden" class="form-control"  name="parent.id" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>