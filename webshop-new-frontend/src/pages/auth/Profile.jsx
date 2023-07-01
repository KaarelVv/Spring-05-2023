import React, { useContext, useRef } from 'react'
import { AuthContext } from '../../store/AuthContext';
import config from "../../data/config.json";

function Profile() {
  const nameRef = useRef();
  const emailRef = useRef();
  const url = "";
  const { loggedInUser } = useContext(AuthContext);

  const changeProfile = () => {
    const payLoad = {
      
      "firstName":nameRef.current.value,
      "email":emailRef.current.value,
      
      "????": true
    }

    // TODO: Backendi päring
    fetch(config.backendUrl + "person")
    .then(res => res.json())
    .then(data => data.json())
  }

  return (
    <div>
      {loggedInUser.users !== undefined &&
        <div>
          <label>Display Name</label> <br />
          <input ref={nameRef} type="text" defaultValue={"VANA NIMI"} /> <br />
          <label>Photo URL</label> <br />
          <input ref={emailRef} type="text" defaultValue={"VANA EMAIL"} /> <br />
          <button onClick={changeProfile}>Change</button>
        </div>}
    </div>
  )
}

export default Profile