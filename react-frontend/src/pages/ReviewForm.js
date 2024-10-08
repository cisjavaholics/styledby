import React, {useState, useEffect, useContext} from 'react';
import Menu from '../fragments/Menu';
import axios from 'axios';
import Toast from "../fragments/Toast";
import bootstrap from 'bootstrap/dist/js/bootstrap.bundle.min';
import {Timestamp} from "firebase/firestore";
import {AuthContext} from "../context/AuthContext";

const ReviewForm = () => {
    const context = useContext(AuthContext);
    const userId = "2egpUjARYPvn0yGEGOlx";

    const [businessName, setBusinessName] = useState('');
    const [user, setUser] = useState('');
    const [type, setType] = useState('');
    const [rating, setRating] = useState('');
    const [description, setDescription] = useState('');
    const [photos, setPhotos] = useState([]);
    const [toastMessage, setToastMessage] = useState("Post successfully created.");
    const [toastColor, setToastColor] = useState("success");

    useEffect(() => {
        const getUserData = async () => {

            await axios.get(`http://localhost:8080/api/users/${userId}`).then( (response)=> {
                setUser(response.data.data.user);

            }).catch ((error) => {
                console.error(error);
            });
        };

        getUserData();
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

        if (!businessName || !type || !description) {
            setToastColor("danger");
            setToastMessage("Please fill in all required fields.");
            showToast();
            return;
        }

        let business = {        }

        try {
            // Check if business already exists
            const busResponse = await axios.get(`http://localhost:8080/api/business/name/${businessName}`);

            if (busResponse.data.data != null) {
                // Use existing business ID
                business = busResponse.data.data[0];
            } else {
                // Create new business
                const createResponse = await axios.post("http://localhost:8080/api/business/", { name: businessName });
                business = createResponse.data.data;
            }
        } catch (error) {
            console.error('Error finding/creating business:', error);
            setToastColor("danger");
            setToastMessage("An error occurred while processing the business.");
            showToast();
            return;
        }

        let data = {
            business: business,
            type,
            description,
            photos: photos.length > 0 ? photos : null, // Check if photos are selected
            createdBy: user,
            createdAt: Timestamp.now()
        }

        try {
            console.log(business, " ", user);
            const response = await axios.post("http://localhost:8080/api/reviews/create", data);
            if (response.status === 200) {
                // reset form fields
                setBusinessName("");
                setDescription("");
                setType("");
                setPhotos("");
                setToastColor("success");
                setToastMessage("Post successfully created.");
            }
        } catch (error) {
            console.error('Error creating review:', error);
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
                            id="businessName"
                            placeholder="Enter business name"
                            value={businessName}
                            onChange={event => setBusinessName(event.target.value) } />
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
