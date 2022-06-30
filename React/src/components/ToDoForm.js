import React, {useState} from 'react';
import axios from "../api/axios";
 
function ToDoForm() {
    const [taskName, setTaskName] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [deadLine, setDeadLine] = useState('');
    const [priority, setPriority] = useState(0);
 
    const handleSubmit = async (e) => {
        e.preventDefault();
        const ADDTASK_URL = "/tasks/new";
    //     const resposne = await axios.post(ADDTASK_URL, JSON.stringify({username: userLogin, password: passwrodLogin }, {headers: {'Content-Type': 'application/json'
    //   }},{ withCredentials: true, }))
      const response = await axios.post(ADDTASK_URL,
        JSON.stringify({taskName, taskDescription,"priority": priority, deadLine, "done": false }),
        {
            headers: { 'Content-Type': 'application/json' },
            withCredentials: true
        }
        
    );
    }
 
 
  return (
    <form className='task-form' onSubmit={handleSubmit}>
        <input type='text' value={taskName} placeholder='task name' name='task-name' className='task-input' onChange={(e) => setTaskName(e.target.value)}></input>
        <input type='text' value={taskDescription} placeholder='Description' name='task-description' className='task-input' onChange={(e) => setTaskDescription(e.target.value)}></input>
        <input type='datetime-local' value={deadLine}  name='task-deadline' className='task-input' onChange={(e) => setDeadLine(e.target.value)}></input>
        <input type='range' value={priority} min="0" max="3" name='task-priority' className='task-input' onChange={(e) => setPriority(parseInt(e.target.value))}></input>
        <button name='add-button' className='task-add-button'>ADD</button>
    </form>
  )
}
 
export default ToDoForm