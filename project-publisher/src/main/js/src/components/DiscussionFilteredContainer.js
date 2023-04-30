import React, {useEffect, useState} from "react";
import {Link, useParams} from 'react-router-dom';
import config from '../config.json';

function DiscussionFilteredContainer() {

    const {id} = useParams();
    const [discussions, setDiscussions] = useState([]);
    const PUB_URL = config.PUBLISHER_URL;


    useEffect(() => {
        fetch(`${PUB_URL}/publisher/${id}`, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            }
        })
            .then(response => response.json())
            .then(data => setDiscussions(data?.discussions))
            .catch(e => console.log(e))
    }, [PUB_URL, id])


    return (
        <div>

            <h1 className={'your-posts'}>Your discussions</h1>
            <div className={'post container'}>
                <Link to={`/publisher/${id}/new`}>
                    <div className={'post-box create'}>
                        <svg className={'create-icon'} viewBox="0 0 20 20" id="meteor-icon-kit__regular-plus"
                             fill="none" xmlns="http://www.w3.org/2000/svg">
                            <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
                            <g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g>
                            <g id="SVGRepo_iconCarrier">
                                <path fillRule="evenodd" clipRule="evenodd"
                                      d="M9 9V1C9 0.44772 9.4477 0 10 0C10.5523 0 11 0.44772 11 1V9H19C19.5523 9 20 9.4477 20 10C20 10.5523 19.5523 11 19 11H11V19C11 19.5523 10.5523 20 10 20C9.4477 20 9 19.5523 9 19V11H1C0.44772 11 0 10.5523 0 10C0 9.4477 0.44772 9 1 9H9z"></path>
                            </g>
                        </svg>
                        <p className={"post-title flicker"}>New discussion</p>
                    </div>
                </Link>
                {discussions?.map((d) => (
                    <div className={'post-box'} key={d?.id}>
                        <Link className="post-title flicker" to={`/discussion/${d?.id}`}>{d?.question}</Link>
                        <div>
                            <span className="post-date">
                                {new Date(d?.created).toLocaleDateString("en", {
                                    year: 'numeric',
                                    month: 'short',
                                    day: 'numeric',
                                    hour: 'numeric',
                                    minute: '2-digit'
                                })}
                            </span>
                            <span>
                                likes: {d?.likes}
                            </span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default DiscussionFilteredContainer;