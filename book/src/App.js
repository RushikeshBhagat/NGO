import React, { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import IndexPage from './IndexPage';
//import LoginPage from './LoginPage';
import AdminDashboard from './AdminDashboard'; //AdminDashboard component
import UserDashboard from './UserDashboard'; // UserDashboard component
import { UserProvider } from './UserContext';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

library.add(faSignOutAlt);

const App = () => {
  const [userType, setUserType] = useState(null);

  const handleLogin = (type) => {
    setUserType(type.toUpperCase());
  };

  return (
    
    <Routes>
      <Route path="/" element={
      <IndexPage 
      onLogin={handleLogin} 
      userType={userType}
    />
    }
    />
    
      {/*<Route
        path="/login"
        element={<IndexPage onLogin={handleLogin} />}
      />*/}

      {userType === 'ADMIN' && (
        <Route
          path="/adminLanding"
          element={<AdminDashboard />}
        />
      )}

      {userType === 'USER' && (
        <Route
          path="/userLanding"
          element={<UserDashboard />}
        />
      )}

      <Route
        path="/*"
        element={<Navigate to="/" />}
      />
    </Routes>
    
  );
};

export default App;