import { ExperienceView } from "../views/ExperienceView";
import { observer } from "mobx-react-lite";

const Experience = observer(
    function ExperienceRender(props){
        return <ExperienceView language={props.language} />;
    }
);

export {Experience};