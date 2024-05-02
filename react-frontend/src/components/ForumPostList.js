import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ForumList.css';
import {Link} from "react-router-dom";
import ForumComponent from "./ForumComponent";


function ForumPostList(props) {
    const url = 'http://localhost:8080/api/forumPosts/';
    const [forumPosts, setForumPosts] = useState([]);

    useEffect(() => {
        // Fetch forum posts when the component mounts
        getForumPosts();
    }, []);

    const getForumPosts = async () => {
        try {
            const response = await axios.get(url);
            setForumPosts(response.data.data);
        } catch (error) {
            console.error('Error fetching forum posts:', error);
        }
    };
    const limitedPosts = forumPosts.slice(0, props.maxItems);
    return (
        <>
            <link rel="stylesheet" href="ForumList.css" />
            {forumPosts.length === 0 ? (
                <>
                    <ForumComponent
                        title={'Loading'}
                        topic={0}
                        description={'Loading'}
                        postedBy={'Forum posts are loading'}
                        postedAt={'Loading'}
                        width={props.fWidth}
                        height={props.fHeight}
                    />
                </>
            ) : (
                limitedPosts.map((forumPost) => (
                    <Link to={`/forumPostOne/${forumPost.id}`} className="forum-link" style={{textDecoration:"none",color:"black"}}>
                        <ForumComponent
                            key={forumPost.id}
                            title={forumPost.title ? forumPost.title : "Question"}
                            topic={forumPost.topic}
                            description={forumPost.description}
                            postedBy={forumPost.postedBy ? forumPost.postedBy.username : "Unknown"}
                            postedAt={forumPost.postedAt ? new Date(forumPost.postedAt.seconds * 1000).toDateString() : "Unknown"} //
                            width={props.fWidth}
                            height={props.fHeight}
                        />
                    </Link>
                ))
            )}
        </>
    );
}

export default ForumPostList;