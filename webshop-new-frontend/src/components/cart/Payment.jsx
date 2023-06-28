import React, { useEffect, useState } from 'react'
import config from "../../data/config.json";
//import { json, useParams } from 'react-router-dom';
// import { calcSum } from "./../../pages/global/Cart";


//({ sum })
function Payment(props) {
  

  const { sum } = props;

  const [link, setLink] = useState();

  // const [products, setProducts] = useState();

  // useEffect(() => {
  //   fetch(config.backendUrl + "/product")
  //     .then(res => res.json())
  //     .then(json => {
  //       setProducts(json || []);
  //     })
  // }, []);

function pay() {
    // const cartProducts =
      
    
    fetch(config.backendUrl + "/payment/1/" + sum, {
      method: "POST",
      body: JSON.stringify(),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then(response => response.json())
      .then(data => setLink(data));
  
  }

  return (
    <div>
    <button onClick={pay}>MAKSMA</button>
    {link && (
      <a href={link.link} target="_blank" rel="noopener noreferrer">
        Payment Link
      </a>
    )}
  </div>
  )
}

export default Payment