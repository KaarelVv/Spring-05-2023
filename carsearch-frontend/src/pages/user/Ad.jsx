import React, { useContext, useEffect, useRef, useState } from 'react'
import { toast } from 'react-toastify';
import ImageDisplay from '../../components/ImageDisplay';
import { AuthContext } from '../../context/AuthContext';
import "../../css/styles.css"




function Ad() {

    const { accountId } = useContext(AuthContext);
    console.log(accountId);

    const [ads, setAds] = useState([]);
    const [isLoading, setLoading] = useState(false);


    const titleRef = useRef();
    const descriptionRef = useRef();
    const typeRef = useRef();
    const priceRef = useRef();
    const activeRef = useRef();
    const imageRef = useRef();


    useEffect(() => {
        fetch(`http://localhost:8080/account/${accountId}/ads`)
            .then(res => res.json())
            .then(json => {
                console.log(json);
                setAds(json.ads);
                console.log(json.ads);
                setLoading(false);
            })
            .catch(e => {
                toast.error("Failed to fetch ads");
                setLoading(false);
            });
    }, [accountId]);

    function add() {
        const addAd = {
            "title": titleRef.current.value,
            "description": descriptionRef.current.value,
            "type": typeRef.current.value,
            "price": priceRef.current.value,
            "active": activeRef.current.checked,
            "accountId": accountId
        }
        const formData = new FormData();

        for (let i = 0; i < imageRef.current.files.length; i++) {
            formData.append("files", imageRef.current.files[i]);
        }
        formData.append("title", addAd.title);
        formData.append("description", addAd.description);
        formData.append("type", addAd.type);
        formData.append("price", addAd.price);
        formData.append("active", addAd.active);
        formData.append("accountId", addAd.accountId);

        fetch("http://localhost:8080/ad", {
            method: "POST",
            body: formData,
        })
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .then(json => setAds(json))
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
    }

    if (isLoading) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <label>
                Image:
                <input type="file" ref={imageRef} multiple />
            </label>
            <label>
                Title:
                <input type="text" ref={titleRef} />
            </label>

            <label>
                Description:
                <input type="text" ref={descriptionRef} />
            </label>
            <label>
                Type:
                <select type="text" ref={typeRef}>
                    <option>Sell</option>
                    <option>Buy</option>
                    <option>Exchange</option>
                </select>
            </label>
            <label>
                Price:
                <input type="number" ref={priceRef} />
            </label>
            <label>
                Active:
                <input type="checkbox" ref={activeRef} />
            </label>
            <button onClick={add}>Submit</button>
            <table className='table'>
                <thead >
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Active</th>
                        <th>Date created</th>
                    </tr>
                </thead>
                <tbody>
                    {console.log(ads)}
                    {ads.map((ad, index) => (
                        <tr key={index.id}>
                            <td>{ad.title}</td>
                            <td>{ad.description}</td>
                            <td>{ad.type}</td>
                            <td>{ad.active ? 'True' : 'False'}</td>
                            <td>{ad.creationDate}</td>
                            <td>{ad.imageUrl}</td>
                            <ImageDisplay adId={ad.id} ></ImageDisplay>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default Ad