<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小卖部管理平台</title> 
<#include "../common/header.ftl"/>
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">

<#--<script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>-->
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
    /*-----------------------------------------------------------------------------
    var setting = {
        async: {
            enable: true,
            url: "/catalog/get",
            autoParam: ["id"]
        },
        callback: {
            onClick: zTreeOnClick
        }
    };

    function zTreeOnClick(event, treeId, treeNode) {
    
        //给新增分类的模态框的父分类名设值
        $("#parentCatalog").val(treeNode.name);
        //给新增分类的模态框的父分类id设值
        $("#parentCatalogId").val(treeNode.id);
    
        //清空分类属性面板内容
        $("#skuPropertyPanel").empty();
    
        //重新通过分类ID加载分类属性面板内容
        $("#skuPropertyPanel").load("/skuProperty/get/" + treeNode.id)
    
    };

    var treeObj;
    $(document).ready(function () {
    
        treeObj = $.fn.zTree.init($("#catalogTree"), setting);
    
    });
    
     //打开添加分类属性模态框
    function addSkuProperty(propertyId) {
        //获取当前选择的分类
        var sNodes = treeObj.getSelectedNodes();
        $("#propertyFormContent").load("/skuProperty/save?id=" + propertyId + "&catalogId=" + sNodes[0].id);
    
        $("#propertyModal").modal("show");
    }
    -----------------------------------------------------------------------------*/
    $(function () {
        // 加载自己的树菜单
        $("#catalogTree").tree({
            url: 'catalog/getForSku',
            // 创建一个点击事件,创建对应的选项卡
            onClick: function (node) {
            
                //给新增分类的模态框的父分类名设值
                $("#parentCatalog").val(node.text);
                //给新增分类的模态框的父分类id设值
                $("#parentCatalogId").val(node.id);
            
                $("#skuPropertyPanel").load("/skuProperty/get/" + node.id);
            }
        });
    });

    //打开分类属性值的模态框
    function showProperty(id) {
        $("#propertyValueModal").modal("show");
    
        //情况分类属性值模态框内容
        $("#skuPropertyValueForm").empty();
    
        //重新通过分类属性ID或者分类属性值模态框内容
        $("#skuPropertyValueForm").load("/skuPropertyValue/get/" + id);
    
    }

    //添加分类属性值的input元素
    function addValue() {
        var valueDivTemplate = $("#valueDivTemplate").html();
        $("#valueDiv").append(valueDivTemplate);
    }

    //删除分类属性值input元素
    function deleteValue(obj, id) {
        $(obj).parent().parent().remove();
        if (id != 0) {
            $.ajax({
                url: "/skuPropertyValue/delete/" + id,
                dataType: "json",
            })
        }
    }

    //提交分类属性值表单
    function submitValueForm() {
        var form = $("#skuPropertyValueForm");
        $("#propertyValueModal").modal("hide");
        var inputs = $("input[name='propertys']");
        
        if (inputs.length>0) {
            form.ajaxSubmit(function (data) {
                var node = $("#catalogTree").tree("getSelected");
                $("#skuPropertyPanel").load("/skuProperty/get/" + node.id);
            });
        }
        return false;
    }

    function addSkuProperty(propertyId) {
        //获取当前选择的分类
        var select = $("#catalogTree").tree('getSelected');
    
        $("#propertyFormContent").load("/skuProperty/add?id=" + propertyId + "&catalogId=" + select.id);
    
        $("#propertyModal").modal("show");
    }

    function saveSkuProperty() {
        var form = $("#skuPropertyForm");
        $("#propertyModal").modal("hide");
        form.ajaxSubmit(function (data) {
            if (data.success) {
                $.messager.confirm("提示", "保存成功!", function () {
                   // window.location.reload();
                    var node = $("#catalogTree").tree("getSelected");
                    $("#skuPropertyPanel").load("/skuProperty/get/" + node.id);
                });
            } else {
                $.messager.alert("提示", data.msg);
            }
        });
    }

    function deleteSkuProper(id, catalogId) {
        $.messager.confirm("提示", "确定删除吗？", function () {
            //删除分类属性，并重新通过分类ID加载分类属性面板内容
            $("#skuPropertyPanel").load("/skuProperty/delete?id=" + id + "&catalogId=" + catalogId);
        });
    }
</script>
<script type="text/x-template" id="valueDivTemplate">
	<div style="height: 40px;">
                    <input type="text"
                           class="form-control"
                           name="propertys"
                           style="width: 200px;margin-bottom: 5px;float: left;">
				<h3 style="float: left;margin: 0;">
					<span class="label label-primary"
                          style="cursor: pointer;"">-</span>
				</h3>
	</div>
	</script>
</head>
<body>
	<div class="container">
		<#include "../common/top.ftl"/>
        <div class="row">
			<div class="col-sm-3"><#assign currentMenu="skuProperty" />
				<#include "../common/menu.ftl" /></div>
			<div class="col-sm-9">


				<div class="page-header">
					<h3>sku属性管理</h3>
				</div>
				<div class="row">
                    <#--zetrees树-->
                        <div class="col-sm-4">
						<ul id="catalogTree" class="ztree"></ul>
					</div>
                    <#--easytry-->
                        <ul id="catalogTree" class="easyui-tree">
                     </ul>
                        
					<div class="col-sm-8" id="skuPropertyPanel">
					</div>
				</div>
			</div>
		</div>
        
        
        <!-- 新增分类属性值模态框 -->
        <div class="modal fade" id="propertyValueModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">分类属性值</h4>
					</div>
                    
                    <!-- 提交添加分类属性值的表单 -->
					<form action="/skuPropertyValue/add" method="post" id="skuPropertyValueForm">
						
					</form>
				</div>
			</div>
		</div>
        
        <!-- 新增分类属性模态框 -->
        <div class="modal fade" id="propertyModal">
			<div class="modal-dialog">
				<div class="modal-content" id="propertyFormContent"></div>
			</div>
		</div>
    </div>
</body>
</html>







