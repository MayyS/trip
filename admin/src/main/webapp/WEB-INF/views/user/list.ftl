<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>骡窝窝系统管理平台</title>
<#include "../common/header.ftl"/>

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

        });
    </script>
</head>

<body>
	<div class="container">
		<#include "../common/top.ftl"/>
		<div class="row">
			<div class="col-sm-3"><#assign currentMenu = "user" />
				<#include "../common/menu.ftl" /></div>
			<div class="col-sm-9">
				<div class="page-header">
					<h3>平台用户管理</h3>
				</div>
				<div class="row">
					<!-- 提交分页的表单 -->
					<form id="searchForm" class="form-inline" method="post"
						action="/user/list.do">
						<input type="hidden" name="currentPage" id="currentPage" value="1" />
						<div class="form-group"></div>
						<div class="form-group">
							<label>关键字</label>
							<input class="form-control" type="text" name="keyWord"
								value="${(qo.keyWord)!''}">
						</div>
						<div class="form-group">
							<button id="query" type="button" class="btn btn-success">
								<i class="icon-search"></i>
								查询
							</button>
						</div>
					</form>
				</div>
				<div class="row">
					<table class="table">
						<thead>
							<tr>
								<th>邮箱</th>
								<th>昵称</th>
								<th>地区</th>
								<th>头像</th>
								<th>性别</th>
								<th>签名</th>
							</tr>
						</thead>
						<tbody>
							<#list pageInfo.list as entity>
							<tr>
								<td>${entity.email!}</td>
								<td>${entity.nickName!}</td>
								<td>${entity.place!}</td>
								<td>
                                    <img src='${entity.headImgUrl!"/img/user/head.jpg"}' width="30px" />
								</td>
								<td>${entity.genderName!}</td>
								<td>${entity.sign!}</td>
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
</body>
</html>