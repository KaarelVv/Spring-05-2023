import React from 'react'
import { useRef, useState} from 'react';


function Game() {

  const nameRef = useRef();
  const [card, setCard] = useState({}); //kandilised sulud ---> täpselt 2: muutuja ja funktsioon
  //loogilised sulud --> dünaamika, 1,2,3,4,5,6 vms muutuja/funkstsioon
  //vasakpoolne on muutuja, mida hoitakse htmls
  // function startGame(){
  // }

  const [message, setMessage] = useState("");

  const startGame = () => {
    //console.log( nameRef.current.value)    // current ---> tagi kelle sees ta on7value ---> väärtus

    fetch("http://localhost:8080/start/" + nameRef.current.value)
      .then(response => response.json())
      .then(json => setCard(json))
  }


  const guess = (guessValue) => {
    fetch("http://localhost:8080/guess/" + guessValue)
      .then(response => response.text())
      .then(text => setMessage(text))
  }

  const newRound = () => {
    setMessage("");
    fetch("http://localhost:8080/start/NEW_ROUND")
      .then(response => response.json())
      .then(json => setCard(json))
  }

  const newGame = () => {
    setMessage("");
    setCard({});
  }
  return (
    <div>{card.value === undefined &&
        <div>
          <input ref={nameRef} placeholder="Mängija nimi" />
          <button onClick={startGame}>Alusta mängu</button>
        </div>}

      <div>{card.rank}</div>
      <div>{card.suit}</div>
      {card.value !== undefined && message === "" &&
        <div>
          <button onClick={() => guess("lower")}>Väiksem</button>
          <button onClick={() => guess("equal")}>Võrdne</button>
          <button onClick={() => guess("higher")}>Suurem</button>
        </div>}

      <div>{message}</div>
      {message !== "" && message !== "Game Over!" && <button onClick={newRound}>Alusta uut mängu</button>}
      {message === "Game Over!" && <button onClick={newGame}>Alusta uut mängu</button>}</div>
  )
}

export default Game