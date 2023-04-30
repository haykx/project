import React, {useContext, useEffect, useState} from 'react'
import {useNavigate, useParams} from 'react-router-dom'
import CommentBox from "./CommentBox";
import Like from "./Like";
import AddComment from "./AddComment";
import config from '../config.json';
import {ApplicationContext} from "./ApplicationContext";


function Discussion() {

    const [discussion, setDiscussion] = useState({
        created: null,
        liked: false
    });
    const navigate = useNavigate();
    const {logged, publisher, setPublisher} = useContext(ApplicationContext);
    const {id} = useParams();
    const token = localStorage.getItem("token") ? "Bearer " + localStorage.getItem("token") : null;
    const PUB_URL = config.PUBLISHER_URL;

    useEffect(() => {
        fetch(`${PUB_URL}/discussion/${id}`, {
            headers: token ? {
                "Authorization": token
            } : {}
        })
            .then(response => response.json())
            .then(data => {
                setDiscussion(data);
                fetch(`${PUB_URL}/publisher/${data.publisherId}`, {})
                    .then(response => response.json())
                    .then(data => setPublisher(data))
                    .catch(e => console.log(e));
            })
            .catch(() => navigate('/home'));
    }, [PUB_URL, id, navigate, token, setPublisher]);


    const refresh = () => {
        fetch(`${PUB_URL}/discussion/${id}`, {
            headers: token ? {
                "Authorization": token
            } : {}
        })
            .then(response => response.json())
            .then(data => {
                setDiscussion(data);
            });
    };

    return (
        <div className={'container'}>
            <div className={'discussion'}>
                <div onClick={() => navigate(-1)} className={'back-home'}>
                    <svg viewBox="0 0 1024 1024" className="icon" version="1.1" xmlns="http://www.w3.org/2000/svg">
                        <g id="SVGRepo_iconCarrier">
                            <path
                                d="M669.6 849.6c8.8 8 22.4 7.2 30.4-1.6s7.2-22.4-1.6-30.4l-309.6-280c-8-7.2-8-17.6 0-24.8l309.6-270.4c8.8-8 9.6-21.6 2.4-30.4-8-8.8-21.6-9.6-30.4-2.4L360.8 480.8c-27.2 24-28 64-0.8 88.8l309.6 280z">
                            </path>
                        </g>
                    </svg>
                    Back
                </div>
                <div className={'question'}>
                    <h2>{discussion.question}</h2>
                </div>
                <div className={'body'}>
                    <p>{discussion.body}</p>
                </div>
                <div className={'details'}>
                    <div>
                        {publisher?.firstName} {publisher?.lastName}
                    </div>
                    <div>
                        {new Date(discussion?.created).toLocaleDateString("en", {
                            year: 'numeric',
                            month: 'short',
                            day: 'numeric',
                            hour: 'numeric',
                            minute: '2-digit'
                        })}
                    </div>
                </div>
                {logged ? (
                    <Like scope={'discussion'} id={discussion.id} likes={discussion?.likes} isLiked={discussion.liked}/>
                ) : null}
            </div>
            {discussion?.comments?.length > 0 ? (
                <h4>Comments:</h4>
            ) : null}
            {logged ? (
                <AddComment scope={'discussion'} parent={discussion.id} refreshAction={refresh}/>
            ) : null}
            <CommentBox comments={discussion?.comments}></CommentBox>

        </div>
    );
}

export default Discussion;