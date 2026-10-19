//import '/src/styles/quizpageStyle.css'

export function ResultView(props) {


    function playAgainACB() {
        props.onPlayAgain();
    }

    return (<div className='quizCSS'>
        <h1> {props.language === "swe" ? 
        `Du fick ${props.points} poäng!`
        :
         `You got ${props.points} ${props.points === 1 ? "point" : "points"}!`}
        </h1>
        <button className="answerButton" onClick={playAgainACB}><p>{props.language === "swe" ? "SPELA IGEN" : "PLAY AGAIN"}</p></button>
    </div>);
}