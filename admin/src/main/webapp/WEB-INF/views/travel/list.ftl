<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">

        .content p{
            line-height: 25px;
            opacity: 0.75;
            font-size: 16px;
            margin-bottom: 15px;
        }


        .content h4 {
            font-weight: bold;
            line-height: 1.333em;
            margin: 5px 0px;
            padding: 5px 0;
            font-size: 16px;
        }

        .content img {
            width: 100%;
            height: auto;
            box-sizing: border-box;
        }

    </style>
    <script type="text/javascript">
        $(function(){
            $("#pagination").twbsPagination({
                totalPages:${pageInfo.pages} || 1,
                    visiblePages:5,
                    startPage:${pageInfo.pageNum} || 1,
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

            /*
                //点击查看按钮
                $(".look_Btn").click(function(){
                    var json=$(this).data("jsonstr");
                    //获取该文章的内容
                    $.get('/travel/getContentById.do',{id:json.id},function (data) {
                        if(data.travelContent){
                           $("#travelModal .modal-body").html(data.travelContent.content);
                        }
                        $("#travelModal").modal("show");

                    });
                });

                //点击保存
                $("#commendForm").ajaxForm(function(data){
                    $.messager.confirm("提示","保存成功",function(){
                        window.location.href="/travelCommend/list.do";
                    });
                });
                $("#saveCommendBtn").click(function(){
                    $("#commendForm").submit();
                });

                //点击推荐按钮
                $(".commend_Btn").click(function(){
                    var json=$(this).data("jsonstr");
                    $("#travelId").val(json.id);
                    $("#travelCommendModal [name=title] ").val(json.title);
                    $("#travelCommendModal [name=coverUrl]").val(json.coverUrl);
                    $("#travelCommendModal img").attr('src',json.coverUrl);
                    $("#travelCommendModal").modal("show");
                });


            $(".changeState").click(function(){
                var json=$(this).data("jsonstr");
                //修改文章发布状态
                $.get('/travel/changeState.do',{id:json.id,state:1},function (data) {
                    if(data.success){
                        window.location.reload();
                    }else{
                        $.messager.alert(data.msg);
                    }
                });
            });
        });
*/

    </script>
</head>

<body>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
		<#assign currentMenu = "travel" />
				<#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>已发布游记管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/travel/list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>关键字</label>
                        <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!''}">
                    </div>
                    <div class="form-group">
                        <button id="query" type="button" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    </div>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                    <tr>
                        <th>标题</th>
                        <th>封面</th>
                        <th>地点</th>
                        <th>作者</th>
                        <th>发布时间</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
					<#list result.data as vo>
                    <tr>
                        <td>${vo.title!""}</td>
                        <td><img src='${vo.coverUrl!""}' width="50px"/></td>
                        <td>${vo.place.name!""}</td>
                        <td>${vo.author.nickName!""}</td>
                        <td>${(vo.releaseTime?string('yyyy-MM-dd HH:mm:ss'))!""}</td>
                        <td>
                            <#if vo.state == -1>
                                拒绝
                            <#elseif vo.state == 0>
                                草稿
                            <#elseif vo.state == 1>
                                待发布
                            <#elseif vo.state == 2>
                                已发布
                            </#if>
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="look_Btn"  data-jsonstr='${vo.jsonString}'>查看游记</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);"  class="changeState" data-jsonstr='${vo.jsonString}'>取消发布</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="commend_Btn"  data-jsonstr='${vo.jsonString}'>推荐</a>
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

<div id="travelModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看内容</h4>
            </div>
            <div class="modal-body content">
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
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
                <form id="commendForm" class="form-horizontal" method="post" action="/travelCommend/saveOrUpdate.do" enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="travelCommendId" type="hidden" name="id" value="" />
                    <input id="travelId" type="hidden" name="travel.id" value="" />
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" placeholder="标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">副标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="subTitle" placeholder="副标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img width="200px"/>
                            <input type="hidden" name="coverUrl"  >
                            <input type="file" class="form-control"  name="pic" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐时间</label>
                        <div class="col-sm-6">
                            <input type="text" name="schedule" class="form-control" onclick="WdatePicker()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐类型</label>
                        <div class="col-sm-6">
                            <select class="form-control" autocomplete="off" name="type" >
                                <option value="1">每月推荐</option>
                                <option value="2">每周推荐</option>
                                <option value="3">攻略推荐</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveCommendBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>