import Home from "./pages/Home";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Menu from "./fragments/Menu";
import Footer from "./fragments/Footer";
import UserComponent from "./components/UserComponent";
import Reviews from "./pages/Reviews";
import Forum from "./pages/Forum";
import ReviewForm from "./pages/ReviewForm";
import {AuthProvider} from "./context/AuthContext";

function App() {
  return (
    <AuthProvider>
        <Router>
            <div className="container">
                <Routes>
                    <Route element = {<Home/>}  path="/"/>
                    <Route element ={<Reviews/>} path= "/reviews" />
                    <Route element ={<ReviewForm/>} path= "/reviewForm" />
                    <Route element ={<Forum/>} path= "/forum" />
                </Routes>
            </div>
            <Footer />
        </Router>
    </AuthProvider>
  );
}

export default App;
