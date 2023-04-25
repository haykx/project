import React, {useContext} from 'react';
import {Link} from 'react-router-dom';
import {ApplicationContext} from "./ApplicationContext";
import config from "../config.json";

function Header() {
    const {logged, setLogged, publisher, setPublisher} = useContext(ApplicationContext);
    const PUB_URL = config.PUBLISHER_URL;
    const token = "Bearer " + localStorage.getItem("token");
    const path = "/publisher/" + publisher?.id;

    const handleLogout = () => {
        setLogged(false);
        localStorage.clear();
    }

    const me = () => {
        fetch(`${PUB_URL}/publisher/me`, {
            headers: token
        }).then(res => res.json())
            .then(data => setPublisher(data))
            .catch(e => console.log(e));

        return null;
    }

    if (!publisher) {
        me();
    }

    return (
        <header>
            <div className="nav">
                <Link className="logo" to="/home">Home</Link>
                {
                    logged ? (
                        <div>
                            <Link className="sign-up" to={path}>My discussions</Link>
                            <Link className="login" to="/login" onClick={handleLogout}>Log Out</Link>
                        </div>
                    ) : (
                        <div>
                            <Link className="sign-up" to="/sign-up">Sign Up</Link>
                            <Link className="login" to="/login">Log In</Link>
                        </div>
                    )
                }

            </div>
        </header>
    );

}

export default Header;