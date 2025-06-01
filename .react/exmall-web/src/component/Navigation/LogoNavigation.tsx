import React from "react";

const LogoNavigation: React.FC = () => {
    return (
        <div className="logo-navigation">
            <a href="/" className="logo-link">
                <img src="/ExMallIcon.png" alt="Exmall Logo" className="logo-image h-36" />
            </a>
        </div>
    );
}

export default LogoNavigation;