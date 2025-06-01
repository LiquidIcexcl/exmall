import Navigation from "./component/Navigation/Navigation.tsx";
import PageContent from "./component/Content/PageContent.tsx";
import {useEffect, useState} from "react";


const App = () => {
    const [currentPage, setCurrentPage] = useState("Home");
    const [isLogin, setIsLogin] = useState(false);

    useEffect(()=>{
        if(localStorage.getItem("token")){
            setIsLogin(true);
        }
    },[localStorage.getItem("token")]);


    return (
        <>
            <div className="flex-col items-start justify-center w-screen h-screen bg-blue-200">
                <div className="fixed top-0 h-[10vh] bg-blue-300">
                    <Navigation setCurrentPage={setCurrentPage}/>
                </div>

                <div className="pt-[10vh] top-0">
                    <PageContent currentPage={currentPage} isLogin={isLogin} />
                </div>
            </div>
        </>
    )
}

export default App;