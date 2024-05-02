import React from 'react';
import { useState } from "react";

function BusinessProfile() {
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
                                        <li><a className="dropdown-item" href="#">Profile</a></li>
                                        <li><a className="dropdown-item" href="#">Reviews</a></li>
                                        <li><a className="dropdown-item" href="#">Notifications</a></li>
                                        <li><a className="dropdown-item" href="#">Settings</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <form className="d-flex" role="search">
                                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                                <button className="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </header>

            <div className="card mb-3">
                <svg className="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#b3e7ff"></rect>
                </svg>
                <div className="card-body">
                    <h5 className="card-title">Braids By Brandee</h5>
                    <p className="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <p className="card-text"><small className="text-body-secondary">@sbraidsbybrandee</small></p>
                </div>
            </div>

            <nav>
                <div className="nav nav-underline flex-column flex-sm-row" id="nav-tab" role="tablist">
                    <a className="flex-sm-fill text-sm-center nav-link" id="nav-service-tab" data-bs-toggle="tab" href="#nav-service" role="tab" aria-controls="nav-service" aria-selected="true">Services</a>
                    <a className="flex-sm-fill text-sm-center nav-link active" id="nav-review-tab" data-bs-toggle="tab" href="#nav-review" role="tab" aria-controls="nav-review" aria-selected="false">Reviews</a>
                    <a className="flex-sm-fill text-sm-center nav-link" id="nav-mention-tab" data-bs-toggle="tab" href="#nav-mention" role="tab" aria-controls="nav-mention" aria-selected="false">Mentions</a>
                </div>
            </nav>

            <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade" id="nav-service" role="tabpanel" aria-labelledby="nav-service-tab">
                    {/* Service tab content */}
                </div>
                <div className="tab-pane fade show active" id="nav-review" role="tabpanel" aria-labelledby="nav-review-tab">
                    <div className="row">
                        <div className="col-12">
                            <div id="simple-list-example" className="d-flex flex-column gap-2 simple-list-example-scrollspy text-center">
                                <div className="review-container container-fluid">
                                    <p style={{ float: "right" }}>April 22, 2024</p>
                                    <h2>Braids By Brandee <span className="badge text-bg-secondary.bg-{#800080}">Hair</span></h2>
                                    <a href="businessProfile.html" style={{ textDecoration: "none" }}><i className="fa-regular fa-business">&nbsp;</i>screavalle</a>
                                    <p>
                                        <span className="rating">&#9733;</span>
                                        <span className="rating">&#9733;</span>
                                        <span className="rating">&#9733;</span>
                                        <span className="rating">&#9733;</span>
                                        <span className="rating">&#9734;</span>
                                    </p>
                                    <div className="desc">
                                        <p> Great style, but they had someone in the chair during my appointment time, which resulted in a slight delay in getting started. However, once they were ready for me, the service was impeccable. </p>
                                    </div>
                                    <p>Social Media or Website: <a href="#">Website</a></p>
                                    <div className="photo-container">
                                        <img src="braids.png" className="img-fluid" />
                                        <img src="braids.png" className="img-fluid" />
                                        <img src="braids.png" className="img-fluid" />
                                        <img src="braids.png" className="img-fluid" />
                                    </div>
                                </div>
                                {/* Similar review-container elements follow */}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="tab-pane fade" id="nav-mention" role="tabpanel" aria-labelledby="nav-mention-tab">
                    <div className="mention-container container-fluid">
                        <p style={{ float: "right" }}>April 22, 2024</p>
                        <h3>Hair Growth Tips <span className="badge text-bg-secondary.bg-{#800080}">Hair</span></h3>
                        <a href="businessProfile.html" style={{ textDecoration: "none" }}><i className="fa-regular fa-business">&nbsp;</i>screavalle</a>
                        <div className="desc">
                            <p> Use Humectants On Your Hair. Humectants love water molecules, so using them in your natural hair (which is often dry hair) is a must. Honey, aloe vera, flax seed gel, and glycerin are all great humectants. </p>
                        </div>
                    </div>
                    {/* Similar mention-container elements follow */}
                </div>
            </div>
        </div>
    );
}

export default BusinessProfile;
