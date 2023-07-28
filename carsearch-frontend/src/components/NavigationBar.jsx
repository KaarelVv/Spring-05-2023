import React, { useContext } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

function NavigationBar() {
  const { loggedIn, setLoggedIn, loggedInUser, emptyUser } = useContext(AuthContext);
  const navigate = useNavigate();



  const logout = () => {
    setLoggedIn(false);
    sessionStorage.removeItem("token");
    navigate("/");
    emptyUser();
  }

  return (
    <Navbar expand="lg" bg="dark" variant="dark" >
      <Container>
        <Navbar.Brand as={Link} to="/">Home Page</Navbar.Brand>
        <Navbar.Brand as={Link} to="/ads">Market</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto ">


            {loggedInUser.email !== undefined &&
              <Nav.Link as={Link} to="/ad" >Upload ad</Nav.Link>}
            <NavDropdown title="Account" id="basic-nav-dropdown">
              {loggedIn === false ?
                <>
                  <NavDropdown.Item as={Link} to="/signup">Sign up</NavDropdown.Item>
                  <NavDropdown.Divider />
                  <NavDropdown.Item as={Link} to="/login">Log in</NavDropdown.Item>
                </>
                :
                <>
                  <NavDropdown.Item as={Link} to="/profile">Profile settings</NavDropdown.Item>
                  <NavDropdown.Divider />
                  <NavDropdown.Item onClick={logout}>Sign out</NavDropdown.Item>
                </>
              }
            </NavDropdown>
            {loggedInUser.admin === true &&
              <NavDropdown title="Admin" id="basic-nav-dropdown">
                <NavDropdown.Item as={Link} to="/profiledb">Profiles</NavDropdown.Item>
                <NavDropdown.Item as={Link} to="/adsdb">Ads DB</NavDropdown.Item>
              </NavDropdown>}
            {loggedInUser.email !== undefined && <div style={{ color: '#fff' }}>Signed in!<br />| Name: {loggedInUser.firstName} | Email: {loggedInUser.email} | ID: {loggedInUser.id} |</div>}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default NavigationBar
