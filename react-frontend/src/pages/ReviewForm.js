import React, { useState, useEffect } from 'react';
import Menu from '../fragments/Menu';
import axios from 'axios';
import Toast from "../fragments/Toast";
import bootstrap from 'bootstrap/dist/js/bootstrap.bundle.min';

const ReviewForm = () => {
    const [business, setBusiness] = useState('');
    const [username, setUsername] = useState('');
    const [type, setType] = useState('');
    const [rating, setRating] = useState('');
    const [description, setDescription] = useState('');
    const [photos, setPhotos] = useState([]);
    const [toastMessage, setToastMessage] = useState("Post successfully created.");
    const [toastColor, setToastColor] = useState("success");

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await axios.get(`http://localhost:8080/api/users/2egpUjARYPvn0yGEGOlx`);
                const user = response.data;
                const username = user.username;
                setUsername(username);
            } catch (error) {
                console.error('Error fetching username:', error);
            }
        }

        fetchData();
    }, []);

    const handleRatingChange = (value) => {
        setRating(value);
    };

    const showToast = () => {
        const toastLiveExample = document.getElementById('liveToast');
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        toastBootstrap.show();
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!business || !type || !description) {
            setToastColor("danger");
            setToastMessage("Please fill in all required fields.");
            showToast();
            return;
        }

        let data = {
            business,
            type,
            description,
            photos: photos.length > 0 ? photos : null, // Check if photos are selected
            createdBy: username,
            createdAt: Date.now()
        }

        try {
            const response = await axios.post("http://localhost:8080/api/reviews/create/", data);
            if (response.status === 200) {
                // reset form fields
                setBusiness("");
                setDescription("");
                setType("");
                setPhotos("");
                setToastColor("success");
                setToastMessage("Post successfully created.");
            }
        } catch (error) {
            console.error('Error creating post:', error);
            setToastColor("danger");
            setToastMessage("An error occurred and the review was not created.");
        } finally {
            showToast();
        }
    };

    const stars = [1, 2, 3, 4, 5];

    return (
        <>
            <Menu />
            <br />
            <div className="container">
                <h3 className="text-center">Write a Review</h3>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="busName" className="form-label">Business Name</label>
                        <input
                            type="text"
                            className="form-control"
                            required="required"
                            id="business"
                            placeholder="Enter business name"
                            value={business}
                            onChange={event => setBusiness(event.target.value) } />
                    </div>
                    <div className="mb-3">
                        <label className="form-label">Service Type</label>
                        <input
                            type="text"
                            className="form-control"
                            required="required"
                            id="type"
                            onChange={event => setType(event.target.value)}>
                        </input>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="photos" className="form-label">Photos</label>
                        <input
                            type="file"
                            className="form-control"
                            id="photos"
                            multiple="multiple"
                            onChange={event => setPhotos(event.target.files)} />
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
                                    checked={rating === star.toString()}
                                    onChange={() => handleRatingChange(star.toString())}
                                />
                                <label className="form-check-label" htmlFor={`star${star}`}>{star}</label>
                            </div>
                        ))}
                    </div>
                    <div className="mb-3">
                        <label htmlFor="content">Detailed Description</label>
                        <textarea
                            className="form-control"
                            id="description"
                            name="content"
                            rows="10"
                            cols="50"
                            placeholder="Write your thoughts here..."
                            onChange={event => setDescription(event.target.value)}></textarea>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
            <Toast message={toastMessage} color={toastColor} />
        </>
    );
};

export default ReviewForm;
