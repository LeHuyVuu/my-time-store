document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const currentPasswordInput = document.getElementById("currentPassword");
    const newPasswordInput = document.getElementById("newPassword");
    const confirmPasswordInput = document.getElementById("confirmPassword");

    form.addEventListener("submit", async (event) => {
        event.preventDefault(); // Ngăn chặn việc gửi form mặc định

        // Kiểm tra xem mật khẩu mới và xác nhận mật khẩu mới có giống nhau không
        if (newPasswordInput.value !== confirmPasswordInput.value) {
            alert("Mật khẩu mới và xác nhận mật khẩu không khớp.");
            return;
        }

        const passwordRequest = {
            currentPassword: currentPasswordInput.value,
            newPassword: newPasswordInput.value
        };

        try {
            const response = await fetch("http://localhost:8080/api/v1/users/updatePassword", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + localStorage.getItem("authToken") // Thêm token nếu có
                },
                body: JSON.stringify(passwordRequest)
            });

            const data = await response.json();

            if (response.ok) {
                alert("Mật khẩu đã được cập nhật thành công!");
                form.reset(); // Đặt lại form
            } else {
                alert(data.message || "Có lỗi xảy ra. Vui lòng thử lại.");
            }
        } catch (error) {
            console.error("Lỗi:", error);
            alert("Có lỗi xảy ra. Vui lòng thử lại.");
        }
    });
});
