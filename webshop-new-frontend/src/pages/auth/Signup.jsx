import React, { useContext, useRef, useState } from 'react'
import { AuthContext } from '../../store/AuthContext'
import { useNavigate } from 'react-router-dom';
import config from "../../data/config.json"

function Signup() {
  const url = "";
  const { setLoggedIn } = useContext(AuthContext);
  const navigate = useNavigate();
  const emailRef = useRef();
  const passwordRef = useRef();
  const [message, setMessage] = useState();

  const signup = () => {
    const payLoad = {
      "email": emailRef.current.value,
      "password": passwordRef.current.value,
      // "?????": true 
    }

    fetch(config.backendUrl + "/signup", {
      method: "POST",
      body: JSON.stringify(payLoad),
      headers: {
        Authorization: "123",
        "Content-Type": "application/json"
      }
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) { //??
          // Registration successful
          setLoggedIn(true); // Update the authentication state
          navigate(url); // Redirect to the dashboard or desired page
        } else {
          setMessage(data.message); //Id juba kasutusel ?
        }
      })

  }
  return (
    <div>
      <div>{message}</div>
      <label>E-mail</label> <br />
      <input ref={emailRef} type="text" /> <br />
      <label>Parool</label> <br />
      <input ref={passwordRef} type="text" /> <br />
      <button onClick={signup}>Registreeru</button>
      
    </div>
  )
}

export default Signup