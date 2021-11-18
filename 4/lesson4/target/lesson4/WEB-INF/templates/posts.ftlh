<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id??>
        <#assign value=user_id>
    </#if>
    <#if value?has_content>
        <#assign userPosts = c.filterPostById(value)/>
        <#if userPosts?has_content>
            <@c.postsView userPosts true/>
        <#else>
            <@noPosts/>
        </#if>
    <#else>
        <@noPosts/>
    </#if>
</@c.page>

<#macro noPosts>
    No posts
</#macro>
