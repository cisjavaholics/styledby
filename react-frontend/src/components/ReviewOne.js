import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import ReviewComponent from './ReviewComponent';

function ReviewOne() {
    const { reviewId } = useParams();
    const [review, setReview] = useState(null);

    useEffect(() => {
        // Fetch review when the component mounts or reviewId changes
        if (reviewId) {
            console.log(reviewId)
            getReview(reviewId);
        }
    }, [reviewId]);

    const getReview = async (id) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/reviews/${id}`);
            setReview(response.data.data);
        } catch (error) {
            console.error('Error fetching review:', error);
        }
    };

    return (
        <>
            <link rel="stylesheet" href="ReviewOnePage.css" />
            {review ? (
                <ReviewComponent
                    key={review.id}
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
        </>
    );
}

export default ReviewOne;

