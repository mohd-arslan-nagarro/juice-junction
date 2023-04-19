import { Fragment } from "react";
import Header from "./Header";
import Image from "./Image";
import Home from './OptionPage';

const HomePage = (props) => {
  return (
    <Fragment>
      <Header />
      <div style={{display:'inline-flex',backgroundColor:'antiquewhite',width:"100%",height:'120%'}}>
        <Image />  
        <Home/>
      </div>
      
    </Fragment>
  );
};

export default HomePage;