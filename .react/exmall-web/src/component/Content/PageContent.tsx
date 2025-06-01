import React from "react";
import Home from "../../router/home/Home.tsx";
import UserPage from "../../router/public/UserPage.tsx";
import MerchantPages from "../../router/auth/MerchantPages.tsx";

interface PageContentProps {
    currentPage: string;
    isLogin?: boolean;
}

const PageContent: React.FC<PageContentProps> = ({currentPage, isLogin}) => {
    return (
        <div className="page-content flex flex-col items-center justify-start w-full h-screen bg-blue-100 p-4">
            <h2 className="">
                {currentPage ? `Current Page: ${currentPage}` : "Welcome to Exmall Web!"}
            </h2>
            {
                currentPage === "Home" && <Home />
            }
            {
                currentPage === "SearchNavigation" && <div className="search-navigation">Search Navigation Component</div>
            }
            {
                currentPage === "UserPage" && <UserPage isLogin={isLogin} />
            }
            {
                currentPage === "MerchantPage" && <MerchantPages isLogin={isLogin} />
            }
            {
                currentPage === "LoginPage" && <div className="login-page">Login Page Component</div>
            }
        </div>
    );
}

export default PageContent;