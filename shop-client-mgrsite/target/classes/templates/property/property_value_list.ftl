<div class="modal-body">
    <input type="hidden" name="catalogProperty.id" value="${catalogProperty.id}"/>
<#if (catalogProperty.type)==2>
    <div class="form-group">
        <label>
        ${catalogProperty.name}
        </label>
        <div id="valueDiv">
            <#list list as data>
                <div style="height: 50px;">
                    <input type="text" class="form-control" value="${data.value}" style="width: 200px;margin-bottom: 5px;float: left;">
                    <h3 style="float: left;margin: 0;">
                        <span class="label label-primary" style="cursor: pointer;" onclick="deleteValue(this,${data.id})">-</span>
                    </h3>
                </div>
            </#list>
        </div>
        <h3><span class="label label-primary" style="cursor: pointer;" onclick="addValue()">+</span></h3>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="submitValueForm()">关闭</button>
        </div>
    </div>
<#else>
    <div class="form-group">
        <label>
        ${catalogProperty.name}
        </label>
        <div id="valueDiv">
            <#if (list?size>0)>
                <#list list as data>
                    <div class="form-group">
                        <textarea  class="form-control" rows="3" name="value">${data.value}</textarea>
                    </div>
                </#list>
            <#else >
                <div class="form-group">
                    <textarea  class="form-control" rows="3" name="value"></textarea>
                </div>
            </#if>
            <#--<#if list?size<0>

            </#if>-->
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="ValueForm()">关闭</button>
        </div>
    </div>
</#if>
</div>








