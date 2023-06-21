import React, { useEffect, useState } from 'react'
import '../Table.css';

function Homepage() {

  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/public-products")
      .then(res => res.json())
      .then(data => setProducts(data));
  }, []);

  return (
    <div>

      <table className='layout'>
        <tr >
          <th className='td'>Nimetus</th>
          <th className='td'>Hind</th>
          <th className='td'>Kirjeldus</th>
        </tr>
        <tbody className='td'>

          {products.map(product =>
            <tr key={product.id}>
              <td className='td'>{product.name}</td>
              <td className='td'>{product.price}$</td>
              <td className='td'>{product.description}</td>

              <td className='td'><img src={product.image} alt='' className='custom-size' /></td>

              <td className='td'><button>Lisa ostukorvi</button></td>
            </tr>)}

        </tbody>

      </table>
    </div>
  )
}

export default Homepage