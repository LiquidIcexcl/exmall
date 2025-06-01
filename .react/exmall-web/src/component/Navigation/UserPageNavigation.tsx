import React from "react";

interface UserPageNavigationProps {
    setCurrentPage: (value: string) => void;
    isLogin?: boolean;
}

{

}

const UserPageNavigation: React.FC<UserPageNavigationProps> = ({setCurrentPage, isLogin}) => {
    return (
        <>
            <div className="flex items-center justify-end w-[30vh] h-full">
                <button className="flex h-auto w-[12vh] m-4 justify-center"
                        onClick={() => setCurrentPage("MerchantPage")}>
                    <p className="text-sm text-center text-white"> 商家中心</p>
                </button>
                <button className="flex h-auto w-[12vh] justify-center"
                        onClick={() => setCurrentPage("UserPage")}>
                    <p className="text-sm text-center text-white"> 个人中心</p>
                </button>
            </div>

        </>

    );
}


export default UserPageNavigation;