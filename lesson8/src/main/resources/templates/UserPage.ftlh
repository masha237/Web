<#import "./commons.ftlh" as c>

<@c.page>
    <#if userView??>
        <div class="user_interpretation">
            <div class="user_block">
                <ul class="info">
                    <li>id: ${userView.id}</li>
                    <li>handle: ${userView.login}</li>
                    <li>Creation time: ${userView.creationTime}</li>
                </ul>
            </div>
        </div>
    <#else>
        <@noSuchUser/>
    </#if>
</@c.page>

<#macro noSuchUser>
    No such user
</#macro>