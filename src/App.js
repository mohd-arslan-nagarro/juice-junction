import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import HomePage from './Common/HomePage';
import UserLogin from './User/UserLogin';
import AdminLogin from './admin/AdminLogin';
import SignUp from './User/SignUp';

function App() {
  return (
    <BrowserRouter>
      <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="userLogin" element={<UserLogin />} />
          <Route path="adminLogin" element={<AdminLogin />} />
          <Route path="signUp" element={<SignUp />} />
        {/* </Route> */}
      </Routes>
    </BrowserRouter>
    // <HomePage/>
  //  <UserLogin/>
  //  <AdminLogin/>
  //  <SignUp/>
  );
}

export default App;
