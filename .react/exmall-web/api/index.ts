import axios, {
    // type InternalAxiosRequestConfig,
    // type AxiosResponse
} from 'axios';

const service = axios.create({
    baseURL: import.meta.env.DEV ? '' : 'http://localhost:8002',
    timeout: 10000,
});

// // 使用 InternalAxiosRequestConfig 类型
// service.interceptors.request.use(
//     (config: InternalAxiosRequestConfig) => {
//         const token = localStorage.getItem('token');
//         if (token) {
//             config.headers = config.headers || {}; // 确保 headers 存在
//             config.headers['Authorization'] = `Bearer ${token}`;
//         }
//         return config;
//     },
//     (error) => {
//         console.error('请求错误:', error);
//         return Promise.reject(error);
//     }
// );

// // 响应拦截器保持不变
// service.interceptors.response.use(
//     (response: AxiosResponse) => response.data,
//     (error) => {
//         console.error('响应错误:', error);
//         return Promise.reject(error);
//     }
// );

export default service;