
import './App.css';
import { useRef ,useState, useEffect} from 'react';


// tumesinine - tag:div, input, button
// heleinine- property: placeholder, className
// punane7oranz - string: "App", "Mängija nimi"
// valge - väljanäidatav tekst
function App() {
  const nameRef = useRef();
  const [card, setCard] = useState({}); //kandilised sulud ---> täpselt 2: muutuja ja funktsioon
                                        //loogilised sulud --> dünaamika, 1,2,3,4,5,6 vms muutuja/funkstsioon
      //vasakpoolne on muutuja, mida hoitakse htmls
  // function startGame(){
  // }

  const [message, setMessage] = useState("");

  const startGame = () => {
   //console.log( nameRef.current.value)    // current ---> tagi kelle sees ta on7value ---> väärtus

   fetch("http://localhost:8080/start/" + nameRef.current.value )
   .then(response => response.json())
   .then(json=> setCard(json))
  }

  
  const guess = (guessValue) => {
    fetch("http://localhost:8080/guess/" + guessValue )
    .then(response => response.text())
    .then(text=> setMessage(text))
   }

   const newRound = () => {
    setMessage("");
    fetch("http://localhost:8080/start/NEW_ROUND")
    .then(response => response.json())
    .then(json=> setCard(json))
   }

   const newGame = () => {
    setMessage("");
    setCard({});
   }

   const[games, setGames] = useState([]);
   //ief+enter
   useEffect(() => { // iga kord kui lehele tulles kohe fetch
    fetch("http://localhost:8080/game-by-score")
    .then(response => response.json())
    .then((json) => setGames(json) )
   }, [card]);

   const deleteGame = (id) => {
    fetch(`http://localhost:8080/game/delete/${id}`)
    .then(response => response.json())
    .then((json) => setGames(json))
    };

    const[players, setPlayers] = useState([]);
    //ief+enter
    useEffect(() => { 
     fetch("http://localhost:8080/player-by-score")
     .then(response => response.json())
     .then((json) => setPlayers(json) )
    }, []);

    const [showTable ,setShowTable] = useState(false);

    const handleButtonOnClick = () => {
      setShowTable(!showTable);
    };
   



 
  

  return (
<div className="App">

{card.value === undefined  && 
<div>
<input ref={nameRef} placeholder="Mängija nimi"/>
<button onClick={startGame}>Alusta mängu</button>
</div>}

<div>{card.rank}</div>
<div>{card.suit}</div>
{card.value !== undefined && message === "" &&
  <div>
<button onClick = {() => guess("lower")}>Väiksem</button>  
<button onClick = {() => guess("equal")}>Võrdne</button>
<button onClick = {() => guess("higher")}>Suurem</button>
</div>}

<div>{message}</div>
{message !== "" && message !=="Game Over!" && <button onClick={newRound}>Alusta uut mängu</button>}
{message === "Game Over!" && <button onClick={newGame}>Alusta uut mängu</button>}
<br/><br/>
<table>
  <thead >
    Games by correct answer
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
        <td>{game.id}</td>
        <td>{game.correctAnswers}</td>
        <td>{game.duration}</td>
        <td>{game.player.name}</td>
        <td><button onClick={() => deleteGame(game.id)}>Delete</button></td>
      </tr>)}
    </tbody>
  </table>
  <br/><br/>
  <button onClick={handleButtonOnClick}>{showTable ? "Hide Table" : "Show players by high score"}</button>
  {showTable &&(<table>
  <thead>
    Players by highest score
  <tr>
  <th>Name</th>
    <th>First Created</th>
    <th>High Score</th>
    </tr>
  </thead>
    <tbody>
    {players.map(players =>
      <tr key={players.id}> 
        <td>{players.name}</td>
        <td>{players.firstCreated}</td>
        <td>{players.highScore}</td>
      </tr>)}
    </tbody>
    </table>)}
</div>  


  );
}

export default App;


