import React, {useState} from "react"

export const Register = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [fName, setFirstName] = useState(''); 
    const [lName, setLastName] = useState(''); 

    const handleSubmit = (e) => {
        e.preventDefault();
    }
    
    return (
        <div className="container"> 
            <div className="registration-container"> 
                <form onSubmit={handleSubmit} class="login-form">
                    <h2> Registration </h2> 
                    
                    <div class="item">
                        <label htmlFor="firstName">First name </label>
                        <input value={fName} onChange={(e)=>setFirstName(e.target.value)} type="text" id="firstName" name="firstName"></input>
                    </div>

                    <div class="item">
                        <label htmlFor="lastName">Last name </label>
                        <input value={lName} onChange={(e)=>setLastName(e.target.value)} type="text" id="lastName" name="lastName"></input>
                    </div>

                    <div class="item">
                        <label htmlFor="username"> Username </label>
                        <input value={username} onChange={(e)=>setUsername(e.target.value)} type="text" id="username" name="username"></input>
                    </div>

                    <div class="item">
                        <label htmlFor="password"> Password </label>
                        <input value={password} onChange={(e)=>setPassword(e.target.value)} type="password" id="password" name="password"></input>
                    </div>

                    <button type="submit">Register</button>
                    <button onClick={() => props.onFormSwitch('login')} > Already have an account? Sign in </button>

                </form>
            </div>
        </div>
    )
}