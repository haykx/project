import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import config from '../config.json';

function Survey() {

    const [survey, setSurvey] = useState({
        questionnaire: []
    });
    const [formState, setFormState] = useState({});
    const [publisher, setPublisher] = useState();
    const navigate = useNavigate();
    const {id} = useParams();
    const token = 'Bearer ' + localStorage.getItem('token');
    const PUB_URL = config.PUBLISHER_URL;


    function handleInputChange(e) {
        const {name, value} = e.target;
        setFormState((prevState) => ({...prevState, [name]: value}));
        console.log(formState);
    }

    const handleCheckboxChange = (e) => {
        let fs = formState;
        const checked = e.target.checked;
        const split = e.target?.name?.split(" | ", 2);
        const q = split[0];
        const n = split[1];
        console.log(q, n);
        if (fs[q]) {
            if (checked) {
                fs[q] += n + "_";
            } else {
                fs[q] = fs[q].replace(n + "_", "");
            }
        } else {
            fs[q] = n + "_";
        }

        setFormState(fs);
        console.log(fs);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const body = {
            surveyId: survey?.id,
            answers: formState
        }

        fetch(`${PUB_URL}/survey-participants`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(body)
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                navigate(-1);
            })
            .catch(e => console.log(e))
    };

    useEffect(() => {
        fetch(`${PUB_URL}/survey/${id}`, {
            headers: {
                "Authorization": token
            }
        })
            .then(response => response.json())
            .then(data => {
                setSurvey(data);
                fetch(`${PUB_URL}/publisher/${data.publisherId}`, {
                    headers: {
                        "Authorization": token
                    }
                })
                    .then(response => response.json())
                    .then(data => setPublisher(data))
                    .catch(e => console.log(e));
            })
            .catch(() => navigate('/home'));
    }, [PUB_URL, id, navigate, token]);


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
                    <h2>{survey.title}</h2>
                </div>
                <div className={'body'}>
                    <p>{survey.body}</p>
                </div>
                <div className={'details'}>
                    <div>
                        {publisher?.firstName} {publisher?.lastName}
                    </div>
                    <div>
                        {new Date(survey?.created).toLocaleDateString("en", {
                            year: 'numeric',
                            month: 'short',
                            day: 'numeric',
                            hour: 'numeric',
                            minute: '2-digit'
                        })}
                    </div>
                </div>
            </div>
            <form className={"comments survey"} onSubmit={handleSubmit}>
                {survey?.questionnaire?.map((question) => {
                    switch (question?.type) {
                        case "DROPDOWN":
                            return (
                                <div className={"comment"}>
                                    <label htmlFor={question?.id}>{question?.question}</label>
                                    <select name={question?.id} required={question?.required}
                                            onChange={handleInputChange}>
                                        {question.options.map((option) =>
                                                (
                                                    <option>{option}</option>
                                                )
                                            )}
                                        </select>
                                    </div>
                                )

                            case "RADIO":
                                return (
                                    <div className={"comment"}>
                                        <label htmlFor={question?.id}>{question?.question}</label>
                                        {question.options.map((option, i) =>
                                            (
                                                <div>
                                                    <input type={"radio"} id={i} name={question?.id}
                                                           required={question?.required} onChange={handleInputChange}/>
                                                    <label htmlFor={i}>{option}</label>
                                                </div>
                                            )
                                        )}
                                    </div>
                                );

                            case "SHORT_ANSWER":
                                return (
                                    <div className={"comment"}>
                                        <label htmlFor={question?.id}>{question?.question} </label>
                                        <input name={question?.id} required={question?.required}
                                               onChange={handleInputChange}/>
                                    </div>
                                );
                            case "PARAGRAPH":
                                return (
                                    <div className={"comment"}>
                                        <label htmlFor={question?.id}>{question?.question}</label>
                                        <textarea name={question?.id} required={question?.required}
                                                  onChange={handleInputChange}></textarea>
                                    </div>
                                );
                            case "MULTIPLE_CHOICE":
                                return (
                                    <div className={"comment"}>
                                        <label htmlFor={question?.id}>{question?.question}</label>
                                        <select name={question?.id} multiple required={question?.required}
                                                onChange={handleInputChange}>
                                            {question.options.map((option) =>
                                                (
                                                    <option>{option}</option>
                                                )
                                            )}
                                        </select>
                                    </div>
                                );

                            case "DATE":
                                return (
                                    <div className={"comment"}>
                                        <label htmlFor={question?.id}>{question?.question}</label>
                                        <input name={question?.id} type={"date"} onChange={handleInputChange}/>
                                    </div>
                                );
                            case "CHECKBOX":
                                return (
                                    <div className={"comment"}>
                                        <label htmlFor={question?.id}>{question?.question}</label>
                                        {question.options.map((option, i) =>
                                            (
                                                <div>
                                                    <input type={"checkbox"} id={i} name={question?.id + " | " + option}
                                                           onChange={handleCheckboxChange}/>
                                                    <label htmlFor={i}>{option}</label>
                                                </div>
                                            )
                                        )}
                                    </div>
                                );
                            default:
                                return null;
                        }
                    }
                )}
                <button type={"submit"}>submit</button>
            </form>
        </div>
    );
}


// function generateQuestion(question){
//     switch (type) {
//         case "DROPDOWN":
//             return (
//                 <div className={"comment"}>
//                     <label htmlFor={question?.id}>{question?.question}</label>
//                     <select name={question?.id} required={question?.required}>
//                         {question.options.map((option, i) =>
//                             (
//                                 <option>{option}</option>
//                             )
//                         )}
//                     </select>
//                 </div>
//             )
//
//         case "RADIO":
//             return (
//                 <div className={"comment"}>
//                     <label htmlFor={question?.id}>{question?.question}</label>
//                     {question.options.map((option, i) =>
//                         (
//                             <div>
//                                 <input type={"radio"} id={i} name={question?.id} required={question?.required} onChange={handleInputChange} />
//                                 <label htmlFor={i}>{option}</label>
//                             </div>
//                         )
//                     )}
//                 </div>
//             );
//
//         case "SHORT_ANSWER":
//             return (
//                 <div className={"comment"}>
//                     <label htmlFor={question?.id}>{question?.question} </label>
//                     <input name={question?.id} required={question?.required} />
//                 </div>
//             );
//         case "PARAGRAPH":
//             return (
//                 <div className={"comment"}>
//                     <label htmlFor={question?.id}>{question?.question}</label>
//                     <textarea name={question?.id} required={question?.required}></textarea>
//                 </div>
//             );
//         case "MULTIPLE_CHOICE":
//             return (
//                 <div className={"comment"}>
//                     <label htmlFor={question?.id}>{question?.question}</label>
//                     <select name={question?.id} multiple required={question?.required}>
//                         {question.options.map((option, i) =>
//                             (
//                                 <option>{option}</option>
//                             )
//                         )}
//                     </select>
//                 </div>
//             );
//
//         case "DATE":
//             return (
//                 <div className={"comment"}>
//                     <label htmlFor={question?.id}>{question?.question}</label>
//                     <input name={question?.id} type={"date"} ></input>
//                 </div>
//             );
//         case "CHECKBOX":
//             return (
//                 <div className={"comment"}>
//                     <label htmlFor={question?.id}>{question?.question}</label>
//                     {question.options.map((option, i) =>
//                         (
//                             <div>
//                                 <input type={"checkbox"} id={i} name={question?.id} />
//                                 <label htmlFor={i}>{option}</label>
//                             </div>
//                         )
//                     )}
//                 </div>
//             );
//         default:
//             return null;
//     }
// }

export default Survey;