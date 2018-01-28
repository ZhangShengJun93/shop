<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小卖部(系统管理平台)</title>
<#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <link type="text/css" rel="stylesheet" href="/js/plugins/uploadFive/uploadifive.css"/>
    <script type="text/javascript" src="/js/plugins/uploadFive/jquery.uploadifive.js"></script>
    <style type="text/css">
        .uploadImg {
            width: 100px;
            height: 100px;
            margin-top: 5px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            //文件上传
            $('#uploadBtn1').uploadifive({
                'auto': true,
                'uploadScript': 'uploadLOGO',
                'fileObjName': 'file',
                'buttonText': '上传品牌LOGO',
                'fileType': 'image',
                'multi': false,
                'fileSizeLimit': 5242880,
                'uploadLimit': 5,
                'queueSizeLimit': 1,
                "removeCompleted": true,
                'onUploadComplete': function (file, data) {
                    $("#uploadImg1").attr("src", data);
                    $("#uploadImage1").val(data);
                },
                onFallback: function () {
                    alert("该浏览器无法使用!");

                }
            });                         
            $(".delete_Btn").click(function () {
                var id = $(this).data("delete");
                $.messager.confirm("提示", "确定删除吗？", function () {

                    $.ajax({
                        url: "/brand_delete?id=" + id,
                        success: function (result) {
                            if (result.success) {
                                $.messager.confirm("温馨提示;", "删除成功", function () {
                                    location.reload();
                                });
                            } else {
                                $.messager.popup(result.message);
                            }
                        }
                    });
                });

                /*  var id = $(this).data("delete");
                  //点击确定的情况下才发送ajax请求
                  if (confirm("确认要删除吗")) {
                     //发送ajax请求，删除数据.
                      $.ajax({
                          url: "/brand_delete?id=" + id,
                          success: function (result) {
                              if (result.success) {
                                  $.messager.confirm("温馨提示;", "删除成功", function () {
                                      location.reload();
                                  });
                              } else {
                                  $.messager.popup(result.message);
                              }
                          }
                      });
                  }*/
            });
            /*给修改按钮添加事件*/
            $(".edit_Btn").click(function () {
                /*//数据回显方式1:
                $("#title").val($(this).data("title"));
                $("#sn").val($(this).data("sn"));
                $("#brandId").val($(this).data("dirid"));*/
                //回显数据方式2:在domain中包装好需要的信息,以json的格式返回来
                var json = $(this).data("json");
                $("#chineseName").val(json.chineseName);
                $("#url").val(json.url);
                $("#uploadImg1").attr("src", json.logo);
                $("#sequence").val(json.sequence);
                $("#description").val(json.description);
                //获取单选框按钮
                $(":radio[name='mods'][value='" + json.mods + "']").prop("checked", "checked");
                //获取json中数据循环
                console.log(json.catalogs)
                for (j = 0; j < json.catalogs.length; j++) {
                    //获取复选框按钮 type="checkbox" name="catIds"
                    /*console.log(json.catalogs[j].id)*/
                    $(":checkbox[name='catIds'][value='" + json.catalogs[j].id + "']").prop("checked", "checked");
                }
                $("#brandId").val(json.id);
                //打开添加窗口
                $("#brandModal").modal("show");
            });
            /*给保存按钮添加事件*/
            $("#saveBtn").click(function () {
                //使用ajaxSubmit提交表单
                $("#editForm").ajaxSubmit({
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $.messager.confirm("提示", "成功!", function () {
                                window.location.reload();
                            });
                        } else {
                            $.messager.alert(data.message)
                        }
                    }
                });
                return false;
            });
            /*给添加绑定事件*/
            $("#addbrandbtn").click(function () {
                //清空表单
                $("#editForm")[0].reset();

                $("#brandId").val("");
                //打开添加窗口
                $("#brandModal").modal("show");
            });
            /*分页条*/
            $('#pagination_container').twbsPagination({
                totalPages: ${pageResult.totalPage},
                startPage: ${pageResult.currentPage},
                visiblePages: 5,
                first: "首页",
                prev: "上一页",
                next: "下一页",
                last: "最后一页",
                onPageClick: function (event, page) {
                    $("#currentPage").val(page);
                    $("#searchForm").submit();
                }
            });
        });
        $("#query").click(function () {
            $("#currentPage").val(1);
            $("#searchForm").submit();
        });


    </script>
</head>

<body>
<input type="hidden" id="productIdHidden"/>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu="product_brand" />
				<#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>品牌管理</h3>
            </div>
            <form id="searchForm" class="form-inline" method="post" action="/brand_list">
                <input type="hidden" name="currentPage" id="currentPage" value="${(qo.currentPage)!''}"/>
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!''}">
                </div>
                <div class="form-group">
                    <button id="query" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    <a href="javascript:void(-1);" class="btn btn-success" id="addbrandbtn">添加品牌</a>
                </div>
            </form>
            <div class="panel panel-default">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>品牌名称</th>
                        <th>品牌网址</th>
                        <th>品牌简述</th>
                        <th>排序</th>
                        <th>显示</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">
                    <#list pageResult.listData as data>
                    <tr>
                        <td>${data.chineseName!""}</td>
                        <td>${data.url}</td>
                        <td><a href="${data.description!""}">
                        ${data.description!""}
                        </a>
                        </td>
                        <td>${data.sequence!""}</td>
                        <td>${data.mods!""}</td>
                        <td>
                            <a href="javascript:void(-1);"
                               data-json='${data.jsonString!""}'
                               class="edit_Btn">修改</a>
                            <a href="javascript:void(-1);" class="delete_Btn" data-delete='${data.id!""}'>删除</a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <div style="text-align: center;" id="pagination_container">
                </div>
            </div>
        </div>
    </div>


    <div id="brandModal" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">品牌编辑</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm" class="form-horizontal" method="post" action="/brand_saveOrupdate" style="margin: -3px 118px">
                        <input id="brandId" type="hidden" name="id" value=""/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">品牌名称</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="chineseName" name="chineseName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">商品分类</label>
                            <div class="col-sm-6">
                            <#list catalogs as item>
                                <div><input type="checkbox" name="catIds" value="${item.id}" }/>${item.name}</div>
                            </#list>
                            <#--  <select class="form-control" id="catalogs" name="catalogsId" style="width: 180px" autocomplate="off" multiple>
                              <#list catalogs as item>
                                  <option value="${item.id}">${item.name}</option>
                              </#list>
                              </select>-->
                            <#-- <script type="text/javascript">
                                  $("#catalogs option[value=${(userinfo.educationBackground.id)!-1}]").attr("selected",true);
                              </script>-->
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">品牌网址</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="url" name="url">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">品牌LOGO</label>
                            <div class="col-sm-6">
                                <div class="col-sm-8">
                                    <div class="idCardItem">
                                        <div>
                                            <a href="javascript:;" id="uploadBtn1">上传</a>
                                        </div>
                                        <img alt="" src="" class="uploadImg" id="uploadImg1" style="width: 5px,height:5px"/>
                                        <input type="hidden" name="logo" id="uploadImage1"/>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">品牌描述</label>
                            <div class="form-group">
                                <textarea id="description" class="form-control" rows="3" name="description"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">排序</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="sequence" name="sequence">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否上线</label>
                            <div class="col-sm-6">
                                <input type="radio" name="mods" value="1">是
                                <input type="radio" name="mods" value="0">否
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="javascript:void(0);" class="btn btn-success" id="saveBtn">保存</a>
                    <a href="javascript:void(0);" class="btn" data-dismiss="modal">关闭</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
