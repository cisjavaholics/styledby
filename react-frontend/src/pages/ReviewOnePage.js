import React, {Component, useState} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";
import ReviewComponent from "../components/ReviewComponent";
import './Home.css';
import ReviewsListHorizontal from "../components/ReviewsListHorizontal";
import ReviewOne from "../components/ReviewOne";

class ReviewOnePage extends Component {
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
                            <h1 className="mt-3 display-3 text-center">Home</h1>
                        </div>
                    </div>
                    <div className={"reviews-grid-container"}>
                        <ReviewOne />
                    </div>
                </div>
            </>
        );
    }
}

export default ReviewOnePage;