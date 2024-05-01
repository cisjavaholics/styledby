import React, {Component} from 'react';
import axios from "axios";
import Menu from "../fragments/Menu";
import ReviewComponent from "../components/ReviewComponent";


class Reviews extends Component {
    constructor(props) {
        super(props);
        this.state = {posts: null}
    }
    componentDidMount() {
        const url = "http://localhost:8080/api/reviews/"
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
                        <h1 className="mt-3 display-4 text-center">Reviews</h1>
                    </div>
                </div>

                <ReviewComponent type={"hair"} rating={2} review={"Worst food everrrrrrrrrr"} company={"Braids BY Brandee"} user={"taby212"} createdAt={"Janary 30, 2023"} width={500} height={600}/>
            </div>
        );
    }
}

export default Reviews;