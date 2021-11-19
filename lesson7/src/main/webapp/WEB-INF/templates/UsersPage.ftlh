<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->

<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th class="admin">Admin</th>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user-id"></td>
                    <td class="user-login"></td>
                    <td class="user-admin"><input class="button" type="button"></td>
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
            const $admin = $(".users .admin");
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");
            <#if (!user??) || !user.isAdmin()>
                $admin.hide();
            </#if>
            ajax({action: "findAll"},
                function (response) {
                    response["users"].forEach(function (user) {
                        $noData.hide();
                        const tr = $($(".users template").prop("content")).clone();
                        tr.find(".user-id").text(user["id"]);
                        tr.find(".user-login").text(user["login"]);
                        <#if user?? && user.isAdmin()>
                            var value = (user["isAdmin"] === true ? "Disable" : "Enable");
                            tr.find(".user-admin .button").attr("value", value);
                            tr.find(".user-admin .button").attr("userToChange", user["id"]);
                        <#else>
                            tr.find(".user-admin").hide();
                        </#if>
                        tr.find("tr").attr("data-userId", user["id"]);
                        $(".users tbody").append(tr);
                    });
                    $(".users .user-admin .button").click(function () {
                        const newValue = $(this).attr("value") === "Enable" ? "Disable" : "Enable";
                        ajax({
                                action: "changeAdminProperty",
                                userToChange: $(this).attr("userToChange"),
                                newValue: newValue === "Disable"
                            },
                            function (response) {
                                const isAdmin = response["currentAdminProp"];
                                $(this).attr("userToChange", isAdmin);
                            });
                        $(this).attr("value", newValue);
                        return false;
                    });
                });
        })
    </script>
</@c.page>
