import React, { useContext, useEffect, useRef, useState } from 'react'
import { AuthContext } from '../../context/AuthContext';

function Profile() {

    
    

    const firstNameRef = useRef();
    const lastNameRef = useRef();
    const phoneNumberRef = useRef();
    const emailRef = useRef();
    const [credentsials, setCredentsials] = useState([]);
    
  const { loggedInUser, accountId } = useContext(AuthContext);
  
    useEffect(() => {
      fetch("http://localhost:8080/user-account", {
        headers: { Authorization: "Bearer " + sessionStorage.getItem("token") },
      })
        .then((res) => res.json())
        .then((data) => {
          console.log(data);
          setCredentsials(data);
        });
    }, []);
  
    const changeProfile = () => {
      const payLoad = {
        "id": credentsials.id, //?????
        "firstName": firstNameRef.current.value,
        "lastName": lastNameRef.current.value,
        "phoneNumber": phoneNumberRef.current.value,
        "email": emailRef.current.value,
        "dateCreated": credentsials.dateCreated,
        "password": credentsials.password,
        "admin": credentsials.admin,
      };
  
      fetch("http://localhost:8080/account", {
        method: "PUT",
        body: JSON.stringify(payLoad),
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + sessionStorage.getItem("token"),
        },
      })
        .then((res) => res.json())
        
    };
  return (
    
   
    <div>
      <label>First Name</label> <br />
      <input ref={firstNameRef} type="text" defaultValue={credentsials.firstName}/>{" "}
      <br />
      <label>Last name</label> <br />
      <input ref={lastNameRef} type="text" defaultValue={credentsials.lastName}/>{" "}
      <br />
      <label>Phone number</label> <br />
      <input ref={phoneNumberRef} type="text" defaultValue={credentsials.phoneNumber}/>{" "}
      <br />
      <label>E-Mail</label> <br />
      <input ref={emailRef} type="text" defaultValue={credentsials.email}/>{" "}
      <br />
      
      <button onClick={changeProfile}>Change</button>
    </div>
    
  )
}

export default Profile