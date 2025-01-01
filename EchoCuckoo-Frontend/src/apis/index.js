import axios from 'axios';

// Tạo một instance axios với cấu hình mặc định
const apiClient = axios.create({
  baseURL: 'https://echo-cuckoo-backend.onrender.com/api/v1', // Thay thế bằng URL của API backend
  headers: {
    'Content-Type': 'application/json',
  },
});

// Thêm Interceptor để tự động thêm token từ localStorage
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token'); // Lấy token từ localStorage
    if (token) {
      config.headers.Authorization = `Bearer ${token}`; // Thêm token vào header
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default apiClient;
