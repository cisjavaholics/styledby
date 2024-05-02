import React, {Component, useState} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";
import ReviewComponent from "../components/ReviewComponent";
import './Home.css';
import ReviewsListHorizontal from "../components/ReviewsListHorizontal";
import ForumPostList from "../components/ForumPostList";

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
                            <h1 className="mt-3 display-3 text-center">Home</h1>
                        </div>
                    </div>
                    <h1 className="mt-3 display-5 text-center">Recent Reviews</h1>
                    <div className={"reviews-grid-container"}>
                        <ReviewsListHorizontal rWidth={400} rHeight={500} maxItems={5}/>
                    </div>
                    <h1 className="mt-3 display-5 text-center">Recent Forum Posts</h1>
                    <div className={"forum-list-container"}>
                        <ForumPostList rWidth={400} rHeight={500} maxItems={5}/>
                    </div>
                </div>
            </>
        );
    }
}

export default Home;