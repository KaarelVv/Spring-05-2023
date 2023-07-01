import React, { useContext, useRef, useState } from 'react';
import { AuthContext } from '../../store/AuthContext';
import { useNavigate } from 'react-router-dom';
import config from "../../data/config.json";
import {Link} from "react-router-dom";

function Login() {
  const url = "/";
  const { setLoggedIn } = useContext(AuthContext);
  const navigate = useNavigate();
  const emailRef = useRef();
  const passwordRef = useRef();
  const [message, setMessage] = useState();

  const login = () => {
    const payLoad = {
      "email": emailRef.current.value,
      "password": passwordRef.current.value,
      // "????": true
    }


    // TODO: Backendi päring
    fetch(config.backendUrl + "/login", {
      method:"POST",
      body: JSON.stringify(payLoad),
      headers: {
        Authorization: "123", 
        "Content-Type": "application/json"},
    })

   
  }
 
  return (
    <div>
      <div>{message}</div>
      <label>E-mail</label> <br />
      <input ref={emailRef} type="text" /> <br />
      <label>Parool</label> <br />
      <input ref={passwordRef} type="text" /> <br />
      {/* <Link to={url}> */}
      <button onClick={login}>Logi sisse</button>
      {/* </Link> */}
    </div>
  )
}

export default Login