import React, { useState } from 'react';
import { Form, Button, Row, Container, Col, FormGroup } from 'react-bootstrap';
import mainImage from "../assests/juicen.png";

const LoginForm=()=> {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Here, you can add your authentication logic with the email and password entered by the user
  }

  return (
    <Container style={Containers}>
        <div>
            <img src={mainImage} alt="A table full of Jucie!" />  
        </div>
        <Form style={{marginLeft:'150px' , color : 'green'}}>
            <h2>Admin Login Page</h2>
            <Row>
                <Col>
                    <Form.Group controlId='FormEmail'>
                        <Form.Label>User Name</Form.Label>
                        <Form.Control type='text' placeholder='Enter Email' />
                    </Form.Group>
                </Col>
            </Row>
            <br/>
            <Row>
                <Col>
                    <Form.Group controlId='FormEmail'>
                        <Form.Label>Password :</Form.Label>
                        <Form.Control type='text' placeholder='Enter Password' />
                    </Form.Group>
                </Col>
            </Row><br/>
            <Row>
                <Button variant='primary' type='submit' style={ButtonStyle}>Submit</Button>
            </Row>
        </Form>
    </Container>
  );
};
const Containers = {
    marginTop:'150px',
    fontSize:'20px',
    marginLeft:'-20px',
    display:'flex'
};
const ButtonStyle ={
    borderRadius:'20%',
    height:'40px',
    width:'200px',
    marginLeft:'35px',
    backgroundColor:'green'
}

export default LoginForm;
