import React from "react";

interface SearchNavigationProps {
    setCurrentPage: (value: string) => void;
}

const SearchNavigation: React.FC<SearchNavigationProps> = ({setCurrentPage}) => {
    return (
        <div className="search-navigation" onClick={() => setCurrentPage("SearchNavigation")}>
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