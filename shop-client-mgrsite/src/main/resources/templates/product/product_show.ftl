<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小卖部管理平台</title>
<#include "../common/header.ftl">
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/plugins/ckeditor/ckeditor.js"></script>
    <link type="text/css" rel="stylesheet" href="/js/plugins/uploadFive/uploadifive.css"/>
    <script type="text/javascript" src="/js/plugins/uploadFive/jquery.uploadifive.js"></script>
    <style type="text/css">
        .uploadifive-queue-item {
            display: none !important;
        }

    </style>
    <script type="text/javascript">
        //        $(function() {
        //            $(".image-div .js-upload").uploadify(
        //                    {
        //
        //                        buttonText : "选择图片",
        //                        fileObjName : "file",
        //                        fileTypeDesc : "商品图片",
        //                        fileTypeExts : "*.gif; *.jpg; *.png",
        //                        swf : "/js/plugins/uploadify/uploadify.swf",
        //                        uploader : "/product/upload",
        //                        overrideEvents : [ "onUploadSuccess", "onUploadProgress",
        //                            "onSelect" ],
        //                        onUploadSuccess : function(file, data) {
        //                            var $wrapper = this.wrapper;
        //                            var $div = $wrapper.parent('div').parent('div');
        //                            //data = JSON.parse(data);
        //                            $div.find('img').attr('src', data);
        //                            $div.find('input').val(data);
        //                            //$div.find('input').val(data.id);
        //                        }
        //                    });
        //
        //
        //        })
        $(function () {

            $('.image-div .js-upload').uploadifive({
                "removeCompleted": true,
                'auto': true,
                'uploadScript': '/product/upload',
                'fileObjName': 'productImage',
                'buttonText': '上传图片',
                //                'queueID' : 'tip-queue1',//进度条显示位置
                'fileType': 'image',
                'multi': false,
                'fileSizeLimit': 5242880,
                'uploadLimit': 10,
                'queueSizeLimit': 10,
                'onUploadComplete': function (file, data) {
                    $(this).closest(".image-div").children("img").prop("src", data);
                    $(this).closest(".image-div").children("img").next("input").val(data);
                    //$(this).closest("div").closest("div").next("img").prop("src",data);//错误拿法
                    //$(this).closest("div").closest("div").next("img").next("input").val(data);//错误拿法
                    console.debug($(this))
                },
                onFallback: function () {
                    alert("该浏览器无法使用!");
                }
            });
        })

        $(function () {

            var editor = CKEDITOR.replace('desc');

            $('#myTabs a').click(function (e) {
                e.preventDefault()
                $(this).tab('show')
            });

            $("#saveButton").click(function () {
                $("#desc").html(editor.getData());
                $.each($(".col-md-6"), function (index, item) {
                    $(this).find($(".imgInput")).prop("name", "productImages[" + index + "].imagePath");
                    $(this).find($(".sequence")).prop("name", "productImages[" + index + "].sequence");
                    $(this).find($(".cover")).prop("checked", true);
                    $(this).find($(".cover")).prop("name", "productImages[" + index + "].cover");
                })
                $("#productSaveForm").ajaxSubmit(function (data) {
                    if (data.success) {
                        $.messager.confirm("提示", "保存成功", function () {
                            window.location.href = "/product/get";
                        })
                    } else {
                        $.messager.alert("提示", data.message);
                    }
                })
            });

        });

        function changeCatalog(obj) {
            var catalogId = $(obj).val();
            if (catalogId) {
                //重新加载商品的属性面板
                $('#productPropertyPanel').load(
                        '/product/commonPropertyInfo?catalogId='
                        + catalogId);
                //重新加载商品sku
                /* $('#productSkuPanel').load(
                    '/getSkuPropertyPanel.do?catalogId='
                            + catalogId); */
            }
            else {
                //商品属性面板
                $('#productPropertyPanel').empty();
                //sku面板
                $('#productSkuPanel').empty();
            }
        }

        $(function () {
            $(".image-div>div").prop("align", "center");
        })
        $(function () {
            var productId = $("#productId").val();
            if (productId) {
                //重新加载商品的属性面板
                $('#productPropertyPanel').load(
                        '/product/allPropertyInfo?productId='
                        + productId);
            } else {
                //商品属性面板
                $('#productPropertyPanel').empty();
                //sku面板
                $('#productSkuPanel').empty();
            }

        })
        $(function () {
            $("input").prop("disabled", true);
        })
        $(function () {
            $("#productPropertyPanel").find($("input")).prop("disabled",true);
        })

    </script>
