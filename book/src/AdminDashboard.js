// AdminLanding.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useLocation } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import './AdminDashboard.css';
import { UserProvider, useUser } from './UserContext';

import { library } from '@fortawesome/fontawesome-svg-core';
import { faPowerOff } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

library.add(faPowerOff); // Add the power-off icon to the library



const AdminDashboard = () => {
  
  const [activeNavLink, setActiveNavLink] = useState('userManagement'); // Default active navlink
  const { state } = useLocation();
  const { user, updateUser } = useUser(); 
  const { firstName, lastName } = state || {};
  const { setUser } = useUser();  
  const navigate = useNavigate();
  
  console.log(firstName)
  console.log(lastName);
  const handleLogout = () => {
    console.log('Logout clicked');
    updateUser(null);
    navigate('/');
  };
  
  return (
    <div className="container">
<header className="header"> 
  <h1>NGO Donation</h1>
  <div className="user-info">
    <h2>
      <span>Welcome, {firstName} {lastName}</span>
    </h2>
  </div>
  <div className="logout-button" onClick={handleLogout}>
    <FontAwesomeIcon icon="power-off" />
  </div>
  
</header>


      <div className="admin-dashboard">
      {/* Sidebar */}
      <div className="sidebar">
        
        <nav>
          <Link
            to="/adminLanding/userManagement"
            className={activeNavLink === 'userManagement' ? 'active' : ''}
            onClick={() => setActiveNavLink('userManagement')}
          >
            User Management
          </Link>
          <Link
            to="/adminLanding/donationManagement"
            className={activeNavLink === 'donationManagement' ? 'active' : ''}
            onClick={() => setActiveNavLink('donationManagement')}
          >
            Donation Management
          </Link>
          <Link
            to="/adminLanding/userView"
            className={activeNavLink === 'userView' ? 'active' : ''}
            onClick={() => setActiveNavLink('userView')}
          >
            User View
          </Link>
        </nav>
      </div>

      {/* Main content */}
      <div className="main-content">
        {/* Header */}

        {/* Content for each navigation link */}
        {/* You can include different components for each section based on the activeNavLink */}
        {/* For simplicity, I'm just displaying the activeNavLink for now */}
        <div className="content">
          <h3>{activeNavLink}</h3>
          {/* Add specific content for each section */}
        </div>
      </div>
    </div>
      <footer className="footer">
        <p>&copy; NGO Donation 2024. All rights reserved.</p>
      </footer>
    </div>
  );
};

export default AdminDashboard;
