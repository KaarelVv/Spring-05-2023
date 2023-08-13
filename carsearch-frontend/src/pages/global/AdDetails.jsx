import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';

function AdDetails() {
    const [ad, setAd] = useState({});
    const [isLoading, setLoading] = useState(false);
    const [message, setMessage] = useState();
    const {adId} = useParams();
    console.log(adId);

    useEffect(() => {
        fetch(`http://localhost:8080/ad/${adId}`)
            .then(res => res.json())
            .then(json => {
                console.log(json);
                setAd(json);
                setLoading(false);
            });
    }, [adId]);


  return (
    <div>
      
    </div>
  )
}

export default AdDetails