import axios from 'axios';

// Tạo một instance axios với cấu hình mặc định
const apiClient = axios.create({
  baseURL: 'https://echo-cuckoo-backend.onrender.com/api/v1', // Thay thế bằng URL của API backend
  headers: {
    'Content-Type': 'application/json',
  },
});


// Xuất instance để sử dụng nếu cần thiết
export default apiClient;
