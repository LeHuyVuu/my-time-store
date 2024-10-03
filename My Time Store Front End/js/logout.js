
// Hàm xử lý sự kiện nhấp chuột vào nút "Log out"

document.addEventListener('DOMContentLoaded', function () {

    function handleLogout() {
        var token = localStorage.getItem('authToken');
        console.log(token);
        // Kiểm tra nếu token tồn tại
        if (token) {
            // Tạo một đối tượng chứa token
            var requestBody = {
                token: token
            };

            fetch('http://localhost:8080/api/v1/logout', {
                method: 'POST', // Phương thức HTTP
                headers: {
                    'Content-Type': 'application/json' // Đặt loại nội dung là JSON
                },
                body: JSON.stringify(requestBody) // Chuyển đổi đối tượng thành chuỗi JSON
            })
                .then(function (response) {
                    if (response.ok) {
                        console.log('Logout successful');
                        // Xóa token khỏi localStorage sau khi logout thành công
                        localStorage.removeItem('authToken');
                        // Chuyển hướng người dùng về trang đăng nhập
                        window.location.href = 'login.html';
                    } else {
                        console.error('Logout failed');
                    }
                })
                .catch(function (error) {
                    console.error('Error:', error);
                });
        } else {
            console.error('No token found');
        }
    }

    // Gán sự kiện click cho nút "Log out"
    document.getElementById('logout').addEventListener('click', handleLogout);
});
