<#-- @ftlvariable name="enterForm" type="ru.itmo.wp.lesson8.form.UserCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice-box">
        <div class="header">Create notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="text-input-label">
                    Enter your text message:
                </div>
                <div class="text-input">
                    <textarea id="text" name="content"></textarea>
                </div>
                <div class="button-field">
                    <input type="submit" class="send-button" value="Create">
                </div>
                <@c.error "noticeCreationForm.content"/>
            </form>
        </div>
    </div>
</@c.page>
