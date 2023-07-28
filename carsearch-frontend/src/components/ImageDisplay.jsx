import React, { useEffect, useState } from 'react';
import "../css/styles.css"

function ImageDisplay({ adId}) { 
    const [imageData, setImageData] = useState([]);
    console.log(adId)

    useEffect(() => {
        if (adId) {
            console.log( adId);
            fetch(`http://localhost:8080/ad/${adId}/images`)
                .then(response => response.json())
                .then(images => {
                    console.log(images);
                    setImageData(images);
                });
        } else {
            console.log('adId is undefined');
        }
    }, [adId]);

    return (
        <div>
            {imageData.map((img, index) => (
                <img key={index} className='ad-picture'  src={img} alt='Your img'   />
            ))}
        </div>
    )
}

export default ImageDisplay