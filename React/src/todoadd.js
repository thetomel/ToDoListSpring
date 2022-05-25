import { TextField } from '@react-ui-org/react-ui';
import { useEffect, useState } from 'react';
import React from 'react';

const ToDoAdd = () => {
    const [text,setText] = useState('');
    const [toDoText, setToDoText] =useState([]);

    const handleClick=(e)=>{
        e.preventDefault()
        const task =(text);
        console.log(task);
        fetch('http://localhost:8080', {
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(task)
        }).then(()=>{
            console.log("Sending to DB")
        })
    }

    useEffect(()=>{
        fetch('http://localhost:8080/tasks')
        .then(res=>res.json())
        .then((result)=>{
            setToDoText(result)})
    })

        
    return (  
        <div className="AddToDo">
            <TextField id="addText" label="Thing To Do:" variant="filled" value={text}  onChange={(e)=>setText(e.target.value)}/>
            <button onClick={handleClick}>Add</button>
        </div>
    );
}
    
export default ToDoAdd ;

