window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.ajax = function (data, handler, url = "", type = "POST", dataType = "json") {
    $.ajax({
        type,
        url,
        data,
        dataType,
        success: function (response) {
            if (response["redirect"]) {
                location.href = response["redirect"];
            } else {
                handler(response);
            }
        }
    });
}