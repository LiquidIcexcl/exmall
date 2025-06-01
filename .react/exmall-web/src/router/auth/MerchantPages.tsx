import React, { useEffect, useState } from "react";
import { getUserInfoApi, updateUserInfoApi } from "../../../api/user.ts";

interface MerchantPagesProps {}

interface UserData {
    isMerchant: number;
    // 其他用户信息字段
}

interface ApiResponse<T> {
    code: string;
    message: string | null;
    data: T;
    requestId: string | null;
    success: boolean;
}

const MerchantPages: React.FC<MerchantPagesProps> = () => {
    const [loading, setLoading] = useState(true);
    const [isMerchant, setIsMerchant] = useState(localStorage.getItem("isMerchant"));
    const [error, setError] = useState<string | null>(null);
    const [response, setResponse] = useState<ApiResponse<UserData> | null>(null);

    useEffect(() => {
        setIsMerchant(localStorage.getItem("isMerchant") === "true" ? 1 : 0);
        console.log(isMerchant);
    }, []);

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const username = localStorage.getItem("username");
                if (!username) {
                    throw new Error("用户未登录");
                }

                const response = await getUserInfoApi(username);
                if (response.success && response.data) {
                    const isMerchantValue = response.success;
                    setIsMerchant(isMerchantValue?1:0);
                    localStorage.setItem("isMerchant", isMerchantValue);
                } else {
                    throw new Error("获取用户信息失败");
                }
            } catch (err: any) {
                setError(err.message || "未知错误");
                console.error("获取用户信息失败", err);
            } finally {
                setLoading(false);
            }
        };

        fetchUserInfo();
    }, []);

    const beMerchant = async () => {
        try {
            setLoading(true);
            const username = localStorage.getItem("username");
            if (!username) {
                throw new Error("用户未登录");
            }

            const formData = {
                username,
                isMerchant: 1
            };

            const success = await updateUserInfoApi(formData);
            if (success) {
                setIsMerchant(true);
                localStorage.setItem("isMerchant", String(1));
                alert("恭喜，您已成功成为商户！");
            } else {
                alert("申请商户失败，请稍后重试");
            }
        } catch (err: any) {
            alert("申请商户出错: " + err.message);
            console.error("申请商户失败", err);
        } finally {
            setLoading(false);
        }
    };

    if (loading) {
        return <div>加载中...</div>;
    }

    if (error) {
        return <div className="text-red-500">{error}</div>;
    }

    return (
        <div className="max-w-3xl mx-auto">
            {isMerchant ? (
                <div className="flex-col bg-white rounded-lg shadow-lg w-[80vh] h-[80vh]">
                    <h1 className="text-black text-sm font-bold mb-4">商户页面</h1>
                    <p className="mb-4">欢迎来到商户中心，您可以在这里管理商品和订单。</p>
                    {/* 商户功能组件 */}
                </div>
            ) : (
                <div className="bg-blue-200 rounded-lg shadow-lg w-[80vh] h-[80vh]">
                    <h1 className="text-2xl font-bold mb-4">非商户用户</h1>
                    <p className="mb-4">您还不是商户，无法访问商户功能。</p>
                    <button
                        onClick={beMerchant}
                        disabled={loading}
                        className="bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded transition duration-200"
                    >
                        {loading ? "申请中..." : "申请成为商户"}
                    </button>
                </div>
            )}
        </div>
    );
};

export default MerchantPages;