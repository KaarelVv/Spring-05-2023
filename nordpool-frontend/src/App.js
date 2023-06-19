
import './App.css';
import { useRef,useState } from 'react';

function App() {
  const algusAeg = useRef();
  const lõpuAeg = useRef();
  const [prices, setPrices] = useState([]);

  const getNordpoolPrices = ()=> {
    // console.log(algusAeg.current.value);
    const start = algusAeg.current.value;
    const end = lõpuAeg.current.value;
    
    fetch(`http://localhost:8080/nordpool?start=${start}&end=${end}`)
    .then(response => response.json())
    .then((data) => setPrices(data))
  }

  return (
    <div className="App">
      <input ref = {algusAeg} placeholder='Algus'/>
      <input ref = {lõpuAeg} placeholder='Lõpp'/>
     <button onClick = {getNordpoolPrices}> Saada päring</button>

     <ul>
        {prices.map((price) => (
          <li key={price.timestamp}>
          <span>Timestamp: {price.timestamp}</span>
          <span>Price: {price.price}</span>
          </li>
          
        ))}
      </ul>
    </div>
  );
}

export default App;
