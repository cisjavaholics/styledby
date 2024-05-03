import React, {useState} from 'react';
import './ReviewComponent.css';
import braidsImage from './braids.png';
import {Link} from "react-router-dom";
import axios from "axios";
import {Rating} from "react-simple-star-rating";

function ReviewComponent(props) {
    const { rating } = props;
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
                    <Link to={`/reviewOne/${props.id}`} className="review-link">
                        <h2>
                            {props.business}
                        </h2>
                    </Link>
                    <span className="badge text-bg-secondary.bg-{#800080}" style={{float: "right"}}>{props.type}</span>
                    <a href="userProfile.html" style={{textDecoration: "none"}}>
                        <i className="fa-regular fa-user">&nbsp;</i>{props.createdBy}
                    </a>
                    <p>
                        <Rating initialValue={props.rating}>

                        </Rating>
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
