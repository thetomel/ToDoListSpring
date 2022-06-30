import React from 'react'
import { Link } from 'react-router-dom';
import './index.css';

const Navbar = () => {
    return ( 
        <nav className="navbar">
            <h1><b>To Do List</b></h1>
            <div className="links">
                <Link to="/">Home</Link>
                <Link to="/tasks">Tasks</Link>
                <Link to="/login">Sign in</Link>
            </div>
        </nav>
     );
}
 
export default Navbar;