import React, { useContext, useRef, useState } from 'react';
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from 'react-router-dom';



function Login() {

  const {setLoggedIn, setLoggedInUser, setAccountId } = useContext(AuthContext);
  const navigate = useNavigate();
  const emailRef = useRef();
  const passwordRef = useRef();
  const [message, setMessage] = useState();

  const login = () => {
    const payLoad = {
      "email": emailRef.current.value,
      "password": passwordRef.current.value,

    }

    fetch("http://localhost:8080/login", {
      method: "POST",
      body: JSON.stringify(payLoad),
      headers: {
        "Content-Type": "application/json"
      },
    })
      .then(response => response.json())
      .then(data => {
        console.log(data)
        setAccountId(data.accountId);
        console.log(data.accountId);
      
        if (data.token !== null && data.token !== undefined) {
          setLoggedIn(true);
          fetch("http://localhost:8080/user-account", {
            headers: { Authorization: "Bearer " + data.token },
          })
            .then((res) => res.json())
            .then((data) => {
              setLoggedInUser(data);
              console.log(data);
              
              
            });
           navigate('/');
          sessionStorage.setItem("token", data.token); 
        } else {
          setMessage(data.message);
        }
      });

  }

  return (
    <div>
      <div>{message}</div>
      <label>E-mail</label> <br />
      <input ref={emailRef} type="text" /> <br />
      <label>Parool</label> <br />
      <input ref={passwordRef} type="text" /> <br />

      <button onClick={login}>Log in</button>

    </div>
  )
}

export default Login