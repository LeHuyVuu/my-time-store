$(document).ready(function () {
    $('#login-form').submit(function (event) {
        event.preventDefault();

        var loginForm = {
            username: $('#name').val(),
            password: $('#password').val()
        };



        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/v1/login",
            contentType: "application/json",
            data: JSON.stringify(loginForm),
            success: function (response) {
                console.log("Server response:", response); // Kiểm tra phản hồi từ server
                if (response.status === "200") {
                    var token = response.data;
                    console.log(token);
                    localStorage.setItem('authToken', token);
                    alert("Login Successful");
                    window.location.href = "shop.html";
                    // Redirect or update UI
                } else {
                    alert("Login Failed: " + response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error("Error details:", xhr.responseText); // Kiểm tra chi tiết lỗi
                alert("Error: " + xhr.responseText);
            }
        });
    });
});
