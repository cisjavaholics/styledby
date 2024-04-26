import Home from "./pages/Home";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Menu from "./fragments/Menu";
import Footer from "./fragments/Footer";
import UserComponent from "./components/UserComponent";
import Reviews from "./pages/Reviews";
import Forum from "./pages/Forum";

function App() {
  return (
    <>
        <Router>
            <div className="container">
                <Routes>
                    <Route element = {<Home/>}  path="/"/>
                    <Route element ={<Reviews/>} path= "/reviews" />
                    <Route element ={<Forum/>} path= "/forum" />
                </Routes>
            </div>
            <Footer />
        </Router>
    </>
  );
}

export default App;
