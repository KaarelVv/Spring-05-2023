import React, { useEffect, useState } from 'react'

function ProfileDb() {

  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
      fetch("http://localhost:8080/account")
          .then(res => res.json())
          .then(json => {
              console.log(json);
              setAccounts(json)
          })

  }, []);
    

  return (
    <div>Profiles</div>
  )
}

export default ProfileDb