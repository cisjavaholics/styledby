import React from 'react';
import './ReviewComponent.css';
import braidsImage from './braids.png';


function ReviewComponent(props) {

    const loadStars = () => {
        const stars = [];
        for (let i = 0; i < props.rating; i++) {
            stars.push(<span className="rating">★</span>);
        }
        for (let j = 0; j < 5-props.rating; j++) {
            stars.push(<span className="rating">☆</span>);
        }
        return stars;
    };

    return (
        <>
            <link rel="stylesheet" href="ReviewComponent.css"/>
            <title>One Review</title>
                <div className="review-container container-fluid" style={{
                    width: props.width,
                    height: props.height
                }}>
                    <div className={"review-header"}>
                        <p style={{ float: "right" }}>{props.createdAt}</p>
                        <h2>
                            {props.business}{" "}
                            <span className="badge text-bg-secondary.bg-{#800080}">{props.type}</span>
                        </h2>
                        <a href="userProfile.html" style={{ textDecoration: "none" }}>
                            <i className="fa-regular fa-user">&nbsp;</i>{props.createdBy}
                        </a>
                        <p>
                            {loadStars() }
                        </p>
                    </div>
                    <div className="desc">
                        <p>
                            {props.review}
                        </p>
                        <p>
                            Social Media or Website: <a href="#">Website</a>
                        </p>
                        <div className="rev-photo-container">
                            <img src={braidsImage} className={"photo-img"}/>
                            <img src={braidsImage} className={"photo-img"} />
                            <img src={braidsImage} className={"photo-img"}/>
                            <img src={braidsImage}  className={"photo-img"}/>
                        </div>
                    </div>
                </div>
        </>

    );
}

export default ReviewComponent;