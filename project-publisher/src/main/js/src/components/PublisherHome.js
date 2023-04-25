import React from 'react';
import DiscussionFilteredContainer from "./DiscussionFilteredContainer";
import {useParams} from "react-router-dom";

function PublisherHome() {

    const {id} = useParams();

    return (
        <div>
            <DiscussionFilteredContainer id={id}/>
        </div>
    );
}

export default PublisherHome;