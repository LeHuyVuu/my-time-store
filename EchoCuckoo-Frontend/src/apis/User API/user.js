import apiClient from "..";

export const getCurrentUser = () => {
    return apiClient.post('/users/myInfo'); // Sử dụng token từ Interceptor
  };