import React, {Component} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";


class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {posts: null}
    }
    componentDidMount() {
        const url = "http://localhost:8080/api/forumPosts"
        const getPosts = async () => {
            await  axios.get(url).then((response) => {

                this.setState({posts: response.data.posts});

            }).catch( (err) => {
                console.log(err);
            })
        }

    //calls the function
        getPosts().then(null);
    }

    render() {
        return (

            <div className="col-12">
                <Menu/>
                <div className="row">
                    <div className="col-12">
                        <h1 className="mt-3 display-3">Latest Posts</h1>
                    </div>
                </div>
                {
                    this.state.posts ?

                        this.state.posts.map((post) => {
                            const categories = this.state.post.forumPostId.map((category, idx) => {
                                return category.title
                            });

                            return (
                                <div className="card shadow-lg p-3 mb-4" key={post.postId}>
                                    <div className="card-body">
                                        <h5 className="card-title">{post.title}</h5>
                                        <h6 className="card-subtitle mb-2 text-muted">by {post.authorId.username}</h6>
                                        <p className="card-text">{post.summary}</p>
                                        <p className="mb-1"><small>Category: {categories.join(", ")}</small></p>
                                        <p className="mb-1"><small>tags: {post.tags.map((tag, idx) => {
                                            return <span className="badge bg-secondary me-2" key={idx}>{tag}</span>
                                        })
                                        }</small></p>
                                    </div>
                                </div>
                            )
                        })
                        : "Loading..."
                }
            </div>
        );
    }
}

export default Home;