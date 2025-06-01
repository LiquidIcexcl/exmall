import React from 'react';
import UserPageNavigation from "./UserPageNavigation.tsx";
import LogoNavigation from "./LogoNavigation.tsx";
import SearchNavigation from "./SearchNavigation.tsx";

const Navbar: React.FC = () => {
    return (
        <>
            <div className="flex items-center justify-start w-full h-[10vh]">
                <div className="flex items-center justify-start w-[20vw]">
                    <LogoNavigation />
                </div>

                <div className="flex items-center justify-center w-[60vw]">
                    <SearchNavigation />
                </div>

                <div className="flex items-center justify-end w-[20vw] pr-6">
                    <UserPageNavigation />
                </div>

            </div>
        </>
    )
};

export default Navbar;