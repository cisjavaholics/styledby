import React, {useContext} from 'react';
import {Redirect} from "react-router-dom";
import {AuthContext} from "../context/AuthContext";

function Logout(props) {
    const context = useContext(AuthContext);
    context.signOut();
    return (
        <Redirect to="/login" />
    );
}

export default Logout