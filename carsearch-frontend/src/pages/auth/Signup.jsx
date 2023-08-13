import React, { useContext, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';

function Signup() {

    const { setLoggedIn, setAccountId } = useContext(AuthContext);
    const navigate = useNavigate();
    const nameRef = useRef();
    const emailRef = useRef();
    const passwordRef = useRef();
    const [message, setMessage] = useState();



    const signup = () => {
        const payLoad = {
            "firstName": nameRef.current.value,
            "email": emailRef.current.value,
            "password": passwordRef.current.value,

        }
        fetch("http://localhost:8080/signup", {
            method: "POST",
            body: JSON.stringify(payLoad),
            headers: {
                "Content-Type": "application/json"
            },
        })
            .then((response) => {
                if (!response.ok) {
                    return response.text()
                        .then((text) => Promise.reject(text));
                }
                return response.json();

            })
            .then((data) => {
                console.log(data)
                if (data.token !== null || data.token !== undefined) {
                    setLoggedIn(true);
                    setAccountId(data.accountId);
                    console.log(data.accountId);
                    sessionStorage.setItem("token", data.token);
                    navigate('/');
                    window.location.reload();   

                } else {
                   
                }
            })
            .catch(error => {
                console.error("There was an error with the request:", error);
                setMessage(error || "An error occurred. Please try again later.");
            });
    }
    return (
        <div className='box-container'>
            <label>Name</label> <br />
            <input ref={nameRef} type='text'></input><br />
            <label>E-mail</label> <br />
            <input ref={emailRef} type="text" /> <br />
            <label>Parool</label> <br />
            <input ref={passwordRef} type="password" /> <br />
            <button onClick={signup}>Submit</button>
            <div>{message}</div>
        </div>
    )
}

export default Signup