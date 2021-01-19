<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- ${tableComment!} -->
<mapper namespace="${packagePath}.${daoName}">

    <sql id="queryParam">

    <#list columns as column>
        <if test="${column.beautifyColumnName} != null and ${column.beautifyColumnName} != ''">
            AND a.${column.columnName} = <#noparse>#</#noparse>{${column.beautifyColumnName}}   <!--${column.comments!}-->
        <#--   AND a.${column.columnName} = ${"#"}{${column.beautifyColumnName}}   <!--${column.comments!}-->
        <#--此处的${"#"}是为了输出# 而不被认为是#{}指令、后期可以再找其他方式实现-->
        </if>
    </#list>
    </sql>

    <!--查询一批数据的查询-->
    <select id="selectList" resultType="${packagePath}.${entityName}">
        select
        <#list columns as column>
            <#if column_has_next>
                a.${column.columnName} as ${column.beautifyColumnName},  <!--${column.comments!}-->
            <#else>
                a.${column.columnName} as ${column.beautifyColumnName}  <!--${column.comments!}-->
            </#if>
        </#list>
        from ${tableName} a
        where 1 = 1
        <include refid="queryParam" />
    </select>
    <!--插入-->
    <insert id="insert" parameterType="${packagePath}.${entityName}">
        insert into ${tableName}(
    <#list columns as column>
        <#if column_has_next>
            ${column.columnName},
        <#else>
            ${column.columnName}
        </#if>
    </#list>
        )values(
    <#list columns as column>
        <#if column_has_next>
            <#noparse>#</#noparse>{${column.beautifyColumnName}},  <!--${column.comments!}-->
        <#else>
            <#noparse>#</#noparse>{${column.beautifyColumnName}}  <!--${column.comments!}-->
        </#if>
    </#list>
        )
    </insert>
    <!--更新-->
    <update id="update" parameterType="${packagePath}.${entityName}">
        update ${tableName} a
        set
    <#list columns as column>
        <#if column_has_next>
           a.${column.columnName} = <#noparse>#</#noparse>{${column.beautifyColumnName}},   <!--${column.comments!}-->
        <#else>
            a.${column.columnName} =  <#noparse>#</#noparse>{${column.beautifyColumnName}}  <!--${column.comments!}-->
        </#if>
    </#list>
        where 1=1
    <#list columns as column>
        <#if (column.pkName)??>
            AND a.${column.columnName} = <#noparse>#</#noparse>{${column.beautifyColumnName}}  <!--${column.comments!}-->
        </#if>
    </#list>
    </update>
    <!--更新不为空的数据-->
    <update id="updateNotEmpty" parameterType="${packagePath}.${entityName}">
        update ${tableName} a
        <set>
        <#list columns as column>
            <if test="${column.beautifyColumnName}!=null">
                a.${column.columnName} = <#noparse>#</#noparse>{${column.beautifyColumnName}},  <!--${column.comments!}-->
            </if>
        </#list>
        </set>
        where 1=1
        <#list columns as column>
            <#if (column.pkName)??>
            AND a.${column.columnName} = <#noparse>#</#noparse>{${column.beautifyColumnName}}   <!--${column.comments!}-->
            </#if>
        </#list>
    </update>
</mapper>