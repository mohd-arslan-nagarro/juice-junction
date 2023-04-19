import { Fragment } from "react";
import mainImage from "../assests/juicen.png";
import classes from "./Header.module.css";

const Image = (props) => {
  return (
    <Fragment>
      <div className={classes["main-image"]}>
        <img src={mainImage} alt="A table full of Jucie!" />  
      </div>
    </Fragment>
  );
};

export default Image;