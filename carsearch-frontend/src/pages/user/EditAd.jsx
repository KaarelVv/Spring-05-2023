import React, { useContext, useEffect, useRef, useState } from 'react'
import { AuthContext } from '../../context/AuthContext';
import { useParams } from 'react-router-dom';
import ImageDisplay from '../../components/ImageDisplay';

function EditAd() {

    const { accountId } = useContext(AuthContext);
    console.log(accountId);
    

    const [ad, setAd] = useState({});
    const [isLoading, setLoading] = useState(false);
    const [message, setMessage] = useState();


    const titleRef = useRef();
    const descriptionRef = useRef();
    const typeRef = useRef();
    const priceRef = useRef();
    const activeRef = useRef();
    const imageRef = useRef();
    const {adId} = useParams(); //destructured allready
    console.log(adId);


    useEffect(() => {
        fetch(`http://localhost:8080/ad/${adId}`)
            .then(res => res.json())
            .then(json => {
                console.log(json);
                setAd(json);
                console.log(json);
                setLoading(false);
            });
    }, [adId]);

    function change() {
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

        fetch(`http://localhost:8080/ad/${adId}`, {
            method: "PUT",

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
                setAd(json);
                console.log("Ad changed");
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
<div className="ad-detail">
        <h2>Editing Ad # {ad.id}</h2>

        <label>
            Title:
            <input type="text" ref={titleRef} defaultValue={ad.title} />
        </label>

        <label>
            Description:
            <textarea ref={descriptionRef} defaultValue={ad.description}></textarea>
        </label>

        <label>
            Type:
            <select ref={typeRef} defaultValue={ad.type}>
                {/* Assuming the types are "Buy" and "Sell", add more if needed */}
                <option value="Buy">Buy</option>
                <option value="Sell">Sell</option>
                <option value="Exchange">Exchange</option>
            </select>
        </label>

        <label>
            Price:
            <input type="number" ref={priceRef} defaultValue={ad.price} />
        </label>

        <label>
            Active:
            <input type="checkbox" ref={activeRef} defaultChecked={ad.active} />
        </label>

        <label>
            Ad Images (You can select multiple):
            <input type="file" ref={imageRef} multiple />
        </label>

        
        <div className="ad-images">
            <h3>Current Ad Images</h3>
            <ImageDisplay key={ad.id} adId={ad.id} ></ImageDisplay>
        </div>

        <button onClick={change}>Save Changes</button>

        {message && <p>{message}</p>}
    </div>
  )
}

export default EditAd