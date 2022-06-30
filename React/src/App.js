import Navbar from './navbar';
import React from 'react';
import { Outlet} from 'react-router-dom';



function App() {
  return (
    <div className="App">
      <Navbar/>
      
      <div className='content'>
      <Outlet />
      {/* <div className="content">
      </div> */}
      </div>
    </div>
  );
}

export default App;
