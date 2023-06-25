import React, {useEffect, useRef, useState } from 'react';
import config from "../../data/config.json";
import { json } from 'react-router-dom';

function MaintainCarouselPictures() {
  const carouselRef = useRef;
  const [carousels , setCarousel] = useState([]);


    useEffect(() => {
      fetch(config.backendUrl +"/carousel")
      .then(res => res.json())
      .then(json => setCarousel(json));
    }, []);

    const add = () =>{
      const newCarousel = {
        "src": carouselRef.current.value,
        "alt": carouselRef.current.value,
        "header": carouselRef.current.value,
        "description": carouselRef.current.value
      };

      fetch(config.backendUrl + "/carousel/add", {
        method: "POST",
        body: JSON.stringify(newCarousel),
        headers: { "Content-Type": "application/json" }
      })
        .then(res => res.json())
        .then(json => setCarousel(json));
    }
    
  return (
    
    <div>
    <table>
    <thead>
      <tr>
        <th>Sources</th>
        <th>Alt</th>
        <th>Header</th>
        <th>Description</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      {carousels.map( carousel=>
      <tr key={carousel.id}>
        <td>{carousel.src}</td>
        <td>{carousel.alt}</td>
        <td>{carousel.header}</td>
        <td>{carousel.description}</td>
        <td>
          {/* <button onClick={() => add()}>Lisa</button> */}
        </td>
      </tr>)}
    </tbody>
    </table>
    </div>
  )
}

export default MaintainCarouselPictures