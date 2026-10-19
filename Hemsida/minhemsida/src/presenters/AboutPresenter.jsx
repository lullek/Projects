import { AboutView } from "../views/AboutView";
import { observer } from "mobx-react-lite";

const About = observer(
    function AboutRender(props){
        return <AboutView language={props.language} />;
    }
);

export {About};