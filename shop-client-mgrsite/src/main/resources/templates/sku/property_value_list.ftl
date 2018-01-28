<div class="modal-body">

		<input type="hidden" name="skuProperty.id" value="${skuProperty.id}"/>
	  <div class="form-group">
	  	<label>
        ${skuProperty.name}
        </label> 
		  <#if skuProperty.type==0>
	  	<div id="valueDiv">
		  	<#list list as data>
                <div style="height: 40px;">
					<input type="text"
                           class="form-control"
                           value="${data.value}"
                           style="width: 200px;margin-bottom: 5px;float: left;">
				<h3 style="float: left;margin: 0;">
					<span class="label label-primary"
                          style="cursor: pointer;"
                          onclick="deleteValue(this,${data.id})">-</span>
				</h3>
				</div>
            </#list>
        </div>
          <#else >
		  <textarea type="text"
                 class="form-control"
                 value=""
                 style="width: 200px;margin-bottom: 5px;float: left;">
          </#if>
          <h3><span class="label label-primary" style="cursor: pointer;" onclick="addValue()">+</span></h3>
	  	<div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="submitValueForm()">关闭</button>
		</div>
	  </div>
	
</div>


