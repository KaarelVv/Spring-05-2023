import React, { useEffect, useState } from 'react'

function ManageAds() {

    const [ads, setAds] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/ad")
            .then(res => res.json())
            .then(json => {
                console.log(json);
                setAds(json)
            })
}, []);

const deleteAd = (id) => {
  fetch(`http://localhost:8080/ad/${id}`, {
    method: "DELETE",
    headers: { "Authorization": "Bearer " + sessionStorage.getItem("token") }
  })
    .then((response) => {
      if (response.ok) {
        console.log('Ad deleted successfully.');
        } else {
        console.error('Error deleting ad:', response.statusText);
      }
    })
    .catch((error) => {
      console.error('Error deleting ad:', error);
    });
};
  return (
    <div>  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Type</th>
        <th>Price</th>
        <th>Active</th>
        <th>Creation Date</th>
      </tr>
    </thead>
    <tbody>
      {ads.map((ad) => (
        <tr key={ad.id}>
          <td>{ad.id}</td>
          <td>{ad.title}</td>
          <td>{ad.description}</td>
          <td>{ad.type}</td>
          <td>{ad.price}</td>
          <td>{ad.active ? 'Yes' : 'No'}</td>
          <td>{new Date(ad.creationDate).toLocaleString()}</td>
          <td><button onClick={() => deleteAd(ad.id)}>Delete</button></td>
        </tr>
      ))}
    </tbody>
  </table></div>
  )
}

export default ManageAds