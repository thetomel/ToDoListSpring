const EditTask = () => {
    const handleClick=(e)=>{
        e.preventDefault()
        fetch('http://localhost:8080/{id}', {
            method:"DEL",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify()
        }).then(()=>{
            console.log("Sending to DB")
        })
    }
    return (  
        <button onClick={handleClick}>Edit</button>
    );
}
 
export default EditTask;