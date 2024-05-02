import React, {createContext, useState} from "react";


const AuthContext = createContext({
    currentUser: {},
    setCurrentUser: ()=>{},
    isLoggedIn: false,
    login: () => {}
});

const AuthProvider = ({children}) => {

    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [currentUser, setUser] = useState(null);

    //REPLACE WITH DATA FROM YOUR FIREBASE
    const fakeUser =
    {
        "userId": "2egpUjARYPvn0yGEGOlx",
        "username": "If your reading this",
        "email": "UpdateWorksWithObjectList@yess.com",
        "reviews": null,
        "saved": null,
        "businesses": [
        {
            "businessId": "5fXEmlRvOkWBOMKKjgsz",
            "name": "StyledbyQuay",
            "category": "skin",
            "rating": 4,
            "reviews": null
        }
        ]
    };

    const setCurrentUser = (user) => setUser(user)

    const login = (email, password) => {
        //TODO: Add firebase log in with backend token

        setCurrentUser(fakeUser);
        setIsLoggedIn(true);
    }

    return (
        <AuthContext.Provider
            value={{
            currentUser,
            isLoggedIn,
            setCurrentUser,
            login
        }}>
            {children}
        </AuthContext.Provider>
    );
}

const AuthConsumer = AuthContext.Consumer;
export{AuthContext, AuthConsumer, AuthProvider};