import React from 'react'
import {useState, useEffect} from 'react';
import  '../App.css';

function GameDatabase() {
    
    const [games, setGames] = useState([]);
  //ief+enter
  useEffect(() => { // iga kord kui lehele tulles kohe fetch
    fetch("http://localhost:8080/game-by-score")
      .then(response => response.json())
      .then((json) => setGames(json))
  }, []);//kandilisse läheb muutuja mida vaja fetchida kogu aeg

  const deleteGame = (id) => {
    fetch(`http://localhost:8080/game/delete/${id}`)
      .then(response => response.json())
      .then((json) => setGames(json))
  };

  return (
    <div>
        <table className='tableLayout'>
        <thead >
          <tr>
            <th colSpan="4" className='table-head'>Games played</th>
          </tr> 
          <tr>
            <th>ID</th>
            <th>Correct answer</th>
            <th>Duration</th>
            <th>Player name</th>
          </tr>
        </thead>
        <tbody>
          {games.map(game =>
            <tr key={game.id}>
              <td className='tdBold'>{game.id}</td>
              <td className='td'>{game.correctAnswers}</td>
              <td className='td'>{game.duration}</td>
              <td className='td'>{game.player.name}</td>
              <td><button onClick={() => deleteGame(game.id)}>Delete</button></td>
            </tr>)}
        </tbody>
      </table>
    </div>
  )
}

export default GameDatabase