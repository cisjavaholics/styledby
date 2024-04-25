import React from 'react';
import './ReviewComponent.css';
import braidsImage from './braids.png';


function ReviewComponent(props) {

    const loadStars = () => {
        const stars = [];
        for (let i = 0; i < props.rating; i++) {
            stars.push(<span className="rating">★</span>);
        }
        for (let i = 0; i < 5-props.rating; i++) {
            stars.push(<span className="rating">☆</span>);
        }
        return stars;
    };

    return (
        <>
            <meta charSet="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="preconnect" href="https://fonts.googleapis.com" />
            <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="" />
            <link
                href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
                rel="stylesheet"
            />
            <link rel="stylesheet" href="ReviewComponent.css"/>
            <title>One Review</title>
            <link
                href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                rel="stylesheet"
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                crossOrigin="anonymous"
            />
                <div className="review-container container-fluid" style={{
                    width: props.width,
                    height: props.height
                }}>
                    <p style={{ float: "right" }}>{props.createdAt}</p>
                    <h2>
                        {props.company}{" "}
                        <span className="badge text-bg-secondary.bg-{#800080}">{props.type}</span>
                    </h2>
                    <a href="userProfile.html" style={{ textDecoration: "none" }}>
                        <i className="fa-regular fa-user">&nbsp;</i>{props.user}
                    </a>
                    <p>
                        {loadStars() }
                    </p>
                    <div className="desc">
                        <p>
                            {props.review}
                        </p>
                    </div>
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
        </>

    );
}

export default ReviewComponent;