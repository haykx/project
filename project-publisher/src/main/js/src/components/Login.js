import React, {useContext, useState} from 'react';
import {useNavigate} from "react-router-dom";
import config from '../config.json';
import {ApplicationContext} from "./ApplicationContext";

function Login() {

    const navigate = useNavigate();
    const [email, setEmail] = useState([])
    const [password, setPassword] = useState([])
    const {setLogged, setPublisher} = useContext(ApplicationContext);
    const PUB_URL = config.PUBLISHER_URL;
    const BASE_URL = config.BASE_URL;

    const handleSubmit = (e) => {
        e.preventDefault();

        const b = {
            email: email,
            password: password
        }

        fetch(`${BASE_URL}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(b)
        }).then(response => response.json())
            .then(data => {
                console.log(data?.access_token);
                localStorage.setItem('token', data?.access_token);
                setLogged(true);
                fetch(`${PUB_URL}/publisher`, {
                    method: 'POST',
                    headers: {
                        "Authorization": `Bearer ${localStorage.getItem('token')}`
                    }
                }).then(response => response.json())
                    .then(data => {
                        setPublisher(data);
                        navigate('/publisher/' + data?.id);
                    })
                    .catch(() => alert("Invalid credentials"));

            })
            .catch(() => alert("Invalid credentials"));


    };

    return (
        <div className='login-container container'>
            <form className='login-form' onSubmit={handleSubmit}>
                <input
                    type='email'
                    placeholder='Email'
                    onChange={e => setEmail(e?.target?.value)}
                    value={email}
                    required
                />
                <input
                    type='password'
                    placeholder='Password'
                    onChange={e => setPassword(e?.target?.value)}
                    value={password}
                    required
                />
                <button>Log In</button>
            </form>
        </div>
    );
}

export default Login;