import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import ImageDisplay from '../../components/ImageDisplay';
import 'bootstrap/dist/css/bootstrap.css';

function AllAds({ isHomePage = false }) {
    const [ads, setAds] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/ad')
            .then(response => response.json())
            .then(data => {
                setAds(data.filter(ad => ad.active));

            })
            .catch(error => console.error(error));
    }, []);


    const adsForHomepage = (ads) => {
        return isHomePage ? ads.slice(0, 3) : ads;
    }


    return (
        <div className="container my-3">
            {isHomePage && <h5>Recently added advertisement</h5>}
            {adsForHomepage(ads).map((ad) => (
                <div key={ad.id} className="list-group my-3">
                    <div className="list-group-item">
                        {isHomePage ? (
                            <div className="horizontal-container">
                                <p >Type: {ad.type}</p>
                                <p >Price: {ad.price}</p>
                                <p >Date Created: {new Date(ad.creationDate).toLocaleDateString()}</p>
                                <p >Description: {ad.description}</p>
                            </div>
                        ) : (
                            <>
                                <div className='static-ad'>
                                    <Link to={`/ad/details/${ad.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                                        <h5>Title: {ad.title}</h5>
                                        <p>Description: {ad.description}</p>
                                        <p>Type: {ad.type}</p>
                                        <p>Price: {ad.price || "Not available"}</p>
                                        <p>Date Created: {new Date(ad.creationDate).toLocaleDateString()}</p>
                                        <p>Images: <ImageDisplay adId={ad.id} /></p>
                                    </Link>
                                </div>
                            </>
                        )}
                    </div>
                </div>
            ))}
        </div>
    );
}

export default AllAds;