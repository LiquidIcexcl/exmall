import React from 'react';
import UserPageNavigation from "./UserPageNavigation.tsx";
import LogoNavigation from "./LogoNavigation.tsx";
import SearchNavigation from "./SearchNavigation.tsx";

interface NavbarProps {
    setCurrentPage: (value: string) => void;
    isLogin?: boolean;
}

const Navbar: React.FC<NavbarProps> = ({setCurrentPage, isLogin}) => {
    return (
        <>
            <div className="flex items-center justify-start w-full h-[10vh]">
                <div className="flex items-center justify-start w-[20vw]">
                    <LogoNavigation />
                </div>

                <div className="flex items-center justify-center w-[60vw]">
                    <SearchNavigation setCurrentPage={setCurrentPage} />
                </div>

                <div className="flex items-center justify-end w-[20vw] pr-6">
                    <UserPageNavigation setCurrentPage={setCurrentPage} isLogin={isLogin} />
                </div>

            </div>
        </>
    )
};

export default Navbar;