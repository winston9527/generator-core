package ${packagePath};

<#-- 引入所需要的类-->
<#list importList as import>
import ${import};
</#list>
/**
 * @Description ${tableComment!}
 * @author ${author}
 * @version 1.0  ${.now}
 */
public class ${entityName} {

    <#list columns as column>
    /**
     * ${column.comments!}
     */
     private ${column.javaType!} ${column.beautifyColumnName};
    </#list>

    <#list columns as column>
    /**
     * 设置${column.comments!}
     */
    public void set${column.beautifyColumnName?cap_first}(${column.javaType!} ${column.beautifyColumnName}){
        this.${column.beautifyColumnName} = ${column.beautifyColumnName};
    }
    /**
     * 获取${column.comments!}
     */
    public ${column.javaType!} get${column.beautifyColumnName?cap_first}(){
        return  this.${column.beautifyColumnName};
    }
    </#list>
}
