import apiClient from "..";


// Hàm gọi API với token tự động từ interceptor
export const getProducts = (params = {}) => {
  // Thiết lập giá trị mặc định cho page và pageSize
  const defaultParams = {
    page: 1,
    pageSize: 10, // Số sản phẩm mặc định mỗi trang
  };

  // Gộp params mặc định với params được truyền
  const finalParams = { ...defaultParams, ...params };

  // Gửi request với các query params
  return apiClient.get('/products', { params: finalParams });
};

export const getProductById = (id) => {
  // Lấy thông tin sản phẩm theo ID
  return apiClient.get(`/products/${id}`);
};

export const createProduct = (data) => {
  // Tạo sản phẩm mới (yêu cầu request body)
  return apiClient.post('/products', data);
};

export const updateProduct = (id, data) => {
  // Cập nhật thông tin sản phẩm theo ID
  return apiClient.put(`/products/${id}`, data);
};

export const deleteProduct = (id) => {
  // Xóa sản phẩm theo ID
  return apiClient.delete(`/products/${id}`);
};
