import React, { useState } from 'react';
import { Form, Button, Row, Container, Col, FormGroup } from 'react-bootstrap';
import mainImage from "../assests/juicen.png";

const SignUpForm=()=> {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmpassword, setconfirmPassword] = useState('');


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
            <h2>Sign Up </h2>
            <Row>
                <Col>
                    <Form.Group controlId='FormName'>
                        <Form.Label>Full Name </Form.Label>
                        <Form.Control type='text' placeholder='Enter Name' />
                    </Form.Group>
                </Col>
            </Row>
            <br/>
            <Row>
                <Col>
                    <Form.Group controlId='FormEmail'>
                        <Form.Label>User Name </Form.Label>
                        <Form.Control type='text' placeholder='Enter Email' />
                    </Form.Group>
                </Col>
            </Row>
            <br/>
            <Row>
                <Col>
                    <Form.Group controlId='FormEmail'>
                        <Form.Label>Password </Form.Label>
                        <Form.Control type='text' placeholder='Enter Password' />
                    </Form.Group>
                </Col>
            </Row><br/>
            <Row>
                <Col>
                    <Form.Group controlId='FormPassword'>
                        <Form.Label>Re-Password </Form.Label>
                        <Form.Control type='text' placeholder='Re enter password' />
                    </Form.Group>
                </Col>
            </Row>
            <br/>
            <Row>
                <Button variant='primary' type='submit' style={ButtonStyle}>SignUp</Button>
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

export default SignUpForm ;