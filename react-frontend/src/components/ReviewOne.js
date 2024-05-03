import React, {Component, useEffect, useState} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";
import ReviewComponent from "./ReviewComponent";
import '../pages/Home.css';
import {useParams} from "react-router-dom";

const ReviewOne = () => {
    const [review, setReview] = useState(null);
    let {reviewId} = useParams();
    console.log(reviewId);

    useEffect(() => {
        // Fetch review when the component mounts or reviewId changes
        if (reviewId) {
            console.log(reviewId)
            getReview();
        }
    }, [reviewId]);

    const getReview = async () => {

        try {
            const response = await axios.get(`http://localhost:8080/api/reviews/rPostId/${reviewId}`);
            setReview(response.data.data);
        } catch (error) {
            console.error('Error fetching review:', error);
        }
    };
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
                        {review ? (
                            <ReviewComponent
                                key={review.id}
                                id={review.rPostId}
                                type={review.type}
                                rating={review.rating}
                                description={review.description}
                                business={review.business ? review.business.name : "loading"}
                                createdBy={review.createdBy.username}
                                createdAt={new Date(review.createdAt.seconds * 1000).toDateString()}
                            />
                        ) : (
                            <p>Loading review...</p>
                        )}
                    </div>
                </div>
            </>
        );
}

export default ReviewOne;