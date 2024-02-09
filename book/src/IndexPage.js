import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './IndexPage.css';

const IndexPage = ({ onLogin, userType }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [authResult, setAuthResult] = useState(null);
  const navigate = useNavigate();
  
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        'http://localhost:8080/authenticate',
        {
          username,
          password,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );

      const result = response.data;
      setAuthResult(result);
      console.log(result)
      console.log(username)
      console.log(password)
      console.log(result.first_name)
      
      if (result.authenticated) {
        onLogin(result.userType.toUpperCase()); // Notify App.js about successful login
      } else {
        setErrorMessage('Incorrect credentials. Please try again.');
      }
      
    } catch (error) {
      console.error('Error during authentication:', error.message);
    }
  };

  useEffect(() => {
    if (authResult && userType === 'ADMIN') {
      navigate('/adminLanding', { state: { firstName: authResult.first_name, lastName: authResult.last_name } });
    } else if (authResult && userType === 'USER') {
      navigate('/userLanding');
    }
  }, [authResult ,userType, navigate]);

  return (
    <div className="container">
      <header className="header">
        <h1>NGO Donation</h1>
      </header>
      <div className="content-container">
        <form autoComplete="off" className="form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label className="input"> Username:</label>
          <input type="text" 
          placeholder="Enter Username"
          value={username} 
          autoComplete="false"
          onChange={(e) => setUsername(e.target.value)} />
        </div>
        <br />
        <div className="form-group">
          <label className="input">Password: </label>
          <input type="password" 
          placeholder="Enter Password"
          value={password} 
          autoComplete="false"
          onChange={(e) => setPassword(e.target.value)} />
        </div>
        <br />
          <button type="submit" className="submit-button">
            Login
          </button>
        </form>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      </div>
      <footer className="footer">
        <p>&copy; NGO Donation 2024. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default IndexPage;
