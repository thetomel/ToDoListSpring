import React from 'react';
import ReactDOM from "react-dom";
import './index.css';
import App from './App';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './components/login';
import NoPage from './components/NoPage';
import Home from './Home';
import TaskListMain from './TaskListMain';
import { AuthProvider } from './context/AuthProvider';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <AuthProvider>
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<App/>}>
        <Route path="/" element={<Home/>}/>
        <Route path="tasks" element={<TaskListMain/>}/>
        <Route path="login" element={<Login/>}/>
        <Route path="/*" element={<NoPage/>}/>
      </Route>
      </Routes>
    </BrowserRouter>
    </AuthProvider>
    
     
 
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals

