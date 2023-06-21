import React from 'react'
import {useState, useEffect} from 'react'
import '../App.css';

function Players() {
    

    const [players, setPlayers] = useState([]);
    //ief+enter
    useEffect(() => {
      fetch("http://localhost:8080/player-by-score")
        .then(response => response.json())
        .then((json) => setPlayers(json))
    }, []);
  
    // const [showTable, setShowTable] = useState(false);
  
    // const handleButtonOnClick = () => {
    //   setShowTable(!showTable);
    // }
  return (
    <div >
              {/* <button onClick={handleButtonOnClick}>{showTable ? "Hide Table" : "Show players by high score"}</button>
      {showTable && ( */}
      <table className='tableLayout'>
        <thead >
        <tr>
          <th colSpan="3" className='table-head'> Players by highest score</th>
        </tr>
          <tr>
            <th>Name</th>
            <th>First Created</th>
            <th>High Score</th>
          </tr>
        </thead>
        <tbody>
          {players.map(players =>
            <tr key={players.id}>

              <td className='tdBold'>{players.name}</td>
              <td className='td'>{players.firstCreated}</td>
              <td className='td'>{players.highScore}</td>
            </tr>)}
        </tbody>
      </table>
      {/* )}  */}
    </div>
  )
}

export default Players