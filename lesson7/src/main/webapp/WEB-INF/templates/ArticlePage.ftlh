<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="add-article form-box">
        <div class="header">Create an article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="addArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title">
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text"></textarea>
                    </div>
                </div>
                <div class="button-field">
                    <input type="submit" class="send-button" value="Create article">
                </div>
                <div class="error">
                    ${error!}
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".add-article form").submit(function () {
                let error = $(this).find(".error");

                ajax({
                        "title": $(this).find("input[name='title']").val(),
                        "text": $(this).find("textarea[name='text']").val(),
                        "action": "addArticle"
                    },function (response) {
                        if (response["error"]) {
                            error.text(response["error"]);
                        }
                    });
                return false;
            });
        })
    </script>
</@c.page>
