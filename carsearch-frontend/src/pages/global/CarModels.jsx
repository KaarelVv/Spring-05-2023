import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CarTrims from './CarTrims';
import "../../css/styles.css"

function CarModels({ selectedCarId }) {

  const [models, setModels] = useState([]);
  const [selectedModel, setSelectedModel] = useState('Empty');

  useEffect(() => {
    fetch(`http://localhost:8080/models/${selectedCarId}`)
      .then(res => res.json())
      .then(json => {
        setModels(json.data);
        console.log(json.data);
      })
  }, [selectedCarId]);
  console.log(selectedModel);
  console.log(selectedCarId)

  const handleModelSelect = (modelName) => {
    setSelectedModel(modelName);
  };

  return (
    <div >
    <h1>There are {models.length} models available for this vehicle</h1>
    <p>To see package options, click on a model!</p>
    <ul>
      {models.map(model => (
        <li key={model.id} className='box'>
          <button onClick={() => handleModelSelect(model.name)} className='button-style'>{model.name}</button>
        </li>
      ))}
    </ul>
    <p>Selected model: {selectedModel}</p>
    {selectedModel && (
      <CarTrims selectedCarId={selectedCarId} selectedModel={selectedModel} />
    )}
  </div>
  );
}

export default CarModels;
