$(document).ready(function() {
    $("#submitButton").click(function() {
        if ($("#urlText").val() == "") {
            alert("Wrong URL!");
            return;
        }

        let request = { url: $("#urlText").val() };
        let jsonBody = JSON.stringify(request);

        $.ajax({
            type: "POST",
            url: "/shorten",
            contentType: "application/json",
            data: jsonBody,
            dataType: "json",
            success: function(result) {
                $("#shortUrl").attr("href", result.shortUrl).text(result.shortUrl);
            }
        });
    });
});
