import { useContext, useEffect, useRef } from "react";
import { AuthContext } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import logo from "your-logo-path"; // Replace with your logo path

const LoginForm = () => {
    const context = useContext(AuthContext);
    const emailRef = useRef("");
    const passwordRef = useRef("");
    const navigate = useNavigate();

    useEffect(() => {
        window.document.body.classList.add("text-center");

        // Cleanup function to remove added class on unmount
        return () => {
            window.document.body.classList.remove("text-center");
        };
    }, []);

    async function handleSubmit(event) {
        event.preventDefault();

        await context.login(emailRef.current.value, passwordRef.current.value).then(() => {
            if (context.isLoggedIn && context.currentUser != null) {
                navigate("/");
            }
        });
    }

    return (
        <main className="container">
            <div className="logo">StyledBy</div>
            <div className="login-heading">Login</div>
            <form onSubmit={handleSubmit}>
                <input type="email" className="form-control" id="email" placeholder="Email" ref={emailRef} required />
                <input type="password" className="form-control" id="password" placeholder="Password" ref={passwordRef} required />
                <div className="forgot-password">Forgot password</div>
                <button type="submit" className="btn btn-primary">Login &rarr;</button>
            </form>
            <div className="register-text">
                New to StyledBy? <button className="register-button btn btn-primary">Register Here</button>
            </div>
        </main>
    );
};

export default LoginForm;

