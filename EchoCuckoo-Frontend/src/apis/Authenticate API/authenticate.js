import apiClient from "..";

export const registerAPI = async (param = {}) => {
    try {
        const response = await apiClient.post('/users/register', param);
        return response.data; 
    } catch (error) {
        console.error("Register API Error:", error);
        throw error; 
    }
};

export const loginAPI = async (param = {}) => {
    try {
        const response = await apiClient.post('/login', param);
        return response.data;
    } catch (error) {
        console.error("Login API Error:", error);
        throw error;
    }
};

export const logoutAPI = async (param = {}) => {
    try {
        const response = await apiClient.post('/users/logout', param);
        return response.data;
    } catch (error) {
        console.error("Logout API Error:", error);
        throw error;
    }
};
