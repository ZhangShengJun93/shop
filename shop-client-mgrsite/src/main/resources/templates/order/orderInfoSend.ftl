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
        $(function () {
           $(".updateState").click(function () {
               var id = $(this).data("id");
               $.get("/updateState?id="+id,function () {
                   window.location.reload();
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
								<th>订单号</th>
								<th>收货人详细</th>
								<th>下单时间</th>
								<th>总金额</th>
								<th>付款状态</th>
								<th>发货状态</th>
                                <th>操作</th>
							</tr>
						</thead>
						<tbody>
                            <#if (orderInfos?size > 0)>
                                <#list orderInfos as data>
                                    <tr>
                                        <td>${data.orderSn}</td>
                                        <td>${(data.addressInfo)!""}</td>
                                        <td>${(data.confirmTime?string("yyyy-MM-dd"))!""}</td>
                                        <td>${data.orderAmount}</td>
                                        <td>${data.payStatusDis}</td>
                                        <td>${data.shippingStatusDis}</td>
                                        <td><input type="button"   class="btn btn-default updateState" value="确认签收" data-id="${data.id}"/></td>
                                    </tr>
                                </#list>
                            </#if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>