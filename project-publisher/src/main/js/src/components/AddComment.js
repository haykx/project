import React, {useEffect, useState} from 'react';
import config from '../config.json';

function AddComment(props) {

    const [add, setAdd] = useState(false);
    const [text, setText] = useState("");
    const [scope, setScope] = useState("");
    const [parent, setParent] = useState({});
    const token = 'Bearer ' + localStorage.getItem('token');
    const PUB_URL = config.PUBLISHER_URL;

    useEffect(() => {
        setAdd(false);
        setScope(props.scope);
        setParent(props.parent);
    }, [props.scope, props.parent, props.refreshAction]);

    const submit = (e) => {
        e.preventDefault();

        let b;
        if (scope === 'discussion') {
            b = {
                discussionId: parent,
                text: text
            }
        } else if (scope === 'comment') {
            b = {
                commentId: parent,
                text: text
            }
        } else {
            return;
        }

        fetch(`${PUB_URL}/comment`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(b)
        }).then(() => {
            props?.refreshAction()
        })
            .catch(e => console.log(e))

    }

    return (
        <div className={'add-comment'}>
            <div onClick={() => setAdd(true)}>
                <svg className={add ? 'hide' : ''} viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <g>
                        <path
                            d="M16.19 2H7.81C4.17 2 2 4.17 2 7.81V16.18C2 19.83 4.17 22 7.81 22H16.18C19.82 22 21.99 19.83 21.99 16.19V7.81C22 4.17 19.83 2 16.19 2ZM16 12.75H12.75V16C12.75 16.41 12.41 16.75 12 16.75C11.59 16.75 11.25 16.41 11.25 16V12.75H8C7.59 12.75 7.25 12.41 7.25 12C7.25 11.59 7.59 11.25 8 11.25H11.25V8C11.25 7.59 11.59 7.25 12 7.25C12.41 7.25 12.75 7.59 12.75 8V11.25H16C16.41 11.25 16.75 11.59 16.75 12C16.75 12.41 16.41 12.75 16 12.75Z"></path>
                    </g>
                </svg>
            </div>
            <form onSubmit={submit} className={add ? '' : 'hide'}>
                <input onChange={(e) => setText(e?.target?.value)} name={'text'} placeholder={'Comment'}></input>
                <div>
                    <button type={"submit"}>Add</button>
                    <button onClick={() => setAdd(false)} type={"reset"}>Cancel</button>
                </div>
            </form>
        </div>

    );
}

export default AddComment;