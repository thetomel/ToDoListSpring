import React, {useState, useRef, useEffect, useContext} from 'react'
import AuthContext from '../context/AuthProvider';
import { Link } from 'react-router-dom';
import axios from "../api/axios";

const LOGIN_URL = "/api/login";
const Login = () => {
  const userRef = useRef(null);
const errRef = useRef();

  const {setAuth} = useContext(AuthContext);
  const  [userLogin, setUserLogin] = useState('');
  const  [passwrodLogin, setPasswordLogin] = useState('');
  const [errMsg, setErrMsg] = useState('');

  useEffect(() => {
    userRef.current.focus();
}, [])

  useEffect(()=>{
    setErrMsg('');}, [userLogin, passwrodLogin])

  const handleSubmit = async (e) =>{
    e.preventDefault();
    try{
      //   const resposne = await axios.post(LOGIN_URL, JSON.stringify({username: userLogin, password: passwrodLogin }, {headers: {'Content-Type': 'application/x-www-form-urlencoded'
      // }},{ withCredentials: true, }))

      // Access-Control-Allow-Origin : *,
        
        const params = new URLSearchParams();
        params.append('username', userLogin);
        params.append('password', passwrodLogin);
        const resposne = await  axios.post(LOGIN_URL, params, {headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        withCredentials: true
      });
        const accessToken = resposne.data.access_token;
        setUserLogin('');
        setAuth({userLogin, passwrodLogin, accessToken});
        setPasswordLogin('');
        
    console.log(accessToken)
    } catch(err){
      if (!err.response) {
        setErrMsg('No Server Response');
    } else if (err.response.status === 400) {
        setErrMsg('Missing Username or Password');
    } else if (err.response.status === 401) {
        setErrMsg('Unauthorized');
    } else if (err.response.status === 403){
      setErrMsg('Wrong Username or Password');

    }
    else {
        setErrMsg('Login Failed');
    }
    errRef.current.focus();
    }
    
  }
  return (

    
    
    <div className='login-page'>
    <b>Sign in</b>
    <p ref={errRef} className={errMsg ? "err-msg" : "err-msg-off"} aria-live="assertive">{errMsg}</p>
    <form onSubmit={handleSubmit}>
      <label>User:</label>
        <input ref={userRef} type="text" name="username-login" placeholder='username' requried autoComplete='off' value={userLogin} onChange={(e)=>{setUserLogin(e.target.value)}}></input>
      <label>Password:</label>
        <input type="password" name="passwod-login" placeholder='password' required autoComplete='off' value={passwrodLogin} onChange={(e)=>{setPasswordLogin(e.target.value)}}></input>
        <br/>
        <button>Login</button>
    </form>
    <Link to="/sing-up"> Sing up</Link>
   
    </div>
  )
}

export default Login