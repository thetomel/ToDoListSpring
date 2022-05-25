import logo from './logo.svg';
import Navbar from './navbar';
import Home from './Home';
import React from 'react';

function App() {
  return (
    <div className="App">
      <Navbar />
        <div className="content">
            <Home></Home>
        </div>
    </div>
  );
}

export default App;
