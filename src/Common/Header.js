import { Fragment } from "react";
import classes from "./Header.module.css";
import {Button} from 'react-bootstrap';

const Header = (props) => {
  return (
    <Fragment>
      <header className={classes.header}>
        <h1>The Juice Junctions</h1>
        {/* <h3>About Us</h3>
        <h3>Contact Us</h3> */}
      </header>
    </Fragment>
  );
};

export default Header;