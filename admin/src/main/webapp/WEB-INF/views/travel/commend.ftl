<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript">
        $(function(){
                $("#pagination").twbsPagination({
                    totalPages:${result.endPage},
                    visiblePages:5,
                    startPage:${qo.currentPage},
                    first:"首页",
                    prev:"上一页",
                    next:"下一页",
                    last:"尾页",
                    onPageClick:function(event,page){
                        $("#currentPage").val(page);
                        $("#searchForm").submit();
                    }
                });

                $("#query").click(function(){
                    $("#currentPage").val(1);
                    $("#searchForm").submit();
                });

                //点击添加按钮
                $("#addTravelCommendBtn").click(function(){
                    $("#editForm")[0].reset();
                    $("#travelCommendModal").modal("show");
                });

                //点击保存
             $("#editForm").ajaxForm(function(data){
                 $.messager.confirm("提示","保存成功",function(){
                     window.location.reload();
                 });
             });
             $("#saveBtn").click(function(){
                 $("#editForm").submit();
             });

             //点击修改按钮
             $(".edit_Btn").click(function(){
                 var json=$(this).data("jsonstr");
                 $("#travelCommendId").val(json.id);
                 $("#travelId").val(json.travel.id);
                 $("#title").val(json.title);
                 $("#subTitle").val(json.subTitle);
                 $("#coverUrl").attr('src',json.coverUrl);
                 $("#schedule").val(json.schedule);
                 $("#type option[value='"+json.type+"']").prop("selected",true);
                 $("#travelCommendModal").modal("show");
             });

            $("#qo_type option[value='${(qo.type)!""}']").prop("selected",true);

            $("#skip").click(function () {
                window.open("/travel/list.do?travelId="+$("#travelId").val());
            })

        });



    </script>
</head>

<body>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
		<#assign currentMenu = "travelCommend" />
				<#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>游记推荐管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/travelCommend/list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>关键字</label>
                        <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!''}">
                    </div>
                    <div class="form-group">
                        <select id="qo_type" class="form-control" autocomplete="off" name="type" >
                            <option value="-1">全部</option>
                            <option value="1">每月推荐</option>
                            <option value="2">每周推荐</option>
                            <option value="3">攻略推荐</option>
                        </select>
                        <button id="query" type="button" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                        <a href="javascript:void(-1);" class="btn btn-success" id="addTravelCommendBtn">添加推荐游记</a>
                    </div>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                    <tr>
                        <th>封面</th>
                        <th>标题</th>
                        <th>副标题</th>
                        <th>推荐时间安排</th>
                        <th>推荐类型</th>
                    </tr>
                    </thead>
                    <tbody>
					<#list result.data as vo>
                    <tr>
                        <td><img src='${vo.coverUrl!""}' width="100px"/> </td>
                        <td>${vo.title}</td>
                        <td>${vo.subTitle}</td>
                        <td>${vo.schedule?string('yyyy-MM-dd')}</td>
                        <td>
                            <#if vo.type == 1>
                                每月推荐
                            <#elseif vo.type == 2>
                                每周推荐
                            <#elseif vo.type == 3>
                                攻略推荐
                            </#if>
                        </td>
                        <td>
                            <a href="javascript:void(-1);" class="edit_Btn"  data-jsonstr='${vo.jsonString}'>修改</a>
                        </td>
                    </tr>
					</#list>
                    </tbody>
                </table>

                <div style="text-align: center;">
                    <ul id="pagination" class="pagination"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="travelCommendModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" method="post" action="/travelCommend/saveOrUpdate.do" enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="travelCommendId" type="hidden" name="id" value="" />
                    <input id="travelId" type="hidden" name="travel.id" value="" />
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" placeholder="标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">副标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="subTitle" name="subTitle" placeholder="副标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img id="coverUrl" width="200px"/>
                            <input type="file" class="form-control" id="pic" name="pic" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐时间</label>
                        <div class="col-sm-6">
                            <input type="text" id="schedule" name="schedule" class="form-control" onclick="WdatePicker()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐类型</label>
                        <div class="col-sm-6">
                            <select id="type" class="form-control" autocomplete="off" name="type" >
                                <option value="1">每月推荐</option>
                                <option value="2">每周推荐</option>
                                <option value="3">攻略推荐</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" style="text-align: center">
                         <a href="javascript:void(0)" target="_blank" id="skip">点击查看游记文章明细</a>
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