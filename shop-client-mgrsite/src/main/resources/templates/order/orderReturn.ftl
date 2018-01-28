<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小卖部管理平台</title>
<#include "../common/header.ftl"/>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js" ></script>
<script type="text/javascript">
	$(function() {
        $('#pagination').twbsPagination({
            totalPages : ${(pageResult.totalPage)!'1'},
            startPage : ${(pageResult.currentPage)!'1'},
            visiblePages : 5,
            first : "首页",
            prev : "上一页",
            next : "下一页",
            last : "最后一页",
            onPageClick : function(event, page) {
                $("#currentPage").val(page);
                $("#searchForm").submit();
            }
        });
        $(".auditor").click(function () {
            var id = $(this).data("id");

            $.get("/auditor?id="+id,function (jsonResult) {
                if (jsonResult.success){
                    $.messager.confirm("温馨提示","审核成功",function () {
                        window.location.reload();
                    })
                }else{
                    $.messager.alert("温馨提示","审核失败")
                }
            })
        });
	});
</script>
</head>
<body>
	<div class="container">
		<#include "../common/top.ftl"/>
		<div class="row">
			<div class="col-sm-3">
				<#assign currentMenu="bidrequest_audit1_list" />
				<#include "../common/menu.ftl" />
			</div>
			<div class="col-sm-9">
				<div class="page-header">
					<h3>订单管理</h3>
				</div>
				<div class="panel panel-default">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>退款金额</th>
								<th>退款时间</th>
								<th>退款人</th>
								<th>退款状态</th>
								<th>退款原因</th>
                                <th>审核</th>
							</tr>
						</thead>
						<tbody>
                            <#if (pageResult.listData?size > 0)>
                                <#list pageResult.listData as data>
                                        <td>${data.id}</td>
                                        <td>${(data.amount)!""}</td>
                                        <td>${(data.returnTime?string("yyyy-MM-dd"))!""}</td>
                                        <td>${data.username}</td>
                                        <td>${data.stateDis}</td>
                                        <td>${data.note}</td>
                                        <td>
                                            <#if (data.state==0)>
                                            <input type="button" value="审核" style="width: 60px;"
                                                   data-id="${data.id}" class="btn btn-default auditor"/>
                                            <#else >
                                                <span style="color: #942a25">审核完成</span>
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                            </#if>
						</tbody>
					</table>
				</div>
                <div style="text-align: center;">
                    <ul id="pagination" class="pagination"></ul>
                </div>
			</div>
		</div>
	</div>
</body>
</html>