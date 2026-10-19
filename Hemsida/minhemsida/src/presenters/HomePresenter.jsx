import { HomeView } from "../views/HomeView";
import { observer } from "mobx-react-lite";

const Home = observer(
    function HomeRender(props){
        return <HomeView language={props.language} />;
    }
);

export {Home};