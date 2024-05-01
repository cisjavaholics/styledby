import React, {Component, useState} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";
import ReviewComponent from "../components/ReviewComponent";
import './Home.css';
import ReviewsListHorizontal from "../components/ReviewsListHorizontal";
import {Link} from "react-router-dom";

class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {posts: null}
    }


    render() {
        return (
            <>
                <link rel="stylesheet" href="Home.css"/>
                <div className="col-12">
                    <Menu/>
                    <div className="row">
                        <div className="col-12">
                            <h1 className="mt-3 display-3">Home</h1>
                        </div>
                    </div>
                    <div className={"review-page-container"}>
                        <p><Link to="/reviews">Reviews</Link></p>
                        <ReviewsListHorizontal rWidth={400} rHeight={500}/>
                    </div>
                </div>
            </>
        );
    }
}

export default Home;