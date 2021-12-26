<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">My articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hidden property options</th>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="article_id"></td>
                    <td class="article_title"></td>
                    <td class="hidden_prop"><input class="button" type="button"></td>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");

            ajax({
                    action: "findAllByUserId"
                },
                function (response) {
                    response["myArticles"].forEach(function (article) {
                        $noData.hide();
                        const tr = $($(".articles template").prop("content")).clone();
                        tr.find(".article_id").text(article["id"]);
                        let title = article["title"];
                        if (title.length > 50) {
                            title = title.substring(0, 50) + "articleId...";
                        }
                        tr.find(".article_title").text(title);
                        const value = (article["hidden"] === true ? "Show" : "Hide");
                        tr.find(".hidden_prop .button").attr("value", value);
                        tr.find(".hidden_prop .button").attr("articleId", article["id"]);
                        $(".articles tbody").append(tr);
                    });
                    $(".articles .hidden_prop .button").click(function () {
                        const newValue = $(this).attr("value") === "Show" ? "Hide" : "Show";
                        ajax(
                            {
                                action: "changeHiddenProp",
                                articleId: $(this).attr("articleId"),
                                newValue: newValue === "Show"
                            },
                            function (response) {
                                const hide = response["currentHidProp"];
                                $(this).attr("articleId", hide);
                            });
                        $(this).attr("value", newValue);
                        return false;
                    });

            });
        })
    </script>
</@c.page>