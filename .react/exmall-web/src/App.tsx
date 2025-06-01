import Navigation from "./component/Navigation/Navigation.tsx";
import PageContent from "./component/Content/PageContent.tsx";
import {useState} from "react";


const App = () => {
    const [currentPage, setCurrentPage] = useState("Home");

    return (
        <>
            <div className="flex-col">
                <div className="fixed h-[10vh] bg-blue-300">
                    <Navigation />
                </div>

                <div className="pt-[10vh] pl-[var(navi-w)] pr-[var(navi-w)]">
                    <PageContent currentPage={currentPage}/>
                </div>

            </div>
        </>
    )
}

export default App;