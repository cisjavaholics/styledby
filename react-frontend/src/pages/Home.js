import React, {Component, useState} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";
import ReviewComponent from "../components/ReviewComponent";
import './Home.css';

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
                    <div className={"review-scroll-container"}>
                        <p>example review cards</p>
                        <ReviewComponent type={"Hair"} rating={2} review={"Worst stylist everrrrrrrrrr"}
                                         company={"Braids By Brandee"} user={"taby212"} createdAt={"Janary 30, 2023"}
                                         width={500} height={600}/>
                        <ReviewComponent type={"Makeup"} rating={5} review={"Best beat and good prices!!"}
                                         company={"BeatByBella"} user={"opal101302"} createdAt={"October 13, 2023"}
                                         width={500} height={600}/>
                    </div>
                </div>
            </>
        );
    }
}

export default Home;