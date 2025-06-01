import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'
import type {RequestHandler} from "express";

// 定义类型来规范传递的对象
type ProxyApp = {
  use: (fn: RequestHandler) => void;
};

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    tailwindcss(),
    react()
  ],
  server: {
    port:5173,
    host:'localHost',
    open:true,
    proxy: {
      // 匹配所有以/api开头的请求
      '/api/exmall/admin': {
        target: 'http://localhost:8002', // 后端服务器地址
        changeOrigin: true, // 修改请求头中的Origin
        // rewrite: (path) => path.replace(/^\/api/, ''), // 重写路径，去除/api前缀
        secure: false, // 允许非HTTPS后端
        ws: true, // 支持WebSocket代理
      },
    },
  },
})

