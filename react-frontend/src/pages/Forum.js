import React, {Component, useState} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";

import './Home.css';
import ForumPostList from "../components/ForumPostList";


class Forum extends Component {
    constructor(props) {
        super(props);
        this.state = {posts: null}
    }


    render() {
        return (
            <>
                <link rel="stylesheet" href="ForumList.css"/>
                <div className="col-12">
                    <Menu/>
                    <div className="row">
                        <div className="col-12">
                            <h1 className="mt-3 display-3 text-center">Forum</h1>
                        </div>
                    </div>
                    <div className={"forum-list-container"}>
                        <ForumPostList fWidth={1000} fHeight={500} maxItems={10000}/>
                    </div>
                </div>
            </>
        );
    }
}

export default Forum;