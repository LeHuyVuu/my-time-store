
// ********** REGISTER *************** //

$(document).ready(function () {
    $('#registrationForm').submit(function (event) {
        event.preventDefault(); // Ngăn form gửi đi mặc định

        // Thu thập dữ liệu từ form
        var formData = {
            userName: $('#username').val(),
            phone: $('#phone').val(),
            address: $('#address').val(),
            password: $('#password').val(),
            confirm: $('#confirm').val(),
            email: $('#email').val()
        };

        // Kiểm tra xác nhận mật khẩu
        if (formData.password !== formData.confirm) {
            alert("Passwords do not match!");
            return;
        }

        // Gửi dữ liệu qua AJAX
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/v1/users/register",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function (response) {
                alert("Registration successful!");
                window.location.href = "shop.html";
            },
            error: function (xhr, status, error) {
                alert("Error: " + xhr.responseText);
            }
        });
    });
});
