import React, {useState} from 'react';
import axios from "axios";
import ReviewComponent from "./ReviewComponent";
function ReviewListHorizontal(props) {

        const url = "http://localhost:8080/api/reviews/"
        const [reviews, setReviews] = useState([]);
        const getReviews = async () => {
            await  axios.get(url).then((response) => {
                setReviews(response.data);
                return reviews.map(review =>
                    <ReviewComponent type={review.type} rating={review.rating} review={review.description}
                                     company={review.company} user={review.createdBy} createdAt={review.createdAt}
                                     width={500} height={600}/> );


            }).catch( (err) => {
                console.log(err);
            })
        }

        //calls the function
        getReviews().then(null);
}
export default ReviewListHorizontal;