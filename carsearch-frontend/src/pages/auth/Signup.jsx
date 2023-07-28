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
            .then(response => response.json())
            .then((data) => {
                console.log(data)
                
                if (data.token !== null || data.token !== undefined) {
                    setLoggedIn(true);
                    setAccountId(data.accountId);
                    console.log(data.accountId);
                    sessionStorage.setItem("token", data.token);
                    navigate('/');
                    
                } else {
                    setMessage(data.message);
                }
            });
    }
    return (
        <div>
            <div>{message}</div>
            <label>Name</label> <br />
            <input ref={nameRef} type='text'></input><br />
            <label>E-mail</label> <br />
            <input ref={emailRef} type="text" /> <br />
            <label>Parool</label> <br />
            <input ref={passwordRef} type="text" /> <br />
            <button onClick={signup}>Submit</button>
        </div>
    )
}

export default Signup