import { useEffect, useState } from 'react';
import DelText from './delbut';
import EditTask from './editput';
const GetToDo = () => {
    const [text,setText] = useState('');
    const [toDoText, setToDoText] =useState([]);


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
                <p>
                    {/* //ID:{task.id}<br/> */}
                    <br/>TASK:<br/>{task.task}<br></br>
                    {/* <DelText></DelText><EditTask></EditTask> */}
                </p>
            ))
            }
            </p>
            <br/>
            

        </div>
    );
}
    
export default GetToDo;

