import React from 'react'
import { useEffect, useState } from 'react';
import config from '../../data/config.json';

function MaintainShops() {
  const [shops, setShops] = useState([]);
  useEffect(() => {
		
    fetch(config.backendUrl + "/shop")
		.then(res => res.json())
		.then(data => setShops(data));
	}, []);
  return (
    <div>MaintainShops</div>
  )
}

export default MaintainShops