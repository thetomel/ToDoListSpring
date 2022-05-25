import AddToDo from './todoadd.js';
import GetToDo from './todoget.js';
import React from 'react';



const  Home = () => {
    
    return ( 
        <div className="Home">
            <h2>Your Tasks</h2>
            <AddToDo></AddToDo>
            <br></br>
            <GetToDo></GetToDo>
        </div>
     );
}
 
export default  Home;