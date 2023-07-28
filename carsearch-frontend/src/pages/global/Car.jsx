import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CarModels from "./CarModels";
import "../../css/styles.css";



function Car() {
    const [makes, setMake] = useState([]);


    let { selectedCar } = useParams();
    console.log(selectedCar)

    useEffect(() => {
        fetch(`http://localhost:8080/make/${selectedCar}`)
            .then((res) => res.json())
            .then((json) => {
                setMake(json.data);
                console.log(json.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, [selectedCar]);
    if (!selectedCar) {
        return <div>No car selected!</div>;
    }
    if (!makes) {
        return <div>Loading...</div>;
    }

    return (
        <div className='parent-container'>
            <div className='box-container'>
                <ul>
                    {makes.map((make) => (
                        <li key={make.id} >
                            <h1>Make: {make.name}</h1>
                            <h1>ID: {make.id}</h1>
                            <br></br>
                            <CarModels selectedCarId={make.id} />
                        </li>

                    ))}
                </ul>

            </div>
        </div>
    );
}

export default Car;
