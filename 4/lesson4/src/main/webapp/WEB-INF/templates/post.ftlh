<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if post_id??>
        <#assign value=post_id>
    </#if>
    <#if post_id??>
        <#assign post = c.findBy(posts, "id", post_id)!/>
        <#if post?has_content>
            <@c.postView post false/>
        <#else>
            <@noSuchPost/>
        </#if>
    <#else>
        <@noSuchPost/>
    </#if>
</@c.page>

<#macro noSuchPost>
    No such post
</#macro>
