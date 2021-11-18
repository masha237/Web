<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/404page.css">
        <link rel="stylesheet" href="/css/user.css">
        <link rel="stylesheet" href="/css/post.css">
        <link rel="stylesheet" href="/css/users.css">

        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <@underlineMenuItem link=item["href"] lable=item["name"]/>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as post>
            <section>
                <div class="header">Post #${post.id}</div>
                <div class="body">
                    <@getPostText post true/>
                </div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>

    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user nameOnly>
<#-- @ftlvariable name="user" type="ru.itmo.web.lesson4.model.User" -->
<#-- @ftlvariable name="nameOnly" type="boolean" -->

    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#else>
        <#switch user.color.name()>
            <#case "BLUE">
                <#assign color="blue">
                <#break>
            <#case "GREEN">
                <#assign color="green">
                <#break>
            <#case "RED">
                <#assign color="red">
                <#break>
            <#default>
                <#assign color="black">
        </#switch>
        <a style="color: ${color}; text-decoration: none" href="/user?handle=${user.handle}">${user.handle}</a>
    </#if>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#macro underlineMenuItem link lable>
    <#if requestLink?? && requestLink?starts_with(link + "/") || requestLink = link>
        <li><a style="border-bottom: 2px solid var(--caption-color)" href=${link}>${lable}</a></li>
    <#else>
        <li><a href=${link}>${lable}</a></li>
    </#if>
</#macro>

<#function findPrev items key id>
    <#assign last="">
    <#list items as item>
        <#if item[key]==id>
            <#return last>
        </#if>
        <#assign last=item>
    </#list>
    <#return last>
</#function>

<#function findNext items key id>
    <#return findPrev(items?reverse key id)>
</#function>

<#macro postsView posts_ reduction>
    <#list posts_?reverse as p>
        <@postView p reduction/>
    </#list>
</#macro>

<#macro postView post reduction>
    <article>
        <#assign u = c.findBy(users, "id", post.user_id)!/>
        <div class="title">${post.title}</div>
        <div class="information">By <@userlink u false/>, 2 days ago, translation</div>
        <div class="body">
            <@getPostText post reduction/>
        </div>
        <ul class="attachment">
            <li>Announcement of <a href="#">Contest</a></li>
        </ul>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>

<#macro getPostText post reduction>
    <#if reduction>
        <#assign len = 250>
        <#if len < post.text?length>
            <#assign text = post.text[0..<len] + "...">
        </#if>
    <#else>
        <#assign text = post.text>
    </#if>
    <#list text?split("\n") as paragraph>
        <p>${paragraph}</p>
    </#list>
</#macro>

<#function filterPostById id>
    <#return posts?filter(post -> post.user_id == id)>
</#function>

<#function postsCount id>
    <#assign count = 0>
    <#list filterPostById(id) as post>
        <#assign count++>
    </#list>
    <#return count>
</#function>