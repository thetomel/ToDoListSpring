
import React from 'react'
import ToDo from './components/ToDo';
import ToDoForm from './components/ToDoForm';
const  TaskListMain = () => {
    
    return ( 
        <div className="TaskListMain">
            <h2>Your Tasks</h2>
            <br></br>
            <ToDoForm ></ToDoForm>
            <ToDo></ToDo>
        </div>
     );
}
 
export default  TaskListMain;
