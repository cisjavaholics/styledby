import React from "react";
import { useState } from "react"; // You can import other hooks if needed
import logo from "./path/to/logo"; // assuming you have the path to your logo

function UserProfile() {
    // Add state or any other hooks as needed
    const [searchQuery, setSearchQuery] = useState("");

    return (
        <div>
            <header>
                <nav className="navbar navbar-expand-lg bg-body-tertiary">
                    <div className="container-fluid">
                        <a className="navbar-brand" href="#">StyledBy</a>
                        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <a className="nav-link active" aria-current="page" href="#">Home</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">Profile</a>
                                </li>
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        More
                                    </a>
                                    <ul className="dropdown-menu">
                                        <li><a className="dropdown-item" href="#">Forum</a></li>
                                        <li><a className="dropdown-item" href="#">Reviews</a></li>
                                        <li><a className="dropdown-item" href="#">Notifications</a></li>
                                        <li><a className="dropdown-item" href="#">Settings</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <form className="d-flex" role="search">
                                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)} />
                                <button className="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>

            <div className="card mb-3">
                <svg className="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#fbb6c1"></rect>
                </svg>

                <div className="card-body">
                    <h5 className="card-title">Sasha Creavalle</h5>
                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <p className="card-text"><small className="text-body-secondary">@screavalle</small></p>
                </div>
            </div>

            <nav>
                <div className="nav nav-underline flex-column flex-sm-row" id="nav-tab" role="tablist">
                    <a className="flex-sm-fill text-sm-center nav-link active" id="nav-review-tab" data-bs-toggle="tab" href="#nav-review" role="tab" aria-controls="nav-review" aria-selected="true">Reviews</a>
                    <a className="flex-sm-fill text-sm-center nav-link" id="nav-profile-tab" data-bs-toggle="tab" href="#nav-forum" role="tab" aria-controls="nav-profile" aria-selected="false">Forums</a>
                </div>
            </nav>

            <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade show active" id="nav-review" role="tabpanel" aria-labelledby="nav-review-tab">
                    {/* Reviews content */}
                </div>
                <div className="tab-pane fade" id="nav-forum" role="tabpanel" aria-labelledby="nav-forum-tab">
                    {/* Forums content */}
                </div>
            </div>
        </div>
    );
}

export default UserProfile;
