import React from "react";

const SearchNavigation: React.FC = () => {
    return (
        <div className="search-navigation">
            <input
                type="text"
                placeholder="Search for products..."
                className="search-input"
            />
            <button className="search-button">Search</button>
        </div>
    );
}

export default SearchNavigation;