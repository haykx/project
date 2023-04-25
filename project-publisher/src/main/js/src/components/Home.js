import React from 'react';
import DiscussionContainer from "./DiscussionContainer";
import {useParams} from 'react-router-dom'

function Home() {
    const {page} = useParams();
    return (
        <div>
            <DiscussionContainer page={page}/>
        </div>

    );
}

export default Home;