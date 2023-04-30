import './App.css';
import React, {useState} from "react";
import {HashRouter as Router, Navigate, Route, Routes} from "react-router-dom";
import Header from "./components/Header";
import Login from "./components/Login";
import Footer from "./components/Footer";
import Discussion from "./components/Discussion";
import Home from "./components/Home"
import PublisherHome from "./components/PublisherHome";
import PostCreate from "./components/PostCreate";
import SignUp from "./components/SignUp";
import Survey from "./components/Survey";
import {ApplicationContext} from "./components/ApplicationContext";
import config from "./config.json";


window.addEventListener('scroll', () => {
    const header = document.querySelector('header')
    header.classList.toggle('shadow', window.scrollY > 0)
})

function App() {

    const [logged, setLogged] = useState(!!localStorage.getItem("token"));
    const [publisher, setPublisher] = useState({});
    const PUB_URL = config.PUBLISHER_URL;

    if (logged && !publisher) {
        fetch(`${PUB_URL}/publisher/me`, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }).then(res => res.json())
            .then(data => {
                setPublisher(data);
            })
            .catch(e => console.log(e));
    }


    return (
        <ApplicationContext.Provider value={{logged, setLogged, publisher, setPublisher}}>
            <div className="App">
                <Router>
                    <Header/>
                    <Routes>
                        <Route path="/home" element={<Home/>}/>
                        <Route path="/home/:page" element={<Home/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="/sign-up" element={<SignUp/>}/>
                        <Route path="/discussion/:id" element={<Discussion/>}/>
                        <Route path="/survey/:id" element={<Survey/>}/>
                        <Route path="/publisher/:id" element={<PublisherHome/>}/>
                        <Route path="/publisher/:id/new" element={<PostCreate/>}></Route>
                        <Route path={"*"} element={logged ? <Navigate to={"/home"}/> : <Navigate to={"/login"}/>}/>
                    </Routes>
                    <Footer/>
                </Router>
            </div>
        </ApplicationContext.Provider>
    );
}

export default App;
