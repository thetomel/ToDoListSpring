import { useEffect, useState } from 'react';
import axios from 'axios';
import DelText from './delbut';
import EditTask from './editput';
const GetToDo = () => {
    const [text,setText] = useState('');
    const [toDoText, setToDoText] =useState([]);
    function deleteTask(e){
         //Gets value
        let id = e.target.value;
        //DELTE with axios
        axios.delete(("http://localhost:8080/del/" + id))
        .then(() => {
            alert("Task deleted"); //Info
            //setToDoText(null)//Should be but crushes all to blank
        });
    }
    

    useEffect(()=>{
        fetch('http://localhost:8080')
        .then(res=>res.json())
        .then((result)=>{
            setToDoText(result)})
    })
        
    return (  
        <div className="GetToDo">
            <p>
            {toDoText.map(task=>( 
                <div className='Task' key={task.idOfTask}>
                    <p>
                    {/* ID:{task.idOfTask}<br/> */}
                    <br/>TASK:<br/>{task.task}<br></br>
                    <button class="deleteButtons" value={task.idOfTask} /*Gives value to a button*/onClick={deleteTask}>DELETE</button>
                </p>
                </div>
            ))
            }
            </p>
            <br/>
            

        </div>
    );
}
    
export default GetToDo;

