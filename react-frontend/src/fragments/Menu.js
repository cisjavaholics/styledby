
import React from 'react';
import {Link} from "react-router-dom";
import logo from "../logo.png"

function Menu(props) {
    return (
        <>
            <meta charSet="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="preconnect" href="https://fonts.googleapis.com"/>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin=""/>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
            rel="stylesheet"
        />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossOrigin="anonymous"
        />
        <style
            dangerouslySetInnerHTML={{
                __html:
                    '\n    body{\n      font-family: "Inter", sans-serif;\n      font-size: 1em;\n    }\n    p{\n      font-size: 1em;\n    }\n    h2{\n      font-size: 1.5em;\n    }\n    .goback-btn {\n      background-color: transparent;\n      border: none;\n      font-size: 20px;\n      cursor: pointer;\n      padding-top: 30px;\n      padding-left: 30px;\n    }\n    .goback-btn::before {\n      content: "\\2190";\n      font-size: 30px;\n      margin-left: 20px;\n    }\n    .review-container {\n      border: 3px solid #800080;\n      border-radius: 10px;\n      padding: 20px;\n      width: 75%;\n      height: auto;\n      margin: 100px 300px;\n    }\n    .badge{\n      background-color: #800080;\n      color: white;\n      border-radius: 40px;\n      font-size: 0.75em;\n    }\n    .rating {\n      color: orange;\n      font-size: 1.5em;\n    }\n    .desc {\n      font-size: 1.5em;\n    }\n    .photo-container{\n      display: grid;\n      grid-template-columns: repeat(2,1fr);\n      gap: 10px\n    ;\n    }\n    .photo-container img{\n     height: 500px;\n      width: 100%;\n      margin-bottom: 10px\n    ;\n    }\n    \n  '
            }}
        />
        <header>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">
                        StyledBy
                    </a>
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"/>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="#">
                                    Home
                                </a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">
                                    Profile
                                </a>
                            </li>
                            <li className="nav-item dropdown">
                                <a
                                    className="nav-link dropdown-toggle"
                                    href="#"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                >
                                    More
                                </a>
                                <ul className="dropdown-menu">
                                    <li>
                                        <a className="dropdown-item" href="#">
                                            Forum
                                        </a>
                                    </li>
                                    <li>
                                        <a className="dropdown-item" href="#">
                                            Reviews
                                        </a>
                                    </li>
                                    <li>
                                        <a className="dropdown-item" href="#">
                                            Notifications
                                        </a>
                                    </li>
                                    <li>
                                        <a className="dropdown-item" href="#">
                                            Settings
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <form className="d-flex" role="search">
                            <input
                                className="form-control me-2"
                                type="search"
                                placeholder="Search"
                                aria-label="Search"
                            />
                            <button className="btn btn-outline-success" type="submit">
                                Search
                            </button>
                        </form>
                    </div>
                </div>
            </nav>
        </header>
    </>
)
    ;
}

export default Menu;