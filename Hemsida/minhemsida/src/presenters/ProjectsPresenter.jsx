import { ProjectsView } from "../views/ProjectsView";
import { observer } from "mobx-react-lite";

const Projects = observer(
    function ProjectsRender(props){
        return <ProjectsView language={props.language} />;
    }
);

export {Projects};