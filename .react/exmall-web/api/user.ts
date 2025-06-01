// src/api/userApi.ts
import service from './index';

// 用户类型定义
export type User = {
    uid?: number;
    username?: string;
    password?: string; // 密码通常不在前端传输
    phone?: string;
    email?: string;
    avatar?: string; // 头像URL
    isMerchant?: number; // 是否为商户
    shopId?: number;
    // 其他用户字段...
};

// 添加用户
export const addUserApi = (user: User) => {
    return service.post('/api/exmall/admin/v1/user', user);
};

// 更新用户
export const updateUserApi = (user: User) => {
    return service.put(`/api/exmall/admin/v1/user/${user.uid}`, user);
};

// login 用户
export const loginUserApi = async (user: User) => {
    try {
        const response = await service.post('/api/exmall/admin/v1/user/login', user);
        if (response.data.success && response.data.data.token) {
            localStorage.setItem('token', response.data.data.token);
            console.log('登录成功，token已存储');
            alert('登录成功');
        }
        return true;
    } catch (error) {
        console.error('登录失败', error);
        alert('登录失败，请检查用户名和密码');
        throw error;
    }
};

// 获取用户信息
export const getUserInfoApi = async (username: string) => {
    try {
        const response = await service.get(`/api/exmall/admin/v1/user/${username}`);
        return response.data;
    } catch (error) {
        console.error('获取用户信息失败', error);
        throw error;
    }
};

// 注册用户
export const registerApi = async (user: User) => {
    try {
        const response = await service.post('/api/exmall/admin/v1/user', user);
        if (response.data.success) {
            console.log('注册成功');
            alert('注册成功');
        }
        return true;
    } catch (error) {
        console.error('注册失败', error);
        throw error;
    }
};

// 更新用户信息
export const updateUserInfoApi = async (user: User) => {
    try {
        const response = await service.put(`/api/exmall/admin/v1/user`, user, {
            headers: {
                'username': user.username,
            }
        });
        if (response.data.success) {
            console.log('用户信息更新成功');
            alert('用户信息更新成功');
        }
        return true;
    } catch (error) {
        console.error('用户信息更新失败', error);
        throw error;
    }
};

// 用户ID获取商家
export const getMerchantByUserIdApi = async (uid: number) => {
    try {
        const response = await service.get(`/api/exmall/admin/v1/shop/${uid}`);
        return response.data;
    } catch (error) {
        console.error('获取商家信息失败', error);
        throw error;
    }
};