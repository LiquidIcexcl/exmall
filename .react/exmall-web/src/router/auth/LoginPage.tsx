import React, {useEffect, useRef, useState} from "react";
import {getUserInfoApi, loginUserApi, registerApi} from "../../../api/user.ts";

interface LoginPageProps {
    setIsLogin01: (value: boolean) => void;
}

interface ApiResponse<T> {
    code: string;
    message: string | null;
    data: T;
    requestId: string | null;
    success: boolean;
}

const LoginPage: React.FC<LoginPageProps> = (Props) => {

    const usernameRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);
    const emailRef = useRef<HTMLInputElement>(null);
    const phoneRef = useRef<HTMLInputElement>(null);
    const [login, setLogin] = useState<LoginPageProps>();
    const [register, setRegister] = useState<LoginPageProps>();
    const [userInfo, setUserInfo] = useState<ApiResponse<any>>();
    const [isRegister, setIsRegister] = useState<boolean>(false);

    useEffect( () => {
        if (login) {
            console.log("Login successful:", login);
            console.log("currentUserInfo", localStorage.getItem("userInfo"));
        }
    }, [login]);

    useEffect( () => {
        if (register) {
            setIsRegister(false);
        }
    }, [register]);

    useEffect(() => {
        if (userInfo&&login) {
            localStorage.setItem("username", userInfo.data.username);
            localStorage.setItem("uid", userInfo.data.uid);
            localStorage.setItem("phone", userInfo.data.email);
            localStorage.setItem("email", userInfo.data.email);
            console.log("User info saved to localStorage:", localStorage.getItem("username"));
            console.log("User info saved to localStorage:", localStorage.getItem("uid"));
            console.log("User info saved to localStorage:", localStorage.getItem("email"));
            console.log("User info saved to localStorage:", localStorage.getItem("phone"));
            Props.setIsLogin01(true);
        }
    }, [userInfo,login]);



    const handlerLoginForm= async () => {
        const formData = {
            username: usernameRef.current?.value,
            password: passwordRef.current?.value
        };
        if (!formData.username || !formData.password) {
            alert("Please fill in both username and password.");
            return;
        }
        setLogin(await loginUserApi(formData));
        setUserInfo(await getUserInfoApi(usernameRef.current?.value));
        return
    };

    const handleRegisterForm = async () => {
        const formData = {
            username: usernameRef.current?.value,
            password: passwordRef.current?.value,
            email: passwordRef.current?.value,
            phone: passwordRef.current?.value
        };
        if (!formData.username || !formData.password || !formData.email || !formData.phone) {
            alert("Please fill in all fields.");
            return;
        }
        setRegister(await registerApi(formData));
        return
    }



    return (
        <>
            {isRegister ?
                (
                    <>
                        <div className="flex-col p-[10vh] m-[10vh] h-[70vh] w-[50vh] bg-blue-900 rounded-lg shadow-lg items-center justify-center">

                            <div className="flex items-center justify-center">
                                <p className="text-4xl text-center font-bold size-32 h-[15vh]">Register</p>
                            </div>

                            <input ref={usernameRef} type="text" placeholder="Username" className="mb-4 p-2 rounded-md w-full" />
                            <input ref={passwordRef} type="text" placeholder="Password" className="mb-4 p-2 rounded-md w-full" />
                            <input ref={emailRef} type="text" placeholder="Email" className="mb-4 p-2 rounded-md w-full" />
                            <input ref={phoneRef} type="text" placeholder="Phone" className="mb-4 p-2 rounded-md w-full" />

                            <div className="flex items-center justify-center m-6">
                                <button className="text-center w-[25vh]"
                                        onClick={handleRegisterForm}>
                                    Submit
                                </button>
                            </div>

                            <div className="flex items-center justify-center m-4">
                                <button className="text-2xl text-center w-[25vh]"
                                        onClick={()=> setIsRegister(false)}>
                                    有账号？点击登入
                                </button>
                            </div>
                        </div>
                    </>
                )
                :
                (
                    <>
                        <div className="flex items-center justify-center">
                            <div className="flex-col p-[10vh] m-[10vh] h-[70vh] w-[50vh] bg-blue-900 rounded-lg shadow-lg items-center justify-center">

                                <div className="flex items-center justify-center">
                                    <p className="text-4xl text-center font-bold size-32 h-[15vh]">Login</p>
                                </div>

                                <input ref={usernameRef} type="text" placeholder="Username" className="mb-4 p-2 rounded-md w-full" />
                                <input ref={passwordRef} type="text" placeholder="Password" className="mb-4 p-2 rounded-md w-full" />

                                <div className="flex items-center justify-center m-6">
                                    <button className="text-center w-[25vh]"
                                            onClick={handlerLoginForm}>
                                        Submit
                                    </button>
                                </div>

                                <div className="flex items-center justify-center m-4">
                                    <button className="text-2xl text-center w-[25vh]"
                                            onClick={()=> setIsRegister(true)}>
                                        还没账号？点击注册
                                    </button>
                                </div>
                            </div>
                        </div>
                    </>
                )
            }
        </>
    );
}

export default LoginPage;