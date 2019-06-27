<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <#include "../common/header.ftl"/>

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


                $("[data-state]").click(function(){
                    var json=$(this).data("jsonstr");
                    var state = $(this).data('state');
                    //修改文章发布状态
                    $.get('/travel/changeState.do',{id:json.id,state:state},function (data) {
                        if(data.success){
                            window.location.reload();
                        }else{
                            $.messager.alert(data.msg);
                        }
                    });
                });

                $(".look_Btn").click(function(){
                    var json=$(this).data("jsonstr");
                    //获取该文章的内容
                    $.get('/travel/getContentById.do',{id:json.id},function (data) {
                        $(".modal-body").html(data.travelContent.content);
                        $("#travelModal").modal("show");

                    });


                });

            $("#auditState option[value='${(qo.state)!""}']").prop("selected",true);
        });


    </script>
</head>

<body>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
		<#assign currentMenu = "audit_list" />
				<#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>游记管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/travel/audit_list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>关键字</label>
                        <input class="form-control" type="text" name="keyWord" value="${(qo.keyWord)!''}">
                    </div>
                    <div class="form-group">
                        <select id="auditState" class="form-control" autocomplete="off" name="state" >
                            <option value="-2">全部</option>
                            <option value="1">待发布</option>
                            <option value="-1">已拒绝</option>
                        </select>
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
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
					<#list pageInfo.list as vo>
                    <tr>
                        <td>${vo.title!""}</td>
                        <td><img src='${vo.coverUrl!""}' width="50px"/></td>
                        <#--<td>${vo.place.name!""}</td>-->
                        <#--<td>${vo.author.nickName!""}</td>-->
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
                              <a href="javascript:void(0);"  data-state="2" data-jsonstr='${vo.jsonString}'>发布</a>
                              <#--<a href="javascript:void(0);"  data-state="2" >发布</a>-->
                        </td>
                        <td>
                              <a href="javascript:void(0);"  data-state="-1" data-jsonstr='${vo.jsonString}'>拒绝</a>
                              <#--<a href="javascript:void(0);"  data-state="-1">拒绝</a>-->
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="look_Btn"  data-jsonstr='${vo.jsonString}'>查看文章</a>
                            <#--<a href="javascript:void(0);" class="look_Btn" >查看文章</a>-->
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
                <a href="javascript:void(0);" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>