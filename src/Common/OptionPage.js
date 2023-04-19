import React from 'react';
import {Container,Row,Button} from 'react-bootstrap';
import {Link} from 'react-router-dom';

const Home = () => {

  return (
    <Container>
        <Row>
                <div style={Containers}>
                  <Link to={'adminLogin'}>
                    <Button style={ButtonStyle}>Admin</Button>
                  </Link>
                  <Link to={'userLogin'}>
                    <Button style={ButtonStyle}>User</Button>
                  </Link>
                </div>
        </Row>
    </Container>
  );
};

const Containers={
  display:'inline-flex'
  ,marginTop:'50%'
};
const ButtonStyle={
  width:'250px',
  height:'50px',
  borderRadius:'25%',
  margin:'20px',
  backgroundColor:'green',
  color : 'white'
  
};

export default Home;

