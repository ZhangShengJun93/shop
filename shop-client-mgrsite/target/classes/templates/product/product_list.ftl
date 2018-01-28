<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小卖部管理平台</title>
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/plugins/jquery-easyui/themes/icon.css">
    <#--<#include "../common/header.ftl" />-->
    <link rel="stylesheet" href="/js/bootstrap-3.3.2-dist/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="/css/core.css" type="text/css" />
    <link rel="stylesheet" href="/css/common.css" type="text/css" />
    <script type="text/javascript" src="/js/jquery/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js" ></script>

    <script type="text/javascript" src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>


    <script type="text/javascript">
        $(function(){
            $("#query").click(function(){
                $("#currentPage").val(1);
                $("#searchForm").submit();
//                $("#searchForm").form("submit",{
//                    url:"/product/get",
//                            onSubmit:function (param) {
//                        var catalogIds = $("#catalog_combobox").combobox("getValues");//获取到的是设置要传的值即id
//                        for (var i = 0;i<catalogIds.length;i++){
//                            param["catalogIds["+i+"]"] = catalogIds[i];
//                        }
//                    },
//                })
            });
            $('#pagination_container').twbsPagination({
                totalPages : ${pageResult.totalPage}||1,
                    startPage : ${pageResult.currentPage},
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

            $("table > tbody > tr").click(function () {
                $("table > tbody > tr").removeClass("active");

                $(this).addClass("active");
                console.log($(this));
                $("#productIdHidden").val($(this).data("id"));
            });

            $(".edit-product").click(function(){
                var productId = $("#productIdHidden").val();
                if(productId && productId != ""){
                    window.location.href="/product/add?productId="+productId;
//                    $.messager.alert($("#productIdHidden").val())
                }else{

                    $.messager.alert("请选择需要编辑的商品")
                }
            })

            $(".show-product").click(function(){
                var productId = $("#productIdHidden").val();
                if(productId && productId != ""){

                    window.location.href = "/product/show?productId="+productId;
                }else{

                    $.messager.alert("请选择需要查看的商品")
                }
            })

            $(".generate-sku").click(function(){
                if($("#productIdHidden").val() && $("#productIdHidden").val() != ""){

                    window.location.href = "/productSku/"+$("#productIdHidden").val();
                }else{
                    $.messager.alert("请选择需要生成sku的商品")
                }
            })
            <#if qo.cataIds??>/*如果不为null*/
//                var catalogIds = $("#catalog_combobox").data("catalog");
//                var length = catalogIds.length;
//                if (length>1){
                    var catalog = $("#catalog").val();
                    $("#catalog_combobox").combobox("setValues",catalog.split(","));
//                }else {
//                    $("#catalog_combobox").combobox("setValues",catalogIds);

//                }
            </#if>

        });
    </script>
</head>
<body>
<input type="hidden" id="productIdHidden"/>
<div class="container">
    <#include "../common/top.ftl">
    <div class="row">
    <div class="col-sm-3">
        <#include "../common/menu.ftl">

        <script type="text/javascript">
            $(".in li.product_list").addClass("active");
        </script>
    </div>
    <div class="col-sm-9">
        <div class="page-header">
            <h3>商品管理</h3>
        </div>
        <div class="row">
            <!-- 提交分页的表单 -->
            <form id="searchForm" class="form-inline" method="post" action="/product/get">
                <input type="hidden" name="currentPage" id="currentPage" value="${qo.currrentPage!''}"/>
                <input id="catalog" type="hidden" value='${qo.cataIds!}' >
                <div class="form-group">
                </div>
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword"  value="${qo.keyword!''}">
                    <label>分类</label>
                    <input id="catalog_combobox" name="catalogIds"  class="easyui-combobox" data-catalog='${qo.cataIds!}' data-options="url:'/product/getAllcatalogs',textField:'name',valueField:'id',panelHeight:'auto',multiple:true">

                </div>
                <div class="form-group">
                    <button id="query" type="button" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    <a href="javascript:;" class="btn btn-success edit-product">编辑商品</a>
                    <a href="javascript:;" class="btn btn-success show-product">查看商品</a>
                    <a href="javascript:;" class="btn btn-success generate-sku">生成sku</a>
                </div>
            </form>
        </div>
        <div class="panel panel-default">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>商品编号</th>
                    <th>商品名称</th>
                    <th>分类</th>
                    <th>品牌</th>
                    <th>市场价格</th>
                    <th>基础价格</th>
                </tr>
                </thead>
                <tbody id="tbody">
                <#if pageResult.listData?size &gt;0>
                    <#list pageResult.listData as product>
                        <tr data-id="${product.id}">
                            <td>${product.code}</td>
                            <td><a href="#">${product.name}</a></td>
                            <td>${(product.catalog.name)!""}</td>
                            <td>${(product.brand.chineseName)!""}</td>
                            <td>${product.marketPrice}</td>
                            <td>${product.basePrice}</td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
            <div style="text-align: center;" id="pagination_container">

            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>