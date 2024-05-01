import React, { Component } from 'react';
import Menu from "../fragments/Menu";



class ReviewForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            business: '',
            createdBy: '',
            type: '',
            rating: '',
            description: '',
            photos: [],
            createdAt: ''
        };
    }

    handleChange = (e) => {
        this.setState({ [e.target.id]: e.target.value });
    };
    handleRatingChange = (value) => {
        this.setState({ rating: value });
    };

    handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('/api/reviews', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(this.state),
            });
            if (response.ok) {
                console.log('Review submitted successfully');
                // Optionally, you can reset the form state here
            } else {
                console.error('Failed to submit review');
            }
        } catch (error) {
            console.error('Error submitting review:', error);
        }
    };
    render() {
        const stars = [1, 2, 3, 4, 5];
        return (
            <React.Fragment>
                <Menu />
                <br />
                <div className="container">
                    <h3 className="text-center">Write a Review</h3>
                    <form action="#" method="POST">
                        <div className="mb-3">
                            <label htmlFor="busName" className="form-label">Business Name</label>
                            <input type="text" className="form-control" id="business" placeholder="Enter business name" />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Service Type</label>
                            <select className="form-select" aria-label="Default select example" required="required" id="type">
                                <option selected>Select a service type</option>
                                <option value="1">Hair</option>
                                <option value="2">Nails</option>
                                <option value="3">Other</option>
                            </select>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="photos" className="form-label">Photos</label>
                            <input type="file" className="form-control" id="photos" multiple="multiple" />
                        </div>
                        <div className="mb-3">
                            <label className="form-label">Rating</label>
                            {stars.map((star) => (
                                <div className="form-check form-check-inline" key={star}>
                                    <input
                                        className="form-check-input"
                                        type="radio"
                                        name="rating"
                                        id={`star${star}`}
                                        value={star}
                                        checked={this.state.rating === star.toString()}
                                        onChange={() => this.handleRatingChange(star.toString())}
                                    />
                                    <label className="form-check-label" htmlFor={`star${star}`}>{star}</label>
                                </div>
                            ))}
                        </div>
                        <div className="mb-3">
                            <label htmlFor="content">Detailed Description</label>
                            <textarea className="form-control" id="description" name="content" rows="10" cols="50"
                                      placeholder="Write your thoughts here..."></textarea>
                        </div>
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </form>
                </div>
            </React.Fragment>
        );
    }
}

export default ReviewForm;
