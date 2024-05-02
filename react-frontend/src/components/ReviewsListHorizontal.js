import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ReviewComponent from './ReviewComponent';
import './ReviewsListHorizontal.css';
import {Link} from "react-router-dom";

function ReviewsListHorizontal(props) {
    const url = 'http://localhost:8080/api/reviews/';
    const [reviews, setReviews] = useState([]);
    const [showReviewOne, setShowReviewOne] = useState(false);

    useEffect(() => {
        // Fetch reviews when the component mounts
        getReviews();
    }, []);

    const getReviews = async () => {
        try {
            const response = await axios.get(url);
            setReviews(response.data.data);
        } catch (error) {
            console.error('Error fetching reviews:', error);
        }
    };
    const limitedReviews = reviews.slice(0, props.maxItems);


    return (
        <>
            <link rel="stylesheet" href="ReviewsListHorizontal.css" />
            {reviews.length === 0 ? (
                <>
                    <ReviewComponent
                        type={'Loading'}
                        rating={0}
                        description={'Reviews are loading'}
                        business={'Loading'}
                        createdBy={'Loading'}
                        createdAt={'Loading'}
                        width={props.rWidth}
                        height={props.rHeight}
                    />
                    <ReviewComponent
                        type={'Loading'}
                        rating={0}
                        description={'Reviews are loading'}
                        business={'Loading'}
                        createdBy={'Loading'}
                        createdAt={'Loading'}
                        width={props.rWidth}
                        height={props.rHeight}
                    />
                </>
            ) : (
                limitedReviews.map((review) => (
                    <Link to={`/reviewOne/${review.id}`} className="review-link">
                        <ReviewComponent
                        key={review.id}
                        type={review.type}
                        rating={review.rating}
                        description={review.description}
                        business={review.business ? review.business.name : "loading"}
                        createdBy={review.createdBy.username}
                        createdAt={new Date(review.createdAt.seconds * 1000).toDateString()}
                        width={props.rWidth}
                        height={props.rHeight}
                    />
                    </Link>
                ))
            )}
        </>
    );
}

export default ReviewsListHorizontal;
