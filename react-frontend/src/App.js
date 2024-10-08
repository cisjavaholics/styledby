import Home from "./pages/Home";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Menu from "./fragments/Menu";
import Footer from "./fragments/Footer";
import UserComponent from "./components/UserComponent";
import Reviews from "./pages/Reviews";
import Forum from "./pages/Forum";
import ReviewForm from "./pages/ReviewForm";
import ReviewComponent from "./components/ReviewComponent";
import ReviewOne from "./components/ReviewOne";
import {AuthProvider} from "./context/AuthContext";
import UpdateReview from "./pages/UpdateReview";
import UserProfile from "./pages/UserProfile";

function App() {
  return (
    <>
        <AuthProvider>
            <Router>
                <div className="container">
                    <Routes>
                        <Route element = {<Home/>}  path="/"/>
                        <Route element ={<Reviews/>} path= "/reviews" />
                        <Route  path= "/reviewOne/:reviewId" element ={<ReviewOne/>} />
                        <Route element ={<ReviewForm/>} path= "/reviewForm" />
                        <Route element ={<UpdateReview/>} path= "/updateReview" />
                        <Route element ={<Forum/>} path= "/forum" />
                        <Route element ={<UserProfile/>} path= "/userProfile" />

                    </Routes>
                </div>
                <Footer />
            </Router>
        </AuthProvider>
    </>
  );
}

export default App;
