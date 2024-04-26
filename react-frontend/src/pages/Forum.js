import React, {Component} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";
import ForumComponent from "../components/ForumComponent";


class Forum extends Component {
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
                        <h1 className="mt-3 display-4 text-center">Forum</h1>
                    </div>
                </div>

                <ForumComponent title={"Finding A Nail Tech"} likes={2} description={"Hey everyone, I'm in a bit of a pickle! Can anyone lend a hand with locating a top-notch nail stylist in my area? I've been searching high and low but haven't had much luck. Ideally, I'm looking for someone skilled in intricate nail art designs and who offers a range of trendy styles. It would be amazing to find a stylist who's not only talented but also has a knack for keeping up with the latest nail trends. If anyone knows of any hidden gems or recommendations, please share! Your help would be greatly appreciated!"} topic={"Nails"} postedBy={"taby212"} postedAt={"Janary 30, 2023"} width={500} height={600}/>
            </div>
        );
    }
}

export default Forum;