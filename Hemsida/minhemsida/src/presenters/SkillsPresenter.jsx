import { SkillsView } from "../views/SkillsView";
import { observer } from "mobx-react-lite";

const Skills = observer(
    function SkillsRender(props){
        return <SkillsView language={props.language} />;
    }
);

export {Skills};