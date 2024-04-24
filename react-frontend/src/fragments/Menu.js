
import React from 'react';
import {Link} from "react-router-dom";
import logo from "../logo.png"

function Menu(props) {
    return (
        <div>
            <header>
                <nav className="menu">
                    <ul>
                        <li>Home</li>
                        {/* Use Link instead of anchor tag */}
                    </ul>
                </nav>
            </header>
        </div>
    );
}

export default Menu;