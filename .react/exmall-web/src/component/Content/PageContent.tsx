import React from "react";

interface PageContentProps {
    currentPage: string;
}

const PageContent: React.FC<PageContentProps> = ({currentPage}) => {
    return (
        <div className="page-content">
            <h2 className="text-center text-gray-500 h-600">
                {currentPage ? `Current Page: ${currentPage}` : "Welcome to Exmall Web!"}
            </h2>
        </div>
    );
}

export default PageContent;