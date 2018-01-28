
<#list properties as property>
    <tr class="productCatalogPropertyValues">
     <td><input hidden  tag="name" value="${property.name}"/>${property.name}</td>
     <td><input  tag="value" value="${property.value}" style="width: 300px;"/></td>
    </tr>
</#list>
<script type="text/javascript">
    $(function () {
        $.each($("#productPropertyPanel tr"),function (index,item) {
            $(this).find($("[tag=name]")).prop("name","productCatalogPropertyValues["+index+"].name");
            $(this).find($("[tag=value]")).prop("name","productCatalogPropertyValues["+index+"].value");
        });
    })
</script>
