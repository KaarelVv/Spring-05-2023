import React, { useContext, useEffect, useRef, useState } from 'react'
import { toast } from 'react-toastify';
import ImageDisplay from '../../components/ImageDisplay';
import { AuthContext } from '../../context/AuthContext';
import { Link } from "react-router-dom";
import "../../css/styles.css"




function Ad() {

    const { accountId } = useContext(AuthContext);
    console.log(accountId);

    const [ads, setAds] = useState([]);
    const [isLoading, setLoading] = useState(false);
    const [message, setMessage] = useState();


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

        if (imageRef.current.files.length > 0) {
            for (let i = 0; i < imageRef.current.files.length; i++) {
                formData.append("files", imageRef.current.files[i]);
            }
        } else {
            formData.append("files", "");
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
            .then((response) => {

                if (!response.ok) {

                    console.log(response)
                    throw response;
                }
                return response.json();

            })
            .then(json => {
                setAds(json);
                console.log("Added new ad");
                setMessage(json.message);
            })
            .catch(error => {
                error.json()
                    .then(errorMessage => {
                        console.log(errorMessage.message);
                        setMessage(errorMessage.message);
                    });
                // setMessage(error || "This") ;
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
            <label>
                <button onClick={add}>Submit</button>
            </label>
            <label>
                <div className="error">{message}</div>
            </label>

            <table className='table'>
                <thead >
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Active</th>
                        <th>Date created</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    {console.log(ads)}
                    {ads.map((ad, index) => (
                        <tr key={index.id}>
                            <td >{ad.title}</td>
                            <td>{ad.description}</td>
                            <td>{ad.type}</td>
                            <td>{ad.active ? 'True' : 'False'}</td>
                            <td>{ad.creationDate}</td>
                            <td>{ad.price}</td>
                            <td>{ad.imageUrl}</td>
                            <td><ImageDisplay key={ad.id} adId={ad.id} ></ImageDisplay></td>
                            <td><Link to={`/ad/${ad.id}`}>Edit</Link></td>
                            {console.log(ad.id)}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default Ad