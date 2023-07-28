import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';



function CarTrims({ selectedCarId, selectedModel }) {
    const [trims, setTrim] = useState([]);


    useEffect(() => {
        fetch(`http://localhost:8080/trimbycarmodel/${selectedCarId}/${selectedModel}`)
            .then(res => res.json())
            .then(json => {
                setTrim(json.data);
                console.log(json.data);
            })
    }, [selectedCarId, selectedModel]);

    console.log(selectedCarId)
    console.log(selectedModel);


    return (
        <div className='box'>
            <table>
                <thead>
                    <tr>
                        <th>Trim Name</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    {trims.map(trim => (
                        <tr key={trim.id}>
                            <td><Link to={`/details/${trim.id}`}>{trim.name}</Link></td>
                            <td>{trim.description}</td>

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default CarTrims