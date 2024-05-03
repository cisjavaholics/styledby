import React from "react";
import { useState } from "react";
import Menu from "../fragments/Menu";
import ReviewComponent from "../components/ReviewComponent";
import ForumComponent from "../components/ForumComponent"; // You can import other hooks if needed


function UserProfile() {
    // Add state or any other hooks as needed
    const [searchQuery, setSearchQuery] = useState("");

    return (
        <div>
            <Menu/>
            <h1 className="mt-3 display-4 text-center">Your Profile</h1>
            <div className="card mb-3" >
                <div className="card-body">
                    <h3 className="card-title">Sasha Creavalle</h3>
                    <p className="card-text"><small className="text-body-secondary">@screavalle</small></p>
                </div>
            </div>

            <nav>
                <div className="nav nav-underline flex-column flex-sm-row" id="nav-tab" role="tablist" style={
                    {backgroundColor:"#800080", margin:"10px", borderRadius:"10px"}
                }>
                    <a className="flex-sm-fill text-sm-center nav-link active" id="nav-review-tab" data-bs-toggle="tab"
                       href="#nav-review" role="tab" aria-controls="nav-review" aria-selected="true" style={
                        {color:"white"}
                    }>Reviews</a>
                    <a className="flex-sm-fill text-sm-center nav-link" id="nav-profile-tab" data-bs-toggle="tab"
                       href="#nav-forum" role="tab" aria-controls="nav-profile" aria-selected="false" style={
                        {color:"white"}
                    }>Forums</a>
                </div>
            </nav>
            <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade show active" id="nav-review" role="tabpanel"
                     aria-labelledby="nav-review-tab" >
                    <ReviewComponent
                        type={'Loading'}
                        rating={4}
                        description={'Reviews are loading'}
                        business={'Loading'}
                        createdBy={'Loading'}
                        createdAt={'Loading'}
                    />
                </div>
                <div className="tab-pane fade" id="nav-forum" role="tabpanel" aria-labelledby="nav-forum-tab">
                    <ForumComponent
                        title={'Loading'}
                        topic={0}
                        description={'Loading'}
                        postedBy={'Forum posts are loading'}
                        postedAt={'Loading'}
                    />
                </div>
            </div>
        </div>
    );
}

export default UserProfile;
