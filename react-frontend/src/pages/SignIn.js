import {useContext, useEffect, useRef} from "react";
import {AuthContext} from "../context/AuthContext";
import {useNavigate} from "react-router-dom";

const context = useContext(AuthContext);
const emailRef = useRef("");
const passwordRef = useRef("");

let navigate = useNavigate();

useEffect(()=>{
    window.document.body.classList.add("text-center");

},[])

async function handleSubmit(event){

    event.preventDefault();

    await context.login(emailRef.current.value, passwordRef.current.value).then(()=>{
        if(context.isLoggedIn && context.currentUser != null)
        {
            navigate("/");
        }
    })


}
    return (
        <main className="form-signin w-25 m-auto">
            <form onSubmit={handleSubmit}>
                <img className="mb-4" src={logo} alt="" width="324" />
                <h1 className="h3 mb-3 fw-normal">Please sign in</h1>

                <div className="form-floating">
                    <input type="email" className="form-control" id="floatingInput" placeholder="name@example.com" ref={emailRef}/>
                    <label htmlFor="floatingInput">Email address</label>
                </div>
                <div className="form-floating">
                    <input type="password" className="form-control" id="floatingPassword" placeholder="Password" ref={passwordRef}/>
                    <label htmlFor="floatingPassword">Password</label>
                </div>


                <button className="mt-3 w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
                <p className="mt-5 mb-3 text-body-secondary"> &copy; 2017–2023</p>
            </form>
        </main>
    );


