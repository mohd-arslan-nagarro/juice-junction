import { Fragment } from "react";
import {Col,Row} from 'react-bootstrap';
import Header from "../Common/Header";
import SignUpForm from "./SignUpForm";

const SignUp =()=>{
    return (
        <Fragment>
            <header>
                <Header />
            </header>
            <body>
                <div><SignUpForm/></div>
            </body>
        </Fragment>
        
    )
}

export default SignUp
;