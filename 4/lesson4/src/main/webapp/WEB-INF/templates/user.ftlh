<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

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
                        <#assign cnt = c.postsCount(u.id)>
                        <li>posts: <a href="/posts?user_id=${u.id}">${cnt}</a></li>
                    </ul>
                    <img src="/img/no-title.jpg" alt="">
                </div>
                <div class="arrows">
                    <#assign prevUser=c.findPrev(users, type, value)!>
                    <#assign nextUser=c.findNext(users, type, value)!>
                    <#if prevUser=="">
                        <p style="color: gray;">←</p>
                    <#else>
                        <a href="/user?handle=${prevUser.handle}">←</a>
                    </#if>
                    <#if nextUser=="">
                        <p style="color: gray;">→</p>
                    <#else>
                        <a href="/user?handle=${nextUser.handle}">→</a>
                    </#if>
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
