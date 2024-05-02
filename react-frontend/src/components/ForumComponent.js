import React, {useState} from 'react';
import './ForumComponent.css';
import braidsImage from './braids.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as faEmptyHeart } from '@fortawesome/free-regular-svg-icons';
import { faHeart as faSolidHeart } from '@fortawesome/free-solid-svg-icons';



function ForumComponent(props) {

    const [liked, setLiked] = useState(false);
    const [likes, setLikes] = useState(props.likes);

    const handleLikeClick = () => {
        if (!liked) {
            setLikes(likes + 1);
        } else {
            setLikes(likes - 1);
        }
        setLiked(!liked);
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
            <link rel="stylesheet" href="ForumComponent.css"/>
            <title>One Forum Post</title>
            <link
                href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
                rel="stylesheet"
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                crossOrigin="anonymous"
            />
            <div className="forum-container container-fluid">
                <p className="date">{props.postedAt}</p>
                <button className="like-button" onClick={handleLikeClick} style={{float: "right"}}>
                    {liked ? (
                        <FontAwesomeIcon icon={faSolidHeart} color="red" /> // Filled heart icon when liked
                    ) : (
                        <FontAwesomeIcon icon={faEmptyHeart} /> // Empty heart icon when not liked
                    )}
                    &nbsp;{likes}
                </button>
                <h2>
                    {props.title}{" "}
                    <span className="badge-forum text-bg-secondary.bg-{#800080}">{props.topic}</span>
                </h2>
                <a href="userProfile.html" style={{textDecoration: "none"}}>
                    <i className="fa-regular fa-user">&nbsp;</i>{props.postedBy}
                </a>

                <div className="desc">
                    <p>
                        {props.description}
                    </p>
                </div>
                <div className="forum-photo-container">
                    <img src={braidsImage} className={"forum-photo-img"}/>
                    <img src={braidsImage} className={"forum-photo-img"}/>
                    <img src={braidsImage} className={"forum-photo-img"}/>
                    <img src={braidsImage} className={"forum-photo-img"}/>
                </div>
            </div>
        </>

    );
}

export default ForumComponent;