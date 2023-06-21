
import { Link, Route, Routes } from 'react-router-dom';
import './App.css';
import Game from './pages/Game';
import GameDatabase from './pages/GameDatabase';
import Players from './pages/Players';


// tumesinine - tag:div, input, button
// heleinine- property: placeholder, className
// punane7oranz - string: "App", "Mängija nimi"
// valge - väljanäidatav tekst
function App() {
  return (
    <div className="App">

      <Link to="/">
        <button className='button'>Play game</button>
      </Link>

      <Link to="games">
        <button className='button'>Manage games</button>
      </Link>

      <Link to="players">
        <button className='button'>Show players</button>
      </Link>

      <Routes>

        <Route path='' element={<Game />} />
        <Route path='games' element={<GameDatabase />} />
        <Route path='players' element={<Players />} />


      </Routes>

    </div>




  );
}

export default App;