</head>
<body>
<div class="container">
<#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
        <#include "../common/menu.ftl">

            <script type="text/javascript">
                $(".in li.add_product").addClass("active");
            </script>
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>商品录入</h3>
            </div>

            <div class="panel panel-default">
                <div id="myTabs">

                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#base" aria-controls="base" role="tab"
                                                                  data-toggle="tab">基本信息</a></li>
                        <li role="presentation"><a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">商品详情</a>
                        </li>
                        <li role="presentation"><a href="#image" aria-controls="image" role="tab"
                                                   data-toggle="tab">商品相册</a></li>
                        <li role="presentation"><a href="#property" aria-controls="property" role="tab"
                                                   data-toggle="tab">商品属性</a></li>
                        <!-- <li role="presentation"><a href="#sku" aria-controls="sku" role="tab" data-toggle="tab">sku</a></li>-->
                    </ul>

                    <!-- 提交商品保存表单 -->
                    <form action="/product/saveOrUpdate" method="post" id="productSaveForm">
                        <input type="hidden" id="productId" name="product.id" value="${(product.id)!}"/>
                        <!-- Tab panes -->

                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="base">
                                <!-- 基本信息的页面 -->
                                <div style="padding-bottom: 10px;padding-left: 10px;padding-right: 10px;padding-top: 10px;">
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>商品名称</label> <input class="form-control"
                                                                           name="product.name"
                                                                           value="${(product.name)!}">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>商品编号</label> <input class="form-control"
                                                                           name="product.code"
                                                                           value="${(product.code)!}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>所属品牌</label>
                                                <select class="form-control" name="product.brand.id" disabled>
                                                    <option value="-1">请选择</option>
                                                <#list brands as brand>
                                                    <option value="${brand.id}"
                                                            <#if brand.id==product.brand.id>selected=true</#if>>${(brand.chineseName)!}</option>
                                                </#list>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>所属分类</label>
                                                <select class="form-control" id="catalogId" disabled name="product.catalog.id"
                                                        onchange="changeCatalog(this)">
                                                    <option value="-1">请选择</option>
                                                <#list catalogs as catalog>
                                                    <option value="${catalog.id}"
                                                            <#if catalog.id==product.catalog.id>selected=true</#if>>${catalog.name}</option>
                                                </#list>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>市场售价</label> <input class="form-control"
                                                                           name="product.marketPrice"
                                                                           value="${(product.marketPrice?c)!}">
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>基础售价</label> <input class="form-control"
                                                                           name="product.basePrice"
                                                                           value="${(product.basePrice?c)!}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>是否上架</label>
                                                <div>
                                                    <label class="radio-inline">
                                                        <input type="radio" value="true" checked>是
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" value="false">否
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>是否推荐</label>
                                                <div>
                                                    <label class="radio-inline"> <input type="radio"
                                                                                        value="true" checked>是
                                                    </label> <label class="radio-inline"> <input type="radio"
                                                                                                 value="false">否
                                                </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label>商品关键字</label>
                                                <input class="form-control" placeholder="以逗号分隔" name="product.keyword"
                                                       value="${(product.keyword)!}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label>商品标签</label>
                                                <textarea class="form-control" rows="3" placeholder="以逗号分隔"
                                                          disabled name="product.impressions">${(product.impressions)!}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="detail">
                                <!-- 商品详情 -->
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <textarea id="desc" name="productDesc.details" class="ckeditor"
                                                      disabled  rows="10">${(productDesc.details)!}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="image">
                                <div style="padding-bottom: 10px; padding-left: 10px; padding-right: 10px; padding-top: 10px;">
                                    <div class="row">
                                    <#list productImages as productImage>
                                        <div class="col-lg-3 col-md-6">
                                            <div class="image-div">
                                                <img id="img1" class="uploadImg" src="${(productImage.imagePath)!}">

                                                <input type="hidden" class="imgInput" name="productImages[0].imagePath"
                                                       value="${(productImage.imagePath)!}">
                                            </div>
                                            <div class="input-group">
                                                <select class="form-control sequence" disabled name="productImages[0].sequence">
                                                    <option value="1" <#if productImage.sequence==1>selected</#if>>1
                                                    </option>
                                                    <option value="2" <#if productImage.sequence==2>selected</#if>>2
                                                    </option>
                                                    <option value="3" <#if productImage.sequence==3>selected</#if>>3
                                                    </option>
                                                    <option value="4" <#if productImage.sequence==4>selected</#if>>4
                                                    </option>
                                                    <option value="5" <#if productImage.sequence==5>selected</#if>>5
                                                    </option>
                                                    <option value="6" <#if productImage.sequence==6>selected</#if>>6
                                                    </option>
                                                    <option value="7" <#if productImage.sequence==7>selected</#if>>7
                                                    </option>
                                                    <option value="8" <#if productImage.sequence==8>selected</#if>>8
                                                    </option>
                                                </select>
                                                <span class="input-group-addon">
                                                    <label>
                                                        <input type="radio" class="productImageCover cover"
                                                               name="productImages[0].cover" value="0">
                                                        <span>封面</span>
                                                    </label>
                                                </span>
                                            </div>
                                        </div>
                                    </#list>
                                    </div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="property">
                                <div style="padding-bottom: 10px; padding-left: 10px; padding-right: 10px; padding-top: 10px;">
                                    <table class="table table-bordered catalog-property-table">
                                        <thead>
                                        <tr>
                                            <th>名称</th>
                                            <th>值</th>
                                        </tr>
                                        </thead>
                                        <tbody id="productPropertyPanel">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- <div role="tabpanel" class="tab-pane" id="sku">
                                <div id="productSkuPanel" style="padding-bottom: 10px; padding-left: 10px; padding-right: 10px; padding-top: 10px;">

                                </div>
                            </div> -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>