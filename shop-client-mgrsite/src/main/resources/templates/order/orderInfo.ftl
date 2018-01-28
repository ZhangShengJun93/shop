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
        $("table > tbody > tr").dblclick(function () {
            location.href = "/orderItem?id="+$(this).data("dataid");
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
				<form id="searchForm" class="form-inline" method="post" action="">
                    <input type="hidden" id="currentPage" name="currentPage" value="1"/>
                    <div class="form-group">
                        <label>订单状态</label>
                        <select class="form-control" name="state">
                            <option value="-1"<#if (qo.state) == -1>selected="selected"</#if>>全部</option>
                            <option value="0" <#if (qo.state) == 0>selected="selected"</#if>>未确认</option>
                            <option value="1" <#if (qo.state) == 1>selected="selected"</#if>>已确认</option>
                            <option value="2" <#if (qo.state) == 2>selected="selected"</#if>>已完成</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>下单时间</label>
                        <input class="form-control" class="Wdate" onclick="WdatePicker()"
                               type="text" name="beginDate" style="width: 120px;" id="beginDate" value="${(qo.beginDate?string("yyyy-MM-dd"))!''}" />~
                        <input class="form-control" class="Wdate" onclick="WdatePicker()"
                               type="text" name="endDate" style="width: 120px; id="endDate" value="${(qo.endDate?string("yyyy-MM-dd"))!''}" />
                    </div>
                    <div class="form-group">
                        <button id="query" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    </div>
				</form>
				<div class="panel panel-default">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>订单号</th>
								<th>收货人详细</th>
								<th>下单时间</th>
								<th>总金额</th>
								<th>订单状态</th>
								<th>付款状态</th>
								<th>发货状态</th>
							</tr>
						</thead>
						<tbody>
                            <#if (pageResult.listData?size > 0)>
                                <#list pageResult.listData as data>
                                    <tr data-dataid='${data.id}'>
                                        <td>${data.orderSn}</td>
                                        <td>${(data.addressInfo)!""}</td>
                                        <td>${(data.confirmTime?string("yyyy-MM-dd"))!""}</td>
                                        <td>${data.orderAmount}</td>
                                        <td>${data.orderStatusDis}</td>
                                        <td>${data.payStatusDis}</td>
                                        <td>${data.shippingStatusDis}</td>
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