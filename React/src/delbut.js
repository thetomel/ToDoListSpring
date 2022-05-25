import React from 'react';
const DelText = () => {
    
    return ( 
        <button onClick={// deletes entities
            fetch(`https://localhost:8080/{}`, {
              "method": "DELETE",
              "headers": {
                "x-rapidapi-host": "fairestdb.p.rapidapi.com",
                "x-rapidapi-key": "apikey"
              }
            })
            .then(response => response.json())
            .then(response => {
              console.log(response);
            })
            .catch(err => {
              console.log(err);
            })}>DEL</button>
     );
}
 
export default DelText;