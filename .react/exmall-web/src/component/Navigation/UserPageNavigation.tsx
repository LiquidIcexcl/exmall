import React from "react";

const UserPageNavigation: React.FC = () => {
    return (
        <button>
            <a href="/user" className="flex items-center justify-center w-full h-full">
                <img src="/UserIcon.png" alt="User Icon" className="h-8 w-8" />
            </a>
            <span className="sr-only">User Page</span>
        </button>
    );
}


export default UserPageNavigation;