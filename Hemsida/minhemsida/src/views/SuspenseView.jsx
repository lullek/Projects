export function SuspenseView(props) {
    if(!props.promiseOne && !props.promiseTwo && !props.promiseThree && !props.promiseFour && !props.promiseFive){
        return <div className="modelReadySuspenseCSS">
                <img src= "https://brfenergi.se/iprog/loading.gif"/>
               </div>;
    }
    if(props.errorOne){
        return <span>{props.errorOne.toString()}</span>;
    }
    if(props.errorTwo){
        return <span>{props.errorTwo.toString()}</span>;
    }
    if(props.errorThree){
        return <span>{props.errorThree.toString()}</span>;
    }
    if(props.errorFour){
        return <span>{props.errorFour.toString()}</span>;
    }
    if(props.errorFive){
        return <span>{props.errorFive.toString()}</span>;
    }
    return <div className="quizCSS"><img src= "https://brfenergi.se/iprog/loading.gif" /></div>;
};