<#-- @ftlvariable name="enterForm" type="ru.itmo.wp.lesson8.form.UserCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice-box form-box">
        <div class="header">Create notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="message">Enter your text message:</label></div>
                    <div class="value">
                        <textarea id="text" name="content"></textarea>
                    </div>

                    <@c.error "noticeCreationForm.content"/>
                </div>
                <div class="button-field">
                    <input type="submit" class="send-button" value="Create">
                </div>
            </form>
        </div>
    </div>
</@c.page>
