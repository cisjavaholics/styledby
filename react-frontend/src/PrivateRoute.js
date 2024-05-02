import React, {useContext} from 'react';
import {AuthContext} from "./context/AuthContext";
import {Navigate} from "react-router-dom";

import React, {useContext} from 'react';
import {AuthContext} from "./AuthContext";
import {Navigate} from "react-router-dom";

function PrivateRoute({children}) {

    const {currentUser, isLoggedIn} = useContext(AuthContext);

    if(isLoggedIn && currentUser?.length > 0) {
        localStorage.setItem("user", JSON.stringify(currentUser));
        return children;
    }
    return <Navigate to="/signin" replace={true} />
}

export default PrivateRoute;