import React, {useEffect, useState} from 'react';
import Like from "./Like";
import AddComment from "./AddComment";
import config from '../config.json';

function Comment(props) {

    const [comment, setComment] = useState({
        replies: [],
        publisherName: "",
        replyCount: 0
    });
    const [size, setSize] = useState();
    const [display, setDisplay] = useState();
    const token = 'Bearer ' + localStorage.getItem('token');
    const PUB_URL = config.PUBLISHER_URL;

    useEffect(() => {
        setComment(props.comment);
        setSize(props.size);
        setDisplay(false);
    }, [props.comment, props.size]);

    const showMore = () => {
        if (!comment?.replies) {
            fetch(`${PUB_URL}/comment/${comment?.id}`, {
                headers: {
                    Authorization: token,
                    Accept: "application/json"
                }
            }).then(response => response.json())
                .then(data => setComment(data))
                .catch(e => console.log(e));
        }

        setDisplay(!display);
    };

    const refresh = () => () => {
        fetch(`${PUB_URL}/comment/${comment?.id}`, {
            headers: {
                Authorization: token,
                Accept: "application/json"
            }
        }).then(response => response.json())
            .then(data => setComment(data))
            .catch(e => console.log(e));
    };

    return (
        <div className={'comments'} style={{width: size + '%'}}>
            <div className={'comment'} key={comment?.id}>
                <h3>{comment?.text}</h3>
                <div className={'details'}>
                    <div>
                        {comment?.publisherName}
                    </div>
                    <div>
                        {new Date(comment?.created).toLocaleDateString("en", {
                            year: 'numeric',
                            month: 'short',
                            day: 'numeric',
                            hour: 'numeric',
                            minute: '2-digit'
                        })}
                    </div>
                </div>
                <Like scope={'comment'} id={comment?.id} likes={comment?.likes} isLiked={comment?.liked}/>
                <button className={'show-hide-button'} style={{display: comment?.replyCount ? "flex" : "none"}}
                        onClick={showMore}>{display ? "Hide" : "Show"} {comment?.replyCount} replies
                </button>
                <AddComment scope={'comment'} parent={comment?.id} refreshAction={refresh}/>
                <div className={'reply-box'} style={{display: display ? "flex" : "none"}}>
                    {comment?.replies?.map((r) => (
                        <Comment comment={r} size={size}/>
                    ))}
                </div>
            </div>


        </div>
    );
}

export default Comment;