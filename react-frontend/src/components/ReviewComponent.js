import React from 'react';
import './ReviewComponent.css';
import braidsImage from './braids.png';
import {Link} from "react-router-dom";
import axios from "axios";

function ReviewComponent(props) {

    const loadStars = () => {
        const stars = [];
        for (let i = 0; i < props.rating; i++) {
            stars.push(<span className="rating">★</span>);
        }
        for (let j = 0; j < 5 - props.rating; j++) {
            stars.push(<span className="rating">☆</span>);
        }
        return <div className="rating-container">{stars}</div>; // Wrap stars in a container div
    };
    const handleDelete = async () => {
        try {
            // Make an HTTP DELETE request to your backend API to delete the review
            await axios.delete(`http://localhost:8080/api/reviews/${props.id}`);
            // Optionally, you can add a callback to inform the parent component that the review has been deleted
            // For example: props.onDelete(props.id);
        } catch (error) {
            console.error('Error deleting review:', error);
            // Handle error appropriately (e.g., show a message to the user)
        }
    };
    return (
        <>
            <link rel="stylesheet" href="ReviewComponent.css"/>
            <title>One Review</title>
            <div className="review-container container-fluid">
                <div className={"review-header"}>
                    <p style={{float: "right"}}>{props.createdAt}</p>
                    <h2>
                        {props.business}
                    </h2>
                    <span className="badge text-bg-secondary.bg-{#800080}" style={{float: "right"}}>{props.type}</span>
                    <a href="userProfile.html" style={{textDecoration: "none"}}>
                        <i className="fa-regular fa-user">&nbsp;</i>{props.createdBy}
                    </a>
                    <p>
                        {loadStars()}
                    </p>
                </div>
                <div className="desc">
                    <p>
                        {props.description}
                    </p>
                    <div className="rev-photo-container">
                        <img src={braidsImage} className={"photo-img"}/>
                        <img src={braidsImage} className={"photo-img"} />
                        <img src={braidsImage} className={"photo-img"}/>
                        <img src={braidsImage}  className={"photo-img"}/>
                    </div>
                </div>
                <div className="dropdown-menu">
                    <button className="dropbtn">&#8942;</button>
                    <div className="dropdown-content">
                        <Link className="btn create-btn" to="/updateReview">Update</Link>
                        <button className="btn create-btn" onClick={handleDelete}>Delete</button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default ReviewComponent;
