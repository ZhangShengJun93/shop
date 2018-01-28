<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小卖部管理平台</title>
<#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#save").click(function () {
                $("#userItemForm").ajaxSubmit(function (jsonResult) {
                    if (jsonResult.success){
                        $.messager.confirm("温馨提示","保存成功",function () {
                            location.href="/orderItem?id="+${orderInfo.id};
                        })
                    }else{
                        $.messager.alert("温馨提示",jsonResult.message);
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
                <h3>用户收货管理</h3>
            </div>
            <div align="right"><input class="btn btn-default"
                                      style="background-color:#00ee00" type="button" value="确定保存" id="save"></div>
            <div class="panel panel-default" >
                <form action="/userItemSubmit" method="post" id="userItemForm">
                    <table class="table table-bordered table-hover">
                        <tbody>
                        <input type="hidden" name="orderInfo.id" value="${orderInfo.id}"/>
                        <tr>
                            <td>收货人:</td>
                            <td>
                                <div class="input-group">
                                    <input type="text" name="user.username" value="${user.username}"/>
                                </div>
                            </td>
                            <td>电子邮件:</td>
                            <td>
                                <div class="input-group">
                                    <input type="text" name="user.email" value="${user.email}"/>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>地址:</td>
                            <td>
                                <div class="input-group">
                                    <input type="text" name="orderInfo.address" value="${orderInfo.address}"/>
                                </div>
                            </td>
                            <td>邮编:</td>
                            <td>
                                <div class="input-group">
                                    <input type="text" name="user.experience" value="${user.experience?c}"/>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>电话:</td>
                            <td>
                                <div class="input-group">
                                    <input type="text" name="user.phone" value="${user.phone}"/>
                                </div>
                            </td>
                            <td>手机:</td>
                            <td>
                                <div class="input-group">
                                    <input type="text" name="orderInfo.mobile" value="${orderInfo.mobile}"/>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>