document.addEventListener('DOMContentLoaded', function () {
    const saveButton = document.getElementById('save_info');
    const usernameInput = document.getElementById('username');
    const addressInput = document.getElementById('address');
    const emailInput = document.getElementById('email');
    const phoneInput = document.getElementById('phone');

    // Lấy thông tin người dùng
    const token = localStorage.getItem('authToken');
    if(token == null){
        window.location.href ="login.html";
    }

    if (token) {
        fetch('http://localhost:8080/api/v1/users/myInfo', {
            method: 'GET',
            headers: {
                'Authorization': token
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data && data.data) {
                const user = data.data;
                usernameInput.value = user.username || '';
                addressInput.value = user.address || '';
                emailInput.value = user.email || '';
                phoneInput.value = user.phone || '';
            }
        })
        .catch(error => console.error('Error fetching user data:', error));
    }

    // Lưu thông tin người dùng
    if (saveButton) {
        saveButton.addEventListener('click', function (event) {
            event.preventDefault(); // Ngăn chặn hành động gửi form mặc định

            const token = localStorage.getItem('authToken');

            if (token) {
                const requestBody = {
                    username: usernameInput.value,
                    address: addressInput.value,
                    email: emailInput.value,
                    phone: phoneInput.value
                };


         
                fetch('http://localhost:8080/api/v1/users/update', {
                    method: 'PUT',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    }
                    ,
                    body: JSON.stringify(requestBody)
                })
                .then(response => {
                    // Kiểm tra phản hồi
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.status === '200') {
                        alert('Thông tin người dùng đã được cập nhật thành công!');
                    } else {
                        alert('Cập nhật thông tin người dùng thất bại: ' + data.message);
                    }
                })
                .catch(error => {
                    console.error('Lỗi khi cập nhật thông tin người dùng:', error);
                });
            } else {
                console.error('Không tìm thấy token trong localStorage');
            }
        });
    } else {
        console.error('Không tìm thấy nút Save');
    }
});




