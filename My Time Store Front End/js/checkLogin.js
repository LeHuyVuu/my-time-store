document.addEventListener('DOMContentLoaded', function () {
    var token = localStorage.getItem('authToken'); // Lấy token từ localStorage
    console.log(token); // Kiểm tra token trong console

    // Lắng nghe sự kiện click vào biểu tượng giỏ hàng
    document.getElementById('shopping-cart').parentElement.addEventListener('click', function (event) {
        if (token == null) {
            event.preventDefault(); // Ngăn không cho chuyển đến trang giỏ hàng
            window.location.href = 'login.html'; // Chuyển hướng đến trang đăng nhập
        }
    });
});




document.addEventListener("DOMContentLoaded", function () {
    // Kiểm tra xem có JWT token trong localStorage hay không
    const token = localStorage.getItem("authToken");

    if (token) {
        const payload = JSON.parse(atob(token.split('.')[1])); // Giải mã phần payload
        const expirationTime = payload.exp * 1000; // Lấy thời gian hết hạn, chuyển từ giây sang milliseconds
        const currentTime = Date.now(); // Lấy thời gian hiện tại

        if (currentTime >= expirationTime) {
            // Token đã hết hạn
            localStorage.removeItem('authToken'); // Xóa token khỏi localStorage
            window.location.href = 'login.html'; // Chuyển hướng đến trang đăng nhập
        } else {
            // Token còn hạn
            console.log("Token is valid");
        }
        // Token tồn tại, thay đổi trạng thái đăng nhập
        const loginIcon = document.querySelector('.flaticon-user');
        if (loginIcon) {
            loginIcon.parentElement.href = "user-profile.html"; // Liên kết đến trang profile
        }
    } else {
        // Token không tồn tại, người dùng chưa đăng nhập

    }
});
