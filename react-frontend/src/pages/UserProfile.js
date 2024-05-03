import React, { useContext } from "react";
import { useState } from "react";
import Menu from "../fragments/Menu";
import ReviewComponent from "../components/ReviewComponent";
import ForumComponent from "../components/ForumComponent";
import { AuthContext } from "../context/AuthContext";
import axios from "axios";
import {Link} from "react-router-dom";

class UserProfile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            reviews: null,
            forumPosts: null
        };
        this.userId = "2egpUjARYPvn0yGEGOlx";
    }

    componentDidMount() {
        this.fetchReviews().then(null);
        this.fetchForumPosts().then(null);
    }

    fetchReviews = async () => {
        await axios
            .get(`http://localhost:8080/api/reviews/user_id/${this.userId}`)
            .then(response => {
                this.setState({reviews: response.data.reviews});
            })
            .catch(error => {
                console.error('Error fetching reviews:', error);
            });
    };

    fetchForumPosts = async () => {
        await axios
            .get(`http://localhost:8080/api/forumPosts/user_id/${this.userId}`)
            .then(response => {
                this.setState({forumPosts: response.data.forumPosts});
            })
            .catch(error => {
                console.error('Error fetching forumPosts:', error);
            });
    };

    handleReviewChange = async (rPostId, newValue) => {
        await axios
            .put(`http://localhost:8080/api/reviews/${rPostId}`, {
                updatedReview: newValue
            })
            .then(response => {
                // update state with new post
                const {reviews} = this.state;
                const index = reviews.findIndex(review => review.rPostId === rPostId);
                reviews[index].updatedReview = newValue;
                this.setState({reviews});
            })
            .catch(error => {
                console.error('Error updating review:', error);
            });
    };

    handleForumPostChange = async (fPostId, newValue) => {
        await axios
            .put(`http://localhost:8080/api/forumPosts/${fPostId}`, {
                updatedForumPost: newValue
            })
            .then(response => {
                // update state with new post
                const {forumPosts} = this.state;
                const index = forumPosts.findIndex(forumPost => forumPost.fPostId === fPostId);
                forumPosts[index].updatedForumPost = newValue;
                this.setState({forumPosts});
            })
            .catch(error => {
                console.error('Error updating forumPost:', error);
            });
    };

    handleDeleteReview = async rPostId => {
        await axios
            .delete(`http://localhost:8080/api/reviews/${rPostId}`)
            .then(response => {
                // remove deleted post from state
                const {reviews} = this.state;
                const updatedReviews = reviews.filter(review => review.rPostId !== rPostId);
                this.setState({reviews: updatedReviews});
            })
            .catch(error => {
                console.error('Error deleting review:', error);
            });
    };

    handleDeleteForumPost = async fPostId => {
        await axios
            .delete(`http://localhost:8080/api/forumPosts/${fPostId}`)
            .then(response => {
                // remove deleted post from state
                const {forumPosts} = this.state;
                const updatedForumPosts = forumPosts.filter(forumPost => forumPost.fPostId !== fPostId);
                this.setState({forumPosts: updatedForumPosts});
            })
            .catch(error => {
                console.error('Error deleting forum post:', error);
            });
    };

    render() {
        return (
            <div>
                <Menu/>
                <h1 className="mt-3 display-4 text-center">Your Profile</h1>
                <div className="col-2 text-md-end text-sm-start">
                    <Link to="/reviewForm" className="btn btn-outline-dark mt-md-5 mt-sm-1">
                        <i className="fa-regular"></i> Write a Review
                    </Link>
                </div>
                <div className="card mb-3">
                    <div className="card-body">
                        <h3 className="card-title">Sasha Creavalle</h3>
                        <p className="card-text"><small className="text-body-secondary">@screavalle</small></p>
                    </div>
                </div>
                <nav>
                    <div className="nav nav-underline flex-column flex-sm-row" id="nav-tab" role="tablist"
                         style={{backgroundColor: "#800080", margin: "10px", borderRadius: "10px"}}>
                        <a className="flex-sm-fill text-sm-center nav-link active" id="nav-review-tab"
                           data-bs-toggle="tab" href="#nav-review" role="tab" aria-controls="nav-review"
                           aria-selected="true" style={{color: "white"}}>Reviews</a>
                        <a className="flex-sm-fill text-sm-center nav-link" id="nav-profile-tab" data-bs-toggle="tab"
                           href="#nav-forum" role="tab" aria-controls="nav-profile" aria-selected="false"
                           style={{color: "white"}}>Forums</a>
                    </div>
                </nav>
                <div className="tab-content" id="nav-tabContent">
                    <div className="tab-pane fade show active" id="nav-review" role="tabpanel"
                         aria-labelledby="nav-review-tab">
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
                            likes={4}
                            description={'Loading'}
                            postedBy={'Forum posts are loading'}
                            postedAt={'Loading'}
                        />
                    </div>
                </div>
            </div>
        );
    }
}

UserProfile.contextType = AuthContext; // Set context type here

export default UserProfile;
