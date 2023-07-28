import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import "../../css/trimdetail.css"

function CarTrimDetails() {

    const [trim, setTrim] = useState([]);
    const [loading, setLoading] = useState(true);
    let { trimId } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/trims/${trimId}`)
            .then(res => res.json())
            .then(json => {
                setTrim(json);
                setLoading(false);
            })
    }, [trimId]);
    console.log(trimId);

    const convertRGB = (rgb) => {
        return 'rgb(' + rgb + ')';
    }

    if (loading) {
        return <div>Loading...</div>;
    }

    if (!trim) {
        return <div>Failed to fetch data.</div>;
    }

    return (
        <div className="car-trim-details">
            <h1 className="car-trim-title">{trim.name}</h1>
            <h2 className="car-trim-description">{trim.description}</h2>
            <div >
                <p >MSRP: {trim.msrp}</p>
                <p >Created: {trim.created}</p>
                <p >Modified: {trim.modified}</p>
            </div>
            <h3>Exterior Colors:</h3>
            <ul className="color-list">
                {trim.make_model_trim_exterior_colors.map(color => (
                    <li key={color.id} style={{backgroundColor: convertRGB(color.rgb)}}>
                    {color.name} - RGB: {color.rgb}
                </li>
                ))}
            </ul>
            <h3>Interior Colors:</h3>
            <ul className="color-list">
                {trim.make_model_trim_interior_colors.map(color => (
                     <li key={color.id} style={{backgroundColor: convertRGB(color.rgb)}}>
                     {color.name} - RGB: {color.rgb}
                 </li>
                ))}
            </ul>
            <h3>Mileage:</h3>
            <p>Combined MPG: {trim.make_model_trim_mileage.combined_mpg}</p>
            <p>EPA City MPG: {trim.make_model_trim_mileage.epa_city_mpg}</p>
            <p>EPA Highway MPG: {trim.make_model_trim_mileage.epa_highway_mpg}</p>

            <h3>Engine Details:</h3>
            <p>Engine Type: {trim.make_model_trim_engine.engine_type}</p>
            <p>Fuel Type: {trim.make_model_trim_engine.fuel_type}</p>
            <p>Cylinders: {trim.make_model_trim_engine.cylinders}</p>
            <p>Engine Size: {trim.make_model_trim_engine.size}</p>
            <p>Horsepower: {trim.make_model_trim_engine.horsepower_hp} HP</p>
            <p>Torque: {trim.make_model_trim_engine.torque_ft_lbs} ft-lbs</p>
            <p>Valves: {trim.make_model_trim_engine.valves}</p>

            <h3>Body Details:</h3>
            <p>Body Type: {trim.make_model_trim_body.type}</p>
            <p>Doors: {trim.make_model_trim_body.doors}</p>
            <p>Length: {trim.make_model_trim_body.length} inches</p>
            <p>Width: {trim.make_model_trim_body.width} inches</p>
            <p>Seats: {trim.make_model_trim_body.seats}</p>
            <p>Height: {trim.make_model_trim_body.height} inches</p>
            <p>Wheel Base: {trim.make_model_trim_body.wheel_base} inches</p>
            <p>Ground Clearance: {trim.make_model_trim_body.ground_clearance} inches</p>
            <p>Cargo Capacity: {trim.make_model_trim_body.cargo_capacity} cubic feet</p>
            <p>Curb Weight: {trim.make_model_trim_body.curb_weight} lbs</p>

        </div>
    );
}
export default CarTrimDetails
