import React, {useContext} from 'react';
import DiscussionFilteredContainer from "./DiscussionFilteredContainer";
import {useParams} from "react-router-dom";
import config from '../config.json';
import {ApplicationContext} from "./ApplicationContext";

function PublisherHome() {

    let {id} = useParams();
    const PUB_URL = config.PUBLISHER_URL;
    const {setLogged, setPublisher} = useContext(ApplicationContext);
    const token = "Bearer " + localStorage.getItem("token");

    if (!id) {
        fetch(`${PUB_URL}/publisher/me`, {
            headers: {
                "Authorization": token
            }
        }).then(res => res.json())
            .then(data => {
                setPublisher(data);
                setLogged(true);
                id = data?.id;
            })
            .catch(e => console.log(e));
    }

    return (
        <div>
            <DiscussionFilteredContainer id={id}/>
        </div>
    );
}

export default PublisherHome;