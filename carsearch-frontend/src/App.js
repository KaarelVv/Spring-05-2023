import './App.css';
import 'react-toastify/dist/ReactToastify.css';
import { Route, Routes } from 'react-router-dom';
import HomePage from "./pages/global/HomePage";
import NavigationBar from './components/NavigationBar';
import Cars from './pages/global/Car';
import CarTrims from './pages/global/CarTrims';
import CarTrimDetails from './pages/global/CarTrimDetails';
import Ad from './pages/user/Ad';
import { useContext } from 'react';
import { AuthContext } from './context/AuthContext';
import Signup from './pages/auth/Signup';
import Login from './pages/auth/Login';
import AllAds from './pages/global/AllAds';
import Profile from './pages/auth/Profile';
import ManageAds from './pages/admin/ManageAds';
import ProfileDb from './pages/admin/ProfileDb';

function App() {


  const { loggedInUser } = useContext(AuthContext);


  return (
    < div className='App'>
      <NavigationBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/car/:selectedCar" element={<Cars />} />
        <Route path="/details/:trimId" element={<CarTrimDetails />} />
        <Route path="/cartrims" element={<CarTrims />} />
        <Route path="/ads" element={<AllAds />} />
<Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        
      
        {loggedInUser?.email && (
          <>
            <Route path="/profile" element={<Profile />} />
            <Route path="/ad" element={<Ad />} />
            {loggedInUser?.admin && (
              <>
                <Route path="/profiledb" element={<ProfileDb />} />
                <Route path="/adsdb" element={<ManageAds />} />
              </>
            )}
          </>
        )}
        <Route path="*">Not found!</Route>
      </Routes>



    </div>
  );
}

export default App;
