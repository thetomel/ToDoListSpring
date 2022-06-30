import React, {useState} from 'react'
import ToDoForm from './ToDoForm'

function ToDoList() {
    const [todos, setTodos] = useState({
        taskName: '',
        taskDescription: '',
        deadLine: '',
        priority: 0,
    }); 
    const addTask = task =>
    {
        const newTasks =[task, ...tasks]
        console.log(newTasks)
        // setTodos(newTasks);
    }
  return (
    <div>
        <h1>Tasks</h1>
        <ToDoForm onSumbit={addTask} />
    </div>
  )
}

export default ToDoList