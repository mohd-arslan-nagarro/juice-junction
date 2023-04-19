import { Fragment } from "react";
import {Col,Row} from 'react-bootstrap';
import Header from "../Common/Header";
import LoginForm from "./LoginForm";

const AdminLogin =()=>{
    return (
        <Fragment>
            <header>
                <Header />
            </header>
            <body>
                <div><LoginForm /></div>
            </body>
        </Fragment>
        
    )
}

export default AdminLogin;