import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ReviewComponent from './ReviewComponent';
import './ReviewsListHorizontal.css';

function ReviewsListHorizontal(props) {
    const url = 'http://localhost:8080/api/reviews/';
    const [reviews, setReviews] = useState([]);

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

    return (
        <>
            <link rel="stylesheet" href="ReviewsListHorizontal.css" />
            {reviews.length === 0 ? (
                <>
                    <ReviewComponent
                        type={'Loading'}
                        rating={0}
                        review={'Reviews are loading'}
                        business={'Loading'}
                        createdBy={'Loading'}
                        createdAt={'Loading'}
                        width={props.rWidth}
                        height={props.rHeight}
                    />
                    <ReviewComponent
                        type={'Loading'}
                        rating={0}
                        review={'Reviews are loading'}
                        business={'Loading'}
                        createdBy={'Loading'}
                        createdAt={'Loading'}
                        width={props.rWidth}
                        height={props.rHeight}
                    />
                </>
            ) : (
                reviews.map((review) => (
                    <ReviewComponent
                        key={review.id}
                        type={review.type}
                        rating={review.rating}
                        review={review.review}
                        business={review.business}
                        createdBy={review.createdBy.username}
                        createdAt={new Date(review.createdAt.seconds * 1000).toDateString()}
                        width={props.rWidth}
                        height={props.rHeight}
                    />
                ))
            )}
        </>
    );
}

export default ReviewsListHorizontal;
