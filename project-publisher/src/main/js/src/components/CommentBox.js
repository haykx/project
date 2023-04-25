import React, {useEffect, useState} from 'react';
import Comment from "./Comment";

function CommentBox(props) {

    const [comments, setComments] = useState();
    const defaultSize = 100;

    useEffect(() => {
            setComments(props.comments)
        }
        , [props.comments]);


    return (
        <div className={'comments'}>
            {comments?.map((e) => (
                <Comment comment={e} size={defaultSize}/>
            ))}
        </div>
    );
}

export default CommentBox;