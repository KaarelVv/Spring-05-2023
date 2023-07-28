import React, { createContext, useEffect, useState } from 'react';


// Create the context
export const AuthContext = createContext();

// Create a provider component
export const AuthContextProvider = ({ children }) => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [loggedInUser, setLoggedInUser] = useState({});
  const [accountId, setAccountId] = useState({});

  useEffect(() => {
    fetch("http://localhost:8080/user-account", {
      headers: { "Authorization": "Bearer " + sessionStorage.getItem("token") }
    })
      .then(res => {
        if (!res.ok) {
            throw new Error(`HTTP error! status: ${res.status}`);
        }
        return res.json();
      })
      .then(data => {
        console.log(data);
        setLoggedInUser(data);
        
        setAccountId(data)
        setLoggedIn(true); // remain logged in after refresh
      })
      .catch(err => {
        console.error(err);
        // Here you could add any error handling you want. 
        // For instance, if the token is invalid, you could log the user out.
        emptyUser();
      });
  }, []);
  console.log(loggedInUser.email)

  const emptyUser = () => {
    setLoggedInUser({});
    setAccountId({});
    setLoggedIn(false);
  }

  return (
    <AuthContext.Provider value={{ loggedIn, setLoggedIn, loggedInUser, setLoggedInUser, emptyUser, accountId, setAccountId }}>
      {children}
    </AuthContext.Provider>
  );
};