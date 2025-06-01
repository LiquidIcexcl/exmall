import React, {useEffect} from "react";
import LoginPage from "../auth/LoginPage.tsx";
import {updateUserInfoApi} from "../../../api/user.ts";

interface UserPageProps {
    currentPage?: string;
    isLogin: boolean;
}

const UserPage: React.FC<UserPageProps> = (Props) => {

    let { isLogin } = Props;
    const [isLogin01, setIsLogin01] = React.useState<boolean>(isLogin);
    const [isOption, setIsOption] = React.useState<boolean>(false);
    const passwordRef = React.useRef<HTMLInputElement>(null);
    const emailRef = React.useRef<HTMLInputElement>(null);
    const phoneRef = React.useRef<HTMLInputElement>(null);

    useEffect(() => {
        setIsLogin01(localStorage.getItem("token") !== null && localStorage.getItem("token") !== undefined && localStorage.getItem("token") !== "");
        console.log("UserPage isLogin:", isLogin01);
        setIsLogin01(true);
    }, [localStorage.getItem("token")]);

    useEffect(() => {
        setIsLogin01(isLogin01)
    }, [isLogin01]);

    const handleLoginOut = () => {
        localStorage.removeItem("username");
        localStorage.removeItem("token");
        localStorage.removeItem("password");
        setIsLogin01(false);
    };

    const handleUpdateUserInfo = async (onlyUpdateOne:String) => {
        if (onlyUpdateOne == "password") {
            const formData = {
                username: localStorage.getItem("username"),
                password: passwordRef.current?.value
            };
            if (!formData.username || !formData.password) {
                alert("Please fill in password.");
                return;
            }
            if(await updateUserInfoApi(formData)) {
                localStorage.setItem("password", formData.username);
            }
            return;
        }
        if (onlyUpdateOne == "email") {
            const formData = {
                username: localStorage.getItem("username"),
                email: emailRef.current?.value
            };
            if (!formData.username || !formData.email) {
                alert("Please fill in email.");
                return;
            }
            if(await updateUserInfoApi(formData)){
                localStorage.setItem("email", formData.email);
            }
            return
        }
        if (onlyUpdateOne == "phone") {
            const formData = {
                username: localStorage.getItem("username"),
                phone: phoneRef.current?.value
            };
            if (!formData.username || !formData.phone) {
                alert("Please fill in phone.");
                return;
            }
            if(await updateUserInfoApi(formData)) {
                localStorage.setItem("phone", formData.phone);
            }
            return
        }
    }

    return (
        <>
            {  !isLogin01 ?
                (
                    <>
                        <LoginPage setIsLogin01={setIsLogin01}/>
                    </>
                ) :
                (
                    <>
                        <div className="flex items-center justify-center">
                            <div className="flex-col p-[10vh] m-[5vh] h-[70vh] w-[50vh] bg-blue-900 rounded-lg shadow-lg items-center justify-center">
                                <div className="flex-col items-center justify-center">
                                    {/*<img  src="/exmall-logo.png" alt="Exmall Logo" className="w-[20vh] h-[10vh] justify-self-center" />*/}
                                    <p className="text-white text-center font-bold size-48 h-[4vh] w-auto">你好，{localStorage.getItem("username")}!</p>
                                    <p className="text-gray-400 text-center font-bold size-48 h-[4vh] w-auto"
                                        onClick={()=>setIsOption(!isOption)}>账号设置</p>
                                </div>

                                <div className="flex items-center justify-center">
                                    <button className="text-white text-center font-bold size-48 h-[10vh] m-4">查看我的订单</button>
                                </div>

                                <div className="flex items-center justify-center">
                                    <button className="text-white text-center font-bold size-48 h-[10vh] m-4">查看购物车</button>
                                </div>

                                <div className="flex items-center justify-center">
                                    <button className="text-white text-center font-bold size-48 h-[10vh] m-4"
                                        onClick={handleLoginOut}>
                                            退出登入
                                    </button>
                                </div>

                            </div>
                            {isOption && (
                                <div className="flex-col p-[10vh] m-[5vh] h-[70vh] w-[50vh] bg-blue-900 rounded-lg shadow-lg items-center justify-center">
                                    <p className="text-white text-center font-bold size-48 h-[4vh] w-auto pt-4">账号设置</p>

                                    <div className="flex items-center justify-center pt-8">
                                        <input ref={passwordRef} type="password" className="w-[15vh] mr-4 bg-blue-300"/>
                                        <button className="text-sm text-gray-400 text-center size-48 font-bold h-[5vh] w-auto"
                                                onClick={()=>handleUpdateUserInfo("password")}>修改密码</button>
                                    </div>

                                    <div className="flex items-center justify-center pt-4">
                                        <input ref={emailRef} type="text" className="w-[15vh] mr-4 bg-blue-300"/>
                                        <button className="text-gray-400 text-center font-bold size-48 h-[5vh] w-auto"
                                                onClick={()=>handleUpdateUserInfo("email")}>修改邮箱</button>
                                    </div>

                                    <div className="flex items-center justify-center pt-4">
                                        <input ref={phoneRef} type="text" className="w-[15vh] mr-4 bg-blue-300"/>
                                        <button className="text-gray-400 text-center font-bold size-48 h-[5vh] w-auto"
                                                onClick={()=>handleUpdateUserInfo("phone")}>修改手机</button>
                                    </div>

                                </div>
                            )}
                        </div>


                    </>
                )
            }
        </>
    );
}

export default UserPage;