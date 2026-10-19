import { EducationView } from "../views/EducationView";
import { observer } from "mobx-react-lite";

const Education = observer(
    function EducationRender(props){
        return <EducationView language={props.language} />;
    }
);

export {Education};