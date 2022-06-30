import React from 'react'
import { ImCross, ImPencil, ImCheckboxUnchecked , ImCheckboxChecked} from "react-icons/im";
import axios from '../api/axios';
import ToDoForm from './ToDoForm';

class ToDo extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            DataisLoaded: false
        };
        this.edit = false;
   
       
    }
    

    
    
    componentDidMount() {
        fetch(
    "http://localhost:8080/tasks")
    .then(response => response.json())
    .then(data => {
        const itemsMap = new Map();
        data.forEach(element => itemsMap.set(element.taskID, element));
        this.setState(
          {
            items: itemsMap,
            DataisLoaded: true
          }
        );
      });
    }
    
    
    render() {
        const handleClick = (id)=>{
            // e.preventDefault();
            const STATE_URL ="/tasks/" + id+ "/state";
            axios.patch(STATE_URL);
      
            const { items } = this.state;
            const modifiedItem = items.get(id);
            modifiedItem.done = !modifiedItem.done;
            items.set(id, modifiedItem);
            this.setState({ items });
            console.log(items.get(id))
        }
        const removeTask =(id)=>{
          const {items} = this.state;
          console.log(items)
          items.delete(id);
          console.log(items)
          this.setState(   items);
          const DEL_URL="/tasks/" + id;
          axios.delete(DEL_URL)
        }
        const editTask = (id) =>{
            const {items} = this.state;
            const item = items.get(id);
            
            
        }

        const { DataisLoaded, items } = this.state;
        if (!DataisLoaded) {
            return <div>
            <h1> Pleses wait some time.... </h1> </div>;
            }
       
        
            return [...items].map(([taskID, task])=>(
                
                <div className={task.done ? 'task-done' : 'task'} key={taskID}> 
                    <div><h3 onClick={()=>handleClick(task.taskID)}> {(task.done)? <ImCheckboxChecked />: <ImCheckboxUnchecked />}{task.taskName}</h3></div>
                    <div className='icons'><ImCross className='delete-icon' onClick={()=>removeTask(task.taskID)}/><ImPencil className='edit-icon' onClick={()=>editTask(task.taskID)}/></div>
                    <div><h4>User {task.username} | {task.deadline} | {task.uploadDate} | </h4> </div>
                </div>
              )) ;
            
    

      
}
    }
    
    



export default ToDo