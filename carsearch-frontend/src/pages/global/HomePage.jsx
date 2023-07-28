import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import HomePagePictures from '../../components/home/HomePagePictures';
import "../../css/styles.css"
import AllAds from './AllAds';


function HomePage() {
    const [makes, setMakes] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        setLoading(true);
        fetch("http://localhost:8080/makes")
            .then(res => res.json())
            .then(json => {
                setMakes(json.data);
                setLoading(false);
            })
            .catch(err => {
                console.error(err);
                setError(err);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }
    if (error) {
        return <div>Error loading makes. Please try again.</div>;
    }

    return (
        <div className='box-container'>
            <h1>First project</h1>
            <div>
                <h4>If you want to browse cars, click on make.</h4>
                <h4>To see whats in the market, visit Ads page. </h4>
            </div>

            <div className='horizontal-container'>
                <div></div>
                <HomePagePictures />
                <AllAds isHomePage={true}></AllAds>
            </div>
            <h3>Choose your make to inspect models</h3>
            <div>
                <ul>
                    {makes.map((make) => (
                        <Link to={`/car/${make.name}`} key={make.id}>
                            <li className='box'>
                                {make.name}
                            </li>
                        </Link>
                    ))}
                </ul>
            </div>

        </div>
    );
};

export default HomePage;
