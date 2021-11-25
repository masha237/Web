<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./commons.ftlh" as c>

<@c.page>
    <#if handle??>
        <#assign type="handle">
        <#assign value=handle>
    <#elseif user_id??>
        <#assign type="id">
        <#assign value=user_id>
    </#if>

    <#if type?? && value??>
        <#assign u = c.findBy(users, type, value)!/>

        <#if u?has_content>
            <div class="user_interpretation">
                <div class="user_block">
                    <div class="username">
                        <@c.userlink u false/>
                    </div>
                    <ul class="info">
                        <li>id: ${u.id}</li>
                        <li>handle: ${u.handle}</li>
                        <li>name: ${u.name}</li>
                    </ul>
                    <img src="/img/no-title.jpg" alt="">
                </div>
            </div>
        <#else>
            <@noSuchUser/>
        </#if>
    <#else>
        <@noSuchUser/>
    </#if>
</@c.page>

<#macro noSuchUser>
    No such user
</#macro>