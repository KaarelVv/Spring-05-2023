import React, { useEffect, useState } from 'react'
import { json, useParams } from 'react-router-dom'
import config from "../../data/config.json";
import { Spinner } from 'react-bootstrap';

function SingleProduct() {

  const { id } = useParams();
  
  const [dbProducts, setDbProducts] = useState([]);
// 240tk
  // const dbProducts = dbProducts.find(e => e.id === Number(id));
  const [isLoading, setLoading] = useState(false);
  
  useEffect(() => {
    // TODO: Backendi päring
    fetch(config.backendUrl + "/product/" + id )
    .then(res => res.json())
    .then(json => {setDbProducts(json,
      console.log(json))})
  }, [id]);
  


  

  if (isLoading === true) {
    return <Spinner />
  }

  return (
    <div>
      {dbProducts !== undefined && 
        <div>
          <img  src={dbProducts.image} alt="" />
          <div>ID: {id}</div>
          <div> Name: {dbProducts.name} </div>
          <div> Price: {dbProducts.price} </div>
          <div > Category: {dbProducts.category && dbProducts.category.name} </div>
  {/*alguses dbProduct.category(kui olemas) siis dbProducts.category.name(kuvatakse kategooria nimi)  */}
          <div> Description: {dbProducts.description} </div>
        </div>}
      {dbProducts === undefined && <div>Not dbProducts</div>}
    </div>
  )
}

export default SingleProduct