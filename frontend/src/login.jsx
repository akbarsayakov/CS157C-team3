import React, {useState} from "react"
import loginImage from './LoginImage.jpeg'

export const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
    }
    
    return (
        <div className="container">
            <div class="left-side">
                <img src={loginImage}/>
            </div>

            <div class="right-side">
                <div class="header">
                    <h1>Recipe Finder </h1> 
                    <p>Login to search for recipes </p>
                </div>
                <form onSubmit={handleSubmit} class="login-form">
                    <div class="item">
                        <label htmlFor="username">Enter username</label>
                        <input value={username} onChange={(e)=>setUsername(e.target.value)} type="text" id="username" name="username"></input>
                    </div>

                    <div class="item">
                        <label htmlFor="password">Enter Password</label>
                        <input value={password} onChange={(e)=>setPassword(e.target.value)} type="password" id="password" name="password"></input>
                    </div>

                    <button type="submit">Log in</button>

                    <p> Don't have an account? </p>
                    <button onClick={() => props.onFormSwitch('register')} type="submit"> Create an account </button>
                </form>
            </div>
         </div>
    )
}