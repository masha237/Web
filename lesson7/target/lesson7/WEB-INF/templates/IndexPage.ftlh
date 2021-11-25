<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
                <ul class="attachment">
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                    <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
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
        </template>
        <article class="noData">
            There is 0 articles.
        </article>
    </div>
    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Loading...");

            ajax({
                action: "findAllArticlesVisible",
                url: "/index"
            }, function (response) {
                if (response["articleViews"].length !== 0) {
                    $noData.hide();
                    response["articleViews"].forEach(function (articleView) {
                        const article = $($(".articles template").prop("content")).clone();
                        article.find(".title").text(articleView["article"].title);
                        article.find(".body").text(articleView["article"].text);
                        article.find(".creationTime").text(articleView["article"].creationTime);
                        article.find(".information").text("By " + articleView["user"].login);
                        $(".articles").append(article);
                    });
                } else {
                    $noData.text("There are no article.");
                }
            });
        })
    </script>
</@c.page>
